package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.place.PlaceImage;

import java.util.List;

public record PlaceImageRequest(
        Integer sequence,
        String url,
        String createdBy
) {
    public PlaceImage toPlaceImage() {
        return new PlaceImage(sequence, url, createdBy);
    }
}
