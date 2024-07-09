package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.user.UserPreference;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record UserPreferenceRequest(
        int groupSize,
        boolean travelingWithKid,
        boolean vegetarian,
        boolean nonAlcoholic,
        boolean seafoodRestrictions,
        boolean noSpicyFood,
        @Min(value = 1, message = "1이상 숫자로 입력해주세요.") @Max(value = 10, message = "10이하 숫자로 입력해주세요.") int distance
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
