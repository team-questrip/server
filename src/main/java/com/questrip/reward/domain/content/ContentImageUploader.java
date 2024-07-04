package com.questrip.reward.domain.content;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ContentImageUploader {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.content-bucket}")
    private String bucketName;

    public CompletableFuture<String> uploadImage(String imageUrl, Long contentSequence) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");

                try (InputStream inputStream = connection.getInputStream()) {
                    byte[] imageBytes = inputStream.readAllBytes();

                    String fileName = UUID.randomUUID() + ".jpg";
                    String folderPath = "content_" + contentSequence + "/";
                    String fullPath = folderPath + fileName;
                    ObjectMetadata metadata = new ObjectMetadata();
                    metadata.setContentLength(imageBytes.length);
                    metadata.setContentType("image/jpeg");

                    PutObjectRequest putObjectRequest = new PutObjectRequest(
                            bucketName,
                            fullPath,
                            new ByteArrayInputStream(imageBytes),
                            metadata
                    );

                    amazonS3.putObject(putObjectRequest);

                    return amazonS3.getUrl(bucketName, fullPath).toString();
                } finally {
                    connection.disconnect();
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to upload image: " + e.getMessage(), e);
            }
        });
    }
}