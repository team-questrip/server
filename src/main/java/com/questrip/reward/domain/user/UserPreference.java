package com.questrip.reward.domain.user;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class UserPreference {
    private Long userId;
    private int groupSize;
    private boolean travelingWithKid;
    private Dietary dietary;
    private int distance;

    @Getter
    public static class Dietary {
        private boolean vegetarian;
        private boolean nonAlcoholic;
        private boolean seafoodRestrictions;
        private boolean noSpicyFood;

        public Dietary(boolean vegetarian, boolean nonAlcoholic, boolean seafoodRestrictions, boolean noSpicyFood) {
            this.vegetarian = vegetarian;
            this.nonAlcoholic = nonAlcoholic;
            this.seafoodRestrictions = seafoodRestrictions;
            this.noSpicyFood = noSpicyFood;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Dietary dietary)) return false;
            return isVegetarian() == dietary.isVegetarian() && isNonAlcoholic() == dietary.isNonAlcoholic() && isSeafoodRestrictions() == dietary.isSeafoodRestrictions() && isNoSpicyFood() == dietary.isNoSpicyFood();
        }

        @Override
        public int hashCode() {
            return Objects.hash(isVegetarian(), isNonAlcoholic(), isSeafoodRestrictions(), isNoSpicyFood());
        }
    }

    public static UserPreference defaultPreference(Long userId) {
        return UserPreference.builder()
                .userId(userId)
                .groupSize(1)
                .travelingWithKid(false)
                .vegetarian(false)
                .nonAlcoholic(false)
                .seafoodRestrictions(false)
                .noSpicyFood(false)
                .distance(5)
                .build();
    }

    @Builder
    public UserPreference(Long userId, int groupSize, boolean travelingWithKid, boolean vegetarian, boolean nonAlcoholic, boolean seafoodRestrictions, boolean noSpicyFood, int distance) {
        this.userId = userId;
        this.groupSize = groupSize;
        this.travelingWithKid = travelingWithKid;
        this.dietary = new Dietary(vegetarian, nonAlcoholic, seafoodRestrictions, noSpicyFood);
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPreference that)) return false;
        return getGroupSize() == that.getGroupSize() && isTravelingWithKid() == that.isTravelingWithKid() && getDistance() == that.getDistance() && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getDietary(), that.getDietary());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getGroupSize(), isTravelingWithKid(), getDietary(), getDistance());
    }
}
