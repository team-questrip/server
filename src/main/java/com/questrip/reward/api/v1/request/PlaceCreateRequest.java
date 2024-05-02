package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.place.PlaceContent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.bson.assertions.Assertions;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class PlaceCreateRequest {
    @NotNull(message = "이미지는 필수입니다.")
    private List<MultipartFile> images;
    @NotNull(message = "구글 플레이스 아이디는 필수입니다.")
    private String googlePlaceId;
    @NotNull(message = "추천 이유는 필수입니다.")
    private String recommendationReason;
    @NotNull(message = "추천 활동은 필수입니다.")
    private String activity;

    public PlaceContent toContent() {
        return new PlaceContent(recommendationReason, activity);
    }

    public PlaceCreateRequest(List<MultipartFile> images, String googlePlaceId, String recommendationReason, String activity) {
        this.images = images;
        this.googlePlaceId = googlePlaceId;
        this.recommendationReason = recommendationReason;
        this.activity = activity;
    }
}
