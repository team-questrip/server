package com.questrip.reward.api.v1;

import com.questrip.reward.api.support.Language;
import com.questrip.reward.api.v1.request.LocationRequest;
import com.questrip.reward.api.v1.request.RecommendRequest;
import com.questrip.reward.api.v1.response.PlaceResponse;
import com.questrip.reward.api.v1.response.RecommendResponse;
import com.questrip.reward.domain.recommend.Recommend;
import com.questrip.reward.domain.recommend.RecommendService;
import com.questrip.reward.security.details.LoginUser;
import com.questrip.reward.support.response.ApiResponse;
import com.questrip.reward.support.response.SliceResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    @GetMapping
    public ApiResponse<List<PlaceResponse>> getRecommendPlaces(@AuthenticationPrincipal LoginUser loginUser,
                                                               LocationRequest request,
                                                               @RequestParam(defaultValue = "0", required = false) int page,
                                                               @RequestParam(defaultValue = "10", required = false) int size,
                                                               @Language String language) {
        log.info("recommend request, request user Id is : {}, language is : {}", loginUser.getId(), language);
        List<PlaceResponse> result = recommendService.getRecommendPlaces(loginUser.getUser().getId(), request.toLocation(), page, size, language)
                .map(PlaceResponse::new)
                .getContent();

        return ApiResponse.success("추천 장소 조회 완료", result);
    }

    @PostMapping
    public ApiResponse<RecommendResponse> recommend(@AuthenticationPrincipal LoginUser loginUser, @Valid @RequestBody RecommendRequest request) {
        log.info("recommend status is {}, place id is : {}, user id is", request.status(), request.placeId(), loginUser.getId());
        Recommend recommend = recommendService.save(loginUser.getId(), request.placeId(), request.status());

        return ApiResponse.success(new RecommendResponse(recommend));
    }

    @GetMapping("/{status}")
    public ApiResponse<SliceResult<RecommendResponse>> getStatusRecommends(
            @AuthenticationPrincipal LoginUser loginUser,
            @PathVariable String status,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @Language String language
    ) {
        List<Recommend.Status> statusList = Arrays.stream(status.split(","))
                .map(Recommend.Status::valueOf)
                .collect(Collectors.toList());

        SliceResult<RecommendResponse> response = recommendService.getRecommendsWithStatus(loginUser.getId(), statusList, page, size, language)
                .map(RecommendResponse::new);

        return ApiResponse.success(response);
    }

    @PutMapping("/{status}")
    public ApiResponse<Void> recommendStatusUpdate(
            @PathVariable Recommend.Status status,
            @AuthenticationPrincipal LoginUser loginUser,
            LocationRequest request
    ) {
        log.info("recommend status updated. request status is {}, user id is {}, request location latitude:{}, longitude:{}", status, loginUser.getId(), request.getLatitude(), request.getLongitude());
        recommendService.updateRecommendStatus(loginUser.getId(), request.toLocation(), status);

        return ApiResponse.success("처리 완료");
    }

    @GetMapping("/progress")
    public ApiResponse<RecommendResponse> retrieveProgressRecommend(@AuthenticationPrincipal LoginUser loginUser,
                                                                    @Language String language) {
        Recommend recommend = recommendService.retrieveProgressRecommend(loginUser.getId(), language);

        return ApiResponse.success(new RecommendResponse(recommend));
    }
}
