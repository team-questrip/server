package com.questrip.reward.domain.recommend;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Recommend {
    private Long id;
    private Long userId;
    private Place place;
    private String placeId;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum Status {
        DENIED,
        KEPT,
        ACCEPTED,
        COMPLETED,
        REVOKED
    }

    public void complete(LatLng userLocation) {
        place.checkIn(userLocation);
        this.status = Status.COMPLETED;
    }

    public void revoke() {
        this.status = Status.REVOKED;
    }


    @Builder
    public Recommend(Long id, Long userId, Place place, String placeId, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.place = place;
        this.placeId = placeId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
