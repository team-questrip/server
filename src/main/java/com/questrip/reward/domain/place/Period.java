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

    public BusinessHour getOpenBaseBusinessHour(LocalDateTime baseDate) {
        LocalDateTime openTime = parseToDateTime(open, baseDate).minusSeconds(1L);

        if (areDateDifferent()) {
            LocalDateTime closeTime = parseToDateTime(close, baseDate.plusDays(1L)).plusSeconds(1L);

            return new BusinessHour(openTime, closeTime);
        }

        LocalDateTime closeTime = parseToDateTime(close, baseDate.plusSeconds(1L));

        return new BusinessHour(openTime, closeTime);
    }

    public BusinessHour getCloseBaseBusinessHour(LocalDateTime baseDate) {
        LocalDateTime closeTime = parseToDateTime(close, baseDate).plusSeconds(1L);

        if (areDateDifferent()) {
            LocalDateTime openTime = parseToDateTime(open, baseDate.minusDays(1L)).minusSeconds(1L);

            return new BusinessHour(openTime, closeTime);
        }

        LocalDateTime openTime = parseToDateTime(open, baseDate.minusSeconds(1L));


        return new BusinessHour(openTime, closeTime);
    }

    private boolean areDateDifferent() {
        return open.getDay() != close.getDay();
    }

    private LocalDateTime parseToDateTime(Period.PeriodDetail detail, LocalDateTime baseDate) {
        return LocalDateTime.of(baseDate.getYear(), baseDate.getMonth(), baseDate.getDayOfMonth(), detail.getHour(), detail.getMinute(), 0);
    }
}