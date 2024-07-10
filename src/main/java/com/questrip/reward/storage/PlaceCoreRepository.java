package com.questrip.reward.storage;

import com.questrip.reward.domain.place.*;
import com.questrip.reward.storage.mongo.PlaceEntity;
import com.questrip.reward.storage.mongo.PlaceMongoRepository;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import com.questrip.reward.support.response.SliceResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PlaceCoreRepository implements PlaceRepository {

    private final PlaceMongoRepository placeMongoRepository;

    @Override
    public Place save(Place place) {
        return placeMongoRepository.save(PlaceEntity.from(place)).toPlace();
    }

    @Override
    public Place findById(String id) {
        return placeMongoRepository.findById(id)
                .orElseThrow(() -> new GlobalException(ErrorCode.NOT_EXIST_PLACE, "place id %s is not found.".formatted(id)))
                .toPlace();
    }

    @Override
    public SliceResult<Place> findAllNear(LatLng userLocation, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Slice<Place> slice = placeMongoRepository.findAllByLocationNear(toPoint(userLocation), pageRequest)
                .map(PlaceEntity::toPlace);

        return new SliceResult<>(slice);
    }

    @Override
    public SliceResult<Place> findAllNear(String language, LatLng userLocation, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Slice<Place> slice = placeMongoRepository.findAllByLocationNear(toPoint(userLocation), pageRequest)
                .map(placeEntity -> placeEntity.toPlace(language));

        return new SliceResult<>(slice);
    }

    @Override
    public SliceResult<Place> findRecommendPlace(LatLng userLocation, List<String> placeIds, int page, int size, String language) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Slice<Place> slice = placeMongoRepository.findAllByLocationNearAndIdNotIn(toPoint(userLocation), placeIds, pageRequest)
                .map(placeEntity -> placeEntity.toPlace(language));

        return new SliceResult<>(slice);
    }

    private Point toPoint(LatLng userLocation) {
        return new Point(userLocation.getLongitude(), userLocation.getLatitude());
    }

    @Override
    public List<Place> findAllByIdIn(List<String> placeIds) {
        return placeMongoRepository.findAllByIdIn(placeIds)
                .stream()
                .map(PlaceEntity::toPlace)
                .collect(Collectors.toList());
    }

    @Override
    public List<Place> findAllByIdIn(List<String> placeIds, String language) {
        return placeMongoRepository.findAllByIdIn(placeIds)
                .stream()
                .map(placeEntity -> placeEntity.toPlace(language))
                .collect(Collectors.toList());
    }

    @Override
    public Place update(Place place) {
        PlaceEntity entity = placeMongoRepository.findById(place.getId()).orElseThrow();
        entity.update(place);
        return placeMongoRepository.save(entity).toPlace();
    }

    @Override
    public void addTranslateAll(String placeId, Map<String, TranslatedInfo> translatedInfos) {
        PlaceEntity placeEntity = placeMongoRepository.findById(placeId)
                .orElseThrow(() -> new GlobalException(ErrorCode.NOT_EXIST_PLACE, "can't add translated info place id : %s".formatted(placeId)));

        placeEntity.addTranslation(translatedInfos);
        placeMongoRepository.save(placeEntity);
    }

    @Override
    @Transactional
    public void addTranslateMenuAll(String placeId, Map<String, Set<MenuGroup>> translatedMenuGroups) {
        PlaceEntity placeEntity = placeMongoRepository.findById(placeId)
                .orElseThrow(() -> new GlobalException(ErrorCode.NOT_EXIST_PLACE, "can't add translated menu for place id : %s".formatted(placeId)));

        translatedMenuGroups.forEach((language, menuGroups) -> {
            TranslatedInfo translatedInfo = placeEntity.getTranslations().computeIfAbsent(language, k -> new TranslatedInfo());
            for (MenuGroup group : menuGroups) {
                translatedInfo.addMenuGroup(group);
            }
        });

        placeMongoRepository.save(placeEntity);
    }

    @Override
    public Place findByIdWithLanguage(String id, String language) {
        return placeMongoRepository.findById(id)
                .orElseThrow(() -> new GlobalException(ErrorCode.NOT_EXIST_PLACE, "can't add translated menu for place id : %s".formatted(id)))
                .toPlace(language);
    }

    @Override
    public List<Place> findAll() {
        return placeMongoRepository.findAll()
                .stream()
                .map(PlaceEntity::toPlace)
                .collect(Collectors.toList());
    }
}