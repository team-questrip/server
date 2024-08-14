package com.questrip.reward.client;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Client {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Retryable(
            value = {SdkClientException.class, AmazonServiceException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000)
    )
    public String upload(MultipartFile file) {
        String uploadFileName = getUuidFileName(file.getOriginalFilename());
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getBytes().length);
            amazonS3.putObject(new PutObjectRequest(bucket, uploadFileName, file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new GlobalException(ErrorCode.CAN_NOT_UPLOAD);
        }

        return amazonS3.getUrl(bucket, uploadFileName).toString();
    }

    @Recover
    public String recover(Exception e, MultipartFile f) {
        log.error("[S3 bucket upload error] : {}", e.getMessage());
        throw new GlobalException(ErrorCode.EXTERNAL_SERVER_ERROR);
    }

    private String getUuidFileName(String fileName) {
        String ext = fileName.substring(fileName.indexOf(".") + 1);
        return UUID.randomUUID().toString() + "." + ext;
    }

    public List<Pair<String, String>> upload(List<String> imageUrls, String createdBy) {
        return imageUrls.parallelStream()
                .map(imageUrl -> uploadImage(imageUrl, createdBy))
                .filter(java.util.Objects::nonNull)
                .toList();
    }

    private Pair<String, String> uploadImage(String imageUrl, String createdBy) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            try (InputStream inputStream = connection.getInputStream()) {
                byte[] imageBytes = inputStream.readAllBytes();
                String contentType = connection.getContentType();
                String fileExtension = getFileExtension(contentType);
                String fileName = UUID.randomUUID() + fileExtension;
                String fullPath = "instagram_" + createdBy + "/" + fileName;

                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(imageBytes.length);

                PutObjectRequest putObjectRequest = new PutObjectRequest(
                        bucket,
                        fullPath,
                        new ByteArrayInputStream(imageBytes),
                        metadata
                );

                amazonS3.putObject(putObjectRequest);
                return Pair.of(imageUrl, amazonS3.getUrl(bucket, fullPath).toString());
            }
        } catch (Exception e) {
            log.error("Failed to upload image: {}", imageUrl, e);
            return null;
        }
    }

    private String getFileExtension(String contentType) {
        return switch (contentType) {
            case "video/mp4" -> ".mp4";
            case "image/png" -> ".png";
            case "image/gif" -> ".gif";
            case "image/webp" -> ".webp";
            case "image/tiff" -> ".tiff";
            case "image/bmp" -> ".bmp";
            default -> ".jpg";
        };
    }
}
