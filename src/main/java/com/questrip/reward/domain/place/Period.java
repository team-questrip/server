package com.questrip.reward.domain.place;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Period {
    private PeriodDetail open;
    private PeriodDetail close;

    @Getter
    public static class PeriodDetail {
        private int day;
        private int hour;
        private int minute;
    }
}