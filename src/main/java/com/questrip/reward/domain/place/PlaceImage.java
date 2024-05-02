package com.questrip.reward.domain.place;

import lombok.Getter;

@Getter
public class PlaceImage {
    private int sequence;
    private String url;

    public PlaceImage(int sequence, String url) {
        this.sequence = sequence;
        this.url = url;
    }
}