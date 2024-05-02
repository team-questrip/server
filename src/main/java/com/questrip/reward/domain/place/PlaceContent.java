package com.questrip.reward.domain.place;

import lombok.Getter;

@Getter
public class PlaceContent {
    private String recommendationReason;
    private String activity;

    public PlaceContent(String recommendationReason, String activity) {
        this.recommendationReason = recommendationReason;
        this.activity = activity;
    }
}
