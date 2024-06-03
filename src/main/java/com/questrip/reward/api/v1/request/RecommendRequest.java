package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.recommend.Recommend;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RecommendRequest(@NotEmpty(message = "PlaceId는 필수입니다.") String placeId,
                               @NotNull(message = "status는 필수입니다.") Recommend.Status status) {
}
