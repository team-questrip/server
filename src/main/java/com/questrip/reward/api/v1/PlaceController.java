package com.questrip.reward.api.v1;

import com.questrip.reward.api.v1.request.LocationRequest;
import com.questrip.reward.api.v1.request.PlaceCreateRequest;
import com.questrip.reward.api.v1.response.PlaceAndDirectionResponse;
import com.questrip.reward.api.v1.response.PlaceResponse;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.place.PlaceAndDirection;
import com.questrip.reward.domain.place.PlaceService;
import com.questrip.reward.support.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping("/api/v1/place")
    public ApiResponse<PlaceResponse> create(@Valid PlaceCreateRequest request) {
        Place saved = placeService.save(request.getGooglePlaceId(), request.toContent(), request.getImages());

        return ApiResponse.success("장소 저장 성공", new PlaceResponse(saved));
    }

    @GetMapping("/api/v1/place/{placeId}")
    public ApiResponse<PlaceAndDirectionResponse> findOne(@PathVariable String placeId, @ModelAttribute LocationRequest location) {
        PlaceAndDirection placeAndDirection = placeService.findPlaceWithDirectionSummary(placeId, location.toLocation());

        return ApiResponse.success("장소 조회 성공", new PlaceAndDirectionResponse(placeAndDirection));
    }
}
