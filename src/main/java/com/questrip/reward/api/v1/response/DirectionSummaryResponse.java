package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.direction.DirectionSummary;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DirectionSummaryResponse {
    private String distance;
    private String duration;

    public DirectionSummaryResponse(DirectionSummary summary) {
        this.distance = summary.getDistance();
        this.duration = summary.getDuration();
    }
}
