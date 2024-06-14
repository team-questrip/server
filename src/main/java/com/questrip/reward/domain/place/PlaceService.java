package com.questrip.reward.domain.place;

import com.questrip.reward.domain.direction.DirectionSearcher;
import com.questrip.reward.domain.direction.DirectionSummary;
import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceAppender placeAppender;
    private final PlaceSearcher placeSearcher;
    private final PlaceImageUploader placeImageUploader;
    private final PlaceFinder placeFinder;
    private final PlaceUpdater placeUpdater;
    private final DirectionSearcher directionSearcher;

    public Place save(String googlePlaceId, PlaceContent content, List<MultipartFile> files) {
        List<PlaceImage> images = placeImageUploader.upload(files);
        Place searched = placeSearcher.searchPlace(googlePlaceId).toPlace(content, images);
        log.info("[place save] placeId : {} saved", searched.getGooglePlaceId());

        return placeAppender.append(searched);
    }

    public PlaceAndDirection findPlaceWithDirectionSummary(String id, LatLng userLocation) {
        Place place = placeFinder.findById(id);
        DirectionSummary summary = directionSearcher.getSummary(userLocation, place.getGooglePlaceId());

        return new PlaceAndDirection(place, summary);
    }

    public SliceResult<Place> findAllPlaceNear(LatLng userLocation, int page, int size) {
        log.info("[findAllPlaceNear] request userLocation lat: {}, lng: {}", userLocation.getLatitude(), userLocation.getLongitude());

        return placeFinder.findAllNear(userLocation, page, size);
    }

    public String reverseGeocode(LatLng latLng) {
        return placeSearcher.reverseGeocode(latLng).toAddress();
    }

    @Transactional
    public Place addMenuGroups(String placeId, List<MenuGroup> menuGroups) {
        Place place = placeFinder.findById(placeId);
        for(MenuGroup menuGroup : menuGroups) {
            place.addMenuGroup(menuGroup);
        }

        return placeUpdater.update(place);
    }

    public Set<MenuGroup> findMenuGroups(String placeId) {
        Place place = placeFinder.findById(placeId);

        return place.getMenuGroups();
    }
}
