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

    public boolean is24HourService() {
        return this.getOpen().getDay() == 0
                && this.getOpen().getHour() == 0
                && this.getOpen().getMinute() == 0
                && this.getClose() == null;
    }
}