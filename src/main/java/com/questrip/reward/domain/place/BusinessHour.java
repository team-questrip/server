package com.questrip.reward.domain.place;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BusinessHour {
    private LocalDateTime openTime;
    private LocalDateTime closeTime;

    public BusinessHour(LocalDateTime openTime, LocalDateTime closeTime) {
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public boolean isBetweenBusinessHour(LocalDateTime currentTime) {
        return currentTime.isAfter(openTime) && currentTime.isBefore(closeTime);
    }
}