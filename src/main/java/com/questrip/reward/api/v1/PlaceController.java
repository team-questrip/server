package com.questrip.reward.api.v1;

import com.questrip.reward.api.v1.request.LocationRequest;
import com.questrip.reward.api.v1.request.MenuGroupListRequest;
import com.questrip.reward.api.v1.request.PlaceCreateRequest;
import com.questrip.reward.api.v1.response.*;
import com.questrip.reward.domain.place.*;
import com.questrip.reward.support.response.ApiResponse;
import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/place")
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public ApiResponse<PlaceResponse> create(PlaceCreateRequest request) {
        Place saved = placeService.save(request.getGooglePlaceId(), request.toContent(), request.getImages());

        return ApiResponse.success("장소 저장 성공", new PlaceResponse(saved));
    }

    @GetMapping("/{placeId}")
    public ApiResponse<PlaceAndDirectionResponse> findOne(@PathVariable String placeId, @ModelAttribute LocationRequest location) {
        PlaceAndDirection placeAndDirection = placeService.findPlaceWithDirectionSummary(placeId, location.toLocation());

        return ApiResponse.success("장소 조회 성공", new PlaceAndDirectionResponse(placeAndDirection));
    }

    @GetMapping
    public ApiResponse<SliceResult<PlaceWithDistanceResponse>> findAll(@ModelAttribute LocationRequest location,
                                                                       @RequestParam(defaultValue = "0", required = false) int page,
                                                                       @RequestParam(defaultValue = "10", required = false) int size
    ) {
        SliceResult<PlaceWithDistanceResponse> response = placeService.findAllPlaceNear(location.toLocation(), page, size)
                .map(p -> new PlaceWithDistanceResponse(p, location.toLocation()));

        return ApiResponse.success("장소 조회 성공", response);
    }

    @GetMapping("/reverseGeocode")
    public ApiResponse<ReverseGeocodeResponse> reverseGeocode(@ModelAttribute LocationRequest location) {
        String address = placeService.reverseGeocode(location.toLocation());

        return ApiResponse.success("주소 변환 성공", new ReverseGeocodeResponse(address));
    }

    @GetMapping("/{placeId}/menu")
    public ApiResponse<MenuGroupListResponse> retrieveMenuGroups(@PathVariable String placeId) {
        Set<MenuGroup> menuGroups = placeService.findMenuGroups(placeId);

        return ApiResponse.success(new MenuGroupListResponse(menuGroups));
    }

    @PostMapping("/menu")
    public ApiResponse<PlaceResponse> addMenuGroup(@RequestBody MenuGroupListRequest request) {
        Place place = placeService.addMenuGroups(request.placeId(), request.toGroups());

        return ApiResponse.success(new PlaceResponse(place));
    }
}
