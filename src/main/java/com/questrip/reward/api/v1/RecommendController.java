package com.questrip.reward.api.v1;

import com.questrip.reward.api.v1.request.LocationRequest;
import com.questrip.reward.api.v1.response.PlaceListResponse;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.recommend.RecommendService;
import com.questrip.reward.security.details.LoginUser;
import com.questrip.reward.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
