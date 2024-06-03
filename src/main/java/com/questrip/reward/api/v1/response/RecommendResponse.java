package com.questrip.reward.api.v1.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.questrip.reward.domain.recommend.Recommend;

import java.time.LocalDateTime;

public record RecommendResponse(
        Long id,
        Long userId,
        Recommend.Status status,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul") LocalDateTime createdAt,
        PlaceResponse place
) {
    public RecommendResponse(Recommend recommend) {
        this(recommend.getId(), recommend.getUserId(), recommend.getStatus(), recommend.getCreatedAt(), new PlaceResponse(recommend.getPlace()));
    }
}
