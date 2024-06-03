package com.questrip.reward.domain.recommend;

import com.questrip.reward.domain.place.Place;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Recommend {
    private Long id;
    private Long userId;
    private Place place;
    private Status status;
    private LocalDateTime createdAt;

    public enum Status {
        DENIED,
        KEPT,
        ACCEPTED
    }


    @Builder
    private Recommend(Long id, Long userId, Place place, Status status, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.place = place;
        this.status = status;
        this.createdAt = createdAt;
    }
}
