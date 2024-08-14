package com.questrip.reward.domain.place;

import com.questrip.reward.client.S3Client;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaceImageUploader {

    private final S3Client s3Client;

    public List<PlaceImage> upload(List<MultipartFile> images, String createdBy) {
        AtomicInteger sequence = new AtomicInteger(1);

        return images.stream()
                .map(f -> new PlaceImage(sequence.getAndIncrement(), s3Client.upload(f), createdBy))
                .collect(Collectors.toList());
    }

    public List<PlaceImage> upload(List<PlaceImage> images) {
        if (images.isEmpty()) {
            return Collections.emptyList();
        }

        String createdBy = images.get(0).getCreatedBy();

        Map<String, PlaceImage> imageMap = images.stream()
                .collect(Collectors.toMap(
                        PlaceImage::getUrl,
                        Function.identity(),
                        (existing, replacement) -> existing
                ));

        List<String> imageUrls = new ArrayList<>(imageMap.keySet());

        return s3Client.upload(imageUrls, createdBy)
                .stream()
                .map(pair -> {
                    PlaceImage originalImage = imageMap.get(pair.getFirst());
                    return new PlaceImage(originalImage.getSequence(), pair.getSecond(), createdBy);
                })
                .toList();
    }
}