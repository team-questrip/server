package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.user.UserPreference;

public record UserPreferenceResponse(
        int groupSize,
        boolean travelingWithKid,
        DietaryResponse dietary,
        int distance
) {
    public record DietaryResponse(
            boolean vegetarian,
            boolean nonAlcoholic,
            boolean seafoodRestrictions,
            boolean noSpicyFood
    ){
        public DietaryResponse(UserPreference.Dietary dietary) {
            this(dietary.isVegetarian(), dietary.isNonAlcoholic(), dietary.isSeafoodRestrictions(), dietary.isNoSpicyFood());
        }
    }

    public UserPreferenceResponse(UserPreference userPreference) {
        this(userPreference.getGroupSize(), userPreference.isTravelingWithKid(), new DietaryResponse(userPreference.getDietary()), userPreference.getDistance());
    }
}
