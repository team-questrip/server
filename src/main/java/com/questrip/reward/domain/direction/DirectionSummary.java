package com.questrip.reward.domain.direction;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DirectionSummary {
    private String distance;
    private String duration;

    public DirectionSummary(String distance, String duration) {
        this.distance = distance;
        this.duration = duration;
    }
}
