package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.user.UserPreference;

public record UserPreferenceRequest(
        int groupSize,
        boolean travelingWithKid,
        boolean vegetarian,
        boolean nonAlcoholic,
        boolean seafoodRestrictions,
        boolean noSpicyFood,
        int distance
) {
    public UserPreference toUserPreference(Long userId) {
        return UserPreference.builder()
                .userId(userId)
                .groupSize(groupSize)
                .travelingWithKid(travelingWithKid)
                .vegetarian(vegetarian)
                .nonAlcoholic(nonAlcoholic)
                .seafoodRestrictions(seafoodRestrictions)
                .noSpicyFood(noSpicyFood)
                .distance(distance)
                .build();
    }
}
