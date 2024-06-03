package com.questrip.reward.api.v1;

import com.questrip.reward.api.v1.request.LocationRequest;
import com.questrip.reward.api.v1.request.RecommendRequest;
import com.questrip.reward.api.v1.response.PlaceListResponse;
import com.questrip.reward.api.v1.response.RecommendResponse;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.recommend.Recommend;
import com.questrip.reward.domain.recommend.RecommendService;
import com.questrip.reward.security.details.LoginUser;
import com.questrip.reward.support.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    @GetMapping
    public ApiResponse<PlaceListResponse> getRecommends(@AuthenticationPrincipal LoginUser loginUser, LocationRequest request) {
        List<Place> recommendPlaces = recommendService.getRecommendPlaces(loginUser.getUser().getId(), request.toLocation());

        return ApiResponse.success("추천 장소 조회 완료", new PlaceListResponse(recommendPlaces));
    }

    @PostMapping
    public ApiResponse<RecommendResponse> recommend(@AuthenticationPrincipal LoginUser loginUser, @Valid @RequestBody RecommendRequest request) {
        Recommend recommend = recommendService.save(loginUser.getId(), request.placeId(), request.status());

        return ApiResponse.success(new RecommendResponse(recommend));
    }
}
