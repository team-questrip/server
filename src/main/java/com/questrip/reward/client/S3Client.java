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
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
}