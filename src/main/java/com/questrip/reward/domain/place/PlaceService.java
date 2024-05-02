package com.questrip.reward.domain.place;

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

    public Place save(String googlePlaceId, PlaceContent content, List<MultipartFile> files) {
        List<PlaceImage> images = placeImageUploader.upload(files);
        Place searched = placeSearcher.searchPlace(googlePlaceId).toPlace(content, images);
        return placeAppender.append(searched);
    }
}
