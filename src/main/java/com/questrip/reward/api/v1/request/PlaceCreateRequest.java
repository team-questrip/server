package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.place.PlaceContent;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.bson.assertions.Assertions;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class PlaceCreateRequest {
    @NotNull(message = "이미지는 필수입니다.")
    private List<MultipartFile> images;
    private String createdBy;
    @NotNull(message = "로마 표기는 필수입니다.")
    private String romanizedPlaceName;
    @NotNull(message = "구글 플레이스 아이디는 필수입니다.")
    private String googlePlaceId;
    @NotNull(message = "추천 이유는 필수입니다.")
    private String recommendationReason;
    @NotNull(message = "추천 활동은 필수입니다.")
    private String activity;

    public PlaceContent toContent() {
        return new PlaceContent(recommendationReason, activity);
    }

    @Builder
    public PlaceCreateRequest(List<MultipartFile> images, String createdBy, String romanizedPlaceName, String googlePlaceId, String recommendationReason, String activity) {
        this.images = images;
        this.createdBy = createdBy;
        this.romanizedPlaceName = romanizedPlaceName;
        this.googlePlaceId = googlePlaceId;
        this.recommendationReason = recommendationReason;
        this.activity = activity;
    }
}
