package com.questrip.reward.domain.place;

import com.questrip.reward.domain.direction.DirectionSearcher;
import com.questrip.reward.domain.direction.DirectionSummary;
import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceAppender placeAppender;
    private final PlaceSearcher placeSearcher;
    private final PlaceImageUploader placeImageUploader;
    private final PlaceFinder placeFinder;
    private final DirectionSearcher directionSearcher;

    public Place save(String googlePlaceId, PlaceContent content, List<MultipartFile> files) {
        List<PlaceImage> images = placeImageUploader.upload(files);
        Place searched = placeSearcher.searchPlace(googlePlaceId).toPlace(content, images);
        return placeAppender.append(searched);
    }

    public PlaceAndDirection findPlaceWithDirectionSummary(String id, LatLng userLocation) {
        Place place = placeFinder.findById(id);
        DirectionSummary summary = directionSearcher.getSummary(userLocation, place.getGooglePlaceId());

        return new PlaceAndDirection(place, summary);
    }

    public SliceResult<Place> findAllPlaceNear(LatLng userLocation, int page, int size) {
        return placeFinder.findAllNear(userLocation, page, size);
    }
}
