package com.questrip.reward.domain.place;

import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository {
    Place save(Place place);
}
