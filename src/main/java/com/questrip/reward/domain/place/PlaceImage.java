package com.questrip.reward.domain.place;

import lombok.Getter;

@Getter
public class PlaceImage {
    private int sequence;
    private String url;
    private String createdBy;

    public PlaceImage(int sequence, String url, String createdBy) {
        this.sequence = sequence;
        this.url = url;
        this.createdBy = createdBy;
    }
}