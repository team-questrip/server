package com.questrip.reward.domain.place;

import com.questrip.reward.client.S3Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaceImageUploader {

    private final S3Client s3Client;

    public List<PlaceImage> upload(List<MultipartFile> images) {
        AtomicInteger sequence = new AtomicInteger(1);

        return images.stream()
                .map(f -> new PlaceImage(sequence.getAndIncrement(), s3Client.upload(f)))
                .collect(Collectors.toList());
    }
}