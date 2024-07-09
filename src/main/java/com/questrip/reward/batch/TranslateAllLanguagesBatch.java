//package com.questrip.reward.batch;
//
//import com.questrip.reward.domain.place.Place;
//import com.questrip.reward.domain.place.PlaceRepository;
//import com.questrip.reward.domain.place.PlaceTranslator;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Component
//@Slf4j
//public class TranslateAllLanguagesBatch implements CommandLineRunner {
//
//    private final PlaceRepository placeRepository;
//    private final PlaceTranslator placeTranslator;
//
//    public TranslateAllLanguagesBatch(PlaceRepository placeRepository, PlaceTranslator placeTranslator) {
//        this.placeRepository = placeRepository;
//        this.placeTranslator = placeTranslator;
//    }
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//        log.info("Starting TranslateAllLanguagesBatch");
//
//        List<Place> places = placeRepository.findAllByIdIn(List.of("66822d5424b6473540314543", "66822d5524b6473540314544", "66822d5524b6473540314545"));
//        int totalPlaces = places.size();
//        int processedPlaces = 0;
//
//        for (Place place : places) {
//            try {
//                placeTranslator.translateAllLanguages(place);
//                processedPlaces++;
//
//                if (processedPlaces % 100 == 0 || processedPlaces == totalPlaces) {
//                    log.info("Processed {} out of {} places", processedPlaces, totalPlaces);
//                }
//            } catch (Exception e) {
//                log.error("Error processing place with id {}: {}", place.getId(), e.getMessage());
//            }
//        }
//
//        log.info("TranslateAllLanguagesBatch completed. Processed {} places", processedPlaces);
//    }
//}