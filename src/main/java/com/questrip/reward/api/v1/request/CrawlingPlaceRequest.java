package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.place.Category;
import com.questrip.reward.domain.place.PlaceContent;
import com.questrip.reward.domain.place.PlaceImage;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CrawlingPlaceRequest(
        @NotNull
        String googlePlaceId,
        @NotNull
        Category category,
        @NotNull
        String romanizedPlaceName,
        @NotNull
        String recommendationReason,
        @NotNull
        String activity,
        @NotNull
        List<PlaceImageRequest> images,
        @NotNull
        String createdBy
) {
    public PlaceContent toContent() {
        return new PlaceContent(recommendationReason, activity);
    }

    public List<PlaceImage> toImages(String createdBy) {
        return images.stream().map(r -> r.toPlaceImage(createdBy)).toList();
    }
}