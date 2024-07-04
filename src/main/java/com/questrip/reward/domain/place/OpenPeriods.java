package com.questrip.reward.domain.place;

import lombok.Getter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Getter
public class OpenPeriods {
    private List<Period> periods;

    public OpenPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public OpenStatus isOpen(LocalDateTime now) {
        if (periods.isEmpty()) {
            return OpenStatus.UNKNOWN;
        }

        int dayOfWeek = getDayOfWeek(now);
        int yesterdayOfWeek = (dayOfWeek - 1 + 7) % 7;

        for (Period period : periods) {
            if (isWithinPeriod(period, now, dayOfWeek, yesterdayOfWeek)) {
                return OpenStatus.OPEN;
            }
        }

        return OpenStatus.CLOSE;
    }

    private boolean isWithinPeriod(Period period, LocalDateTime now, int dayOfWeek, int yesterdayOfWeek) {
        LocalTime openTime = LocalTime.of(period.getOpen().getHour(), period.getOpen().getMinute());
        LocalTime closeTime = LocalTime.of(period.getClose().getHour(), period.getClose().getMinute());
        LocalTime currentTime = now.toLocalTime();

        if (period.getOpen().getDay() == dayOfWeek) {
            // 현재 날짜가 영업 시작일인 경우
            return !currentTime.isBefore(openTime) &&
                    (closeTime.isBefore(openTime) || !currentTime.isAfter(closeTime));
        } else if (period.getClose().getDay() == dayOfWeek && period.getOpen().getDay() == yesterdayOfWeek) {
            // 현재 날짜가 영업 종료일이고, 영업이 전날부터 시작된 경우
            return currentTime.isBefore(closeTime) || currentTime.equals(closeTime);
        }

        return false;
    }

    private int getDayOfWeek(LocalDateTime currentTime) {
        return currentTime.getDayOfWeek().getValue() % 7;  // 0(일요일)부터 6(토요일)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpenPeriods that)) return false;
        return Objects.equals(getPeriods(), that.getPeriods());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPeriods());
    }
}