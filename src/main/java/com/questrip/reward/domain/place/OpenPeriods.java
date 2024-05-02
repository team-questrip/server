package com.questrip.reward.domain.place;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
public class OpenPeriods {
    private List<Period> periods;

    public OpenPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public OpenStatus isOpen(LocalDateTime now) {
        if(periods.isEmpty()) {
            return OpenStatus.UNKNOWN;
        }

        if(isOpen24Hours()) {
            return OpenStatus.OPEN;
        }

        int dayOfWeek = getDayOfWeek(now);

        Period openBasePeriod = getOpenBasePeriod(dayOfWeek);
        Period closeBasePeriod = getCloseBasePeriod(dayOfWeek);

        if(openBasePeriod == null && closeBasePeriod == null) {
            return OpenStatus.CLOSE;
        }

        if(isBetweenBusinessHour(openBasePeriod, closeBasePeriod, now)) {
            return OpenStatus.OPEN;
        }

        return OpenStatus.CLOSE;
    }

    private boolean isBetweenBusinessHour(Period open, Period close, LocalDateTime current) {
        boolean openResult = false;
        boolean closeResult = false;

        if(open != null) {
            BusinessHour openBaseBusinessHour = open.getOpenBaseBusinessHour(current);
            openResult = openBaseBusinessHour.isBetweenBusinessHour(current);
        }

        if(close != null) {
            BusinessHour closeBaseBusinessHour = close.getCloseBaseBusinessHour(current);
            closeResult = closeBaseBusinessHour.isBetweenBusinessHour(current);
        }

        return openResult || closeResult;
    }

    private Period getOpenBasePeriod(int dayOfWeek) {
        return periods.stream()
                .filter(period -> period.getOpen().getDay() == dayOfWeek)
                .findFirst()
                .orElse(null);
    }

    private Period getCloseBasePeriod(int dayOfWeek) {
        return periods.stream()
                .filter(period -> period.getClose().getDay() == dayOfWeek)
                .findFirst()
                .orElse(null);
    }

    private int getDayOfWeek(LocalDateTime currentTime) {
        int value = currentTime.getDayOfWeek().getValue();

        if (value == 7) {
            return 0;
        }

        return value;
    }

    private boolean isOpen24Hours() {
        return periods.size() == 1 && periods.get(0).getClose() == null;
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