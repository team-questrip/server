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
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceAppender placeAppender;
    private final PlaceSearcher placeSearcher;
    private final PlaceImageUploader placeImageUploader;
    private final PlaceFinder placeFinder;
    private final PlaceUpdater placeUpdater;
    private final PlaceTranslator placeTranslator;
    private final DirectionSearcher directionSearcher;

    public Place save(String googlePlaceId, String romanizedPlaceName, Category category, PlaceContent content, List<MultipartFile> files, String createdBy) {
        List<PlaceImage> images = placeImageUploader.upload(files, createdBy);
        Place searched = placeSearcher.searchPlace(googlePlaceId, content, category, romanizedPlaceName, images);
        Place appended = placeAppender.append(searched);
        placeTranslator.translateAllLanguages(appended);

        return appended;
    }

    public Place saveCrawlingContents(String googlePlaceId, Category category, String romanizedPlaceName, PlaceContent content, List<PlaceImage> images) {
        List<PlaceImage> placeImages = placeImageUploader.upload(images);
        Place searched = placeSearcher.searchPlace(googlePlaceId, content, category, romanizedPlaceName, placeImages);
        Place appended = placeAppender.append(searched);
        placeTranslator.translateAllLanguages(appended);

        return appended;
    }

    public PlaceAndDirection findPlaceWithDirectionSummary(String id, LatLng userLocation, String language) {
        Place place = placeFinder.findByIdWithLanguage(id, language);
        DirectionSummary summary = directionSearcher.getSummary(userLocation, place.getGooglePlaceId());

        return new PlaceAndDirection(place, summary);
    }

    public SliceResult<Place> findAllPlaceNear(LatLng userLocation, CategoryGroup category, int page, int size, String language) {
        log.info("[findAllPlaceNear] request userLocation lat: {}, lng: {}", userLocation.getLatitude(), userLocation.getLongitude());
        log.info("[findAllPlaceNear] request category: {}", category);

        return placeFinder.findAllNear(language, category, userLocation, page, size);
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
        placeUpdater.update(place);

        Set<MenuGroup> set = menuGroups.stream().collect(Collectors.toSet());
        placeTranslator.addTranslateMenuAllLanguages(placeId, set);

        return place;
    }

    public Set<MenuGroup> findMenuGroups(String placeId, String language) {
        Place place = placeFinder.findByIdWithLanguage(placeId, language);

        return place.getMenuGroups();
    }

    public List<CategoryGroup> findCategories() {
        return placeFinder.findCategories();
    }
}
