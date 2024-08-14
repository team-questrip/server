package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.place.PlaceContent;
import com.questrip.reward.domain.place.PlaceImage;

import java.util.List;

public record CrawlingPlaceRequest(
        String googlePlaceId,
        String romanizedPlaceName,
        String recommendationReason,
        String activity,
        List<PlaceImageRequest> images
) {
    public PlaceContent toContent() {
        return new PlaceContent(recommendationReason, activity);
    }

    public List<PlaceImage> toImages() {
        return images.stream().map(PlaceImageRequest::toPlaceImage).toList();
    }
}