package com.questrip.reward.domain.recommend;

import com.questrip.reward.domain.place.Place;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Recommend {
    private Long id;
    private Place place;
    private Status status;

    public enum Status {
        DENIED,
        KEPT,
        ACCEPTED
    }

    private LocalDateTime createdAt;

    @Builder
    private Recommend(Long id, Place place, Status status, LocalDateTime createdAt) {
        this.id = id;
        this.place = place;
        this.status = status;
        this.createdAt = createdAt;
    }
}
