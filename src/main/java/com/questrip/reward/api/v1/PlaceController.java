package com.questrip.reward.api.v1;

import com.questrip.reward.api.support.Language;
import com.questrip.reward.api.v1.request.CrawlingPlaceRequest;
import com.questrip.reward.api.v1.request.LocationRequest;
import com.questrip.reward.api.v1.request.MenuGroupListRequest;
import com.questrip.reward.api.v1.request.PlaceCreateRequest;
import com.questrip.reward.api.v1.response.*;
import com.questrip.reward.domain.place.*;
import com.questrip.reward.support.response.ApiResponse;
import com.questrip.reward.support.response.SliceResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/place")
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public ApiResponse<PlaceResponse> create(PlaceCreateRequest request) {
        Place saved = placeService.save(request.getGooglePlaceId(), request.getRomanizedPlaceName(),request.getCategory(), request.toContent(), request.getImages(), request.getCreatedBy());

        return ApiResponse.success("장소 저장 성공", new PlaceResponse(saved));
    }

    @PostMapping("/crawled")
    public ApiResponse<PlaceResponse> createFromCrawledData(@RequestBody @Valid CrawlingPlaceRequest request){
        Place place = placeService.saveCrawlingContents(request.googlePlaceId(), request.category(), request.romanizedPlaceName(), request.toContent(), request.toImages(request.createdBy()));

        return ApiResponse.success(new PlaceResponse(place));
    }

    @GetMapping("/{placeId}")
    public ApiResponse<PlaceAndDirectionResponse> findOne(@Language String language,
                                                          @PathVariable String placeId,
                                                          @ModelAttribute LocationRequest location) {
        PlaceAndDirection placeAndDirection = placeService.findPlaceWithDirectionSummary(placeId, location.toLocation(), language);

        return ApiResponse.success("장소 조회 성공", new PlaceAndDirectionResponse(placeAndDirection));
    }

    @GetMapping
    public ApiResponse<SliceResult<PlaceWithDistanceResponse>> findAll(@ModelAttribute LocationRequest location,
                                                                       @RequestParam(defaultValue = "0", required = false) int page,
                                                                       @RequestParam(defaultValue = "10", required = false) int size,
                                                                       @Language String language,
                                                                       @RequestParam(required = false) CategoryGroup category
    ) {
        SliceResult<PlaceWithDistanceResponse> response = placeService.findAllPlaceNear(location.toLocation(), category, page, size, language)
                .map(p -> new PlaceWithDistanceResponse(p, location.toLocation()));

        return ApiResponse.success("장소 조회 성공", response);
    }

    @GetMapping("/reverseGeocode")
    public ApiResponse<ReverseGeocodeResponse> reverseGeocode(@ModelAttribute LocationRequest location) {
        String address = placeService.reverseGeocode(location.toLocation());

        return ApiResponse.success("주소 변환 성공", new ReverseGeocodeResponse(address));
    }

    @GetMapping("/{placeId}/menu")
    public ApiResponse<MenuGroupListResponse> retrieveMenuGroups(@Language String language,
                                                                 @PathVariable String placeId) {
        Set<MenuGroup> menuGroups = placeService.findMenuGroups(placeId, language);

        return ApiResponse.success(new MenuGroupListResponse(menuGroups));
    }

    @PostMapping("/menu")
    public ApiResponse<PlaceResponse> addMenuGroup(@RequestBody MenuGroupListRequest request) {
        System.out.println(request.toString());
        log.info("place id:{} menu insert. menu amount is {}", request.placeId(), request.menuGroups().size());
        Place place = placeService.addMenuGroups(request.placeId(), request.toGroups());

        return ApiResponse.success(new PlaceResponse(place));
    }

    @GetMapping("/category")
    public ApiResponse<CategoryGroupListResponse> getCategories(@Language String language) {
        List<CategoryWithCount> categories = placeService.findCategoryGroupsWithCounts();

        return ApiResponse.success(CategoryGroupListResponse.of(categories, language));
    }
}
