package com.questrip.reward.domain.place;

import com.questrip.reward.support.response.SliceResult;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository {
    Place save(Place place);

    Place findById(String id);

    SliceResult<Place> findAllNear(LatLng userLocation, int page, int size);
}
