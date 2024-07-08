package com.questrip.reward.domain.place;

import com.questrip.reward.client.request.DeeplTargetRequest;
import com.questrip.reward.client.DeeplTranslateClient;
import com.questrip.reward.support.TranslateLanguage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class PlaceTranslator {
    private final DeeplTranslateClient translationClient;
    private final PlaceRepository placeRepository;

    private static final List<String> LANGUAGE_LIST = TranslateLanguage.LIST;

    @Transactional
    public void translateAllLanguages(Place place) {
        Map<String, TranslatedInfo> translatedInfoMap = new ConcurrentHashMap<>();

        List<CompletableFuture<Void>> futures = LANGUAGE_LIST.stream()
                .filter(language -> !language.equals("EN"))
                .map(language -> CompletableFuture.runAsync(() -> {
                    TranslatedInfo translatedInfo = translateAll(place, language);
                    translatedInfoMap.put(language, translatedInfo);
                }))
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRun(() -> placeRepository.addTranslateAll(place.getId(), translatedInfoMap))
                .exceptionally(e -> {
                    log.error("Error occurred while translating all languages for place {}: {}", place.getId(), e.getMessage());
                    return null;
                })
                .join();
    }

    private TranslatedInfo translateAll(Place place, String language) {
        try {
            TranslatedInfo translatedInfo = translatePlace(place, language);
            translateMenu(place, language, translatedInfo);
            return translatedInfo;
        } catch (Exception e) {
            log.error("Error translating for language {} and place {}: {}", language, place.getId(), e.getMessage());
            throw new CompletionException(e);
        }
    }

    private TranslatedInfo translatePlace(Place place, String language) {
        List<String> placeTranslateRequest = PlaceTranslationUtil.createPlaceTranslationRequest(place);
        List<String> translatedPlaceTexts = translationClient.getTranslate(new DeeplTargetRequest(placeTranslateRequest, language)).getTexts();
        return PlaceTranslationUtil.createTranslatedPlaceInfo(translatedPlaceTexts);
    }

    private void translateMenu(Place place, String language, TranslatedInfo translatedInfo) {
        if (place.getMenuGroups().isEmpty()) {
            return;
        }

        List<String> menuGroupTranslateRequest = PlaceTranslationUtil.createMenuGroupTranslationRequest(place.getMenuGroups());
        List<String> translatedMenuGroupTexts = translationClient.getTranslate(new DeeplTargetRequest(menuGroupTranslateRequest, language)).getTexts();
        Set<MenuGroup> translatedMenuGroups = PlaceTranslationUtil.createTranslatedMenuGroups(place.getMenuGroups(), translatedMenuGroupTexts);
        translatedInfo.setMenuGroups(translatedMenuGroups);
    }

    @Transactional
    public void addTranslateMenuAllLanguages(String placeId, Set<MenuGroup> originalMenuGroup) {
        Map<String, Set<MenuGroup>> translatedMenuGroups = new ConcurrentHashMap<>();

        List<CompletableFuture<Void>> futures = LANGUAGE_LIST.stream()
                .filter(language -> !language.equals("EN"))
                .map(language -> CompletableFuture.runAsync(() -> {
                    Set<MenuGroup> translated = translateMenu(originalMenuGroup, language);
                    translatedMenuGroups.put(language, translated);
                }))
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRun(() -> placeRepository.addTranslateMenuAll(placeId, translatedMenuGroups))
                .exceptionally(e -> {
                    log.error("Error occurred while translating menus for place {}: {}", placeId, e.getMessage());
                    return null;
                })
                .join();
    }

    private Set<MenuGroup> translateMenu(Set<MenuGroup> originalMenuGroup, String language) {
        try {
            List<String> menuGroupTranslateRequest = PlaceTranslationUtil.createMenuGroupTranslationRequest(originalMenuGroup);
            List<String> translatedMenuGroupTexts = translationClient.getTranslate(new DeeplTargetRequest(menuGroupTranslateRequest, language)).getTexts();
            return PlaceTranslationUtil.createTranslatedMenuGroups(originalMenuGroup, translatedMenuGroupTexts);
        } catch (Exception e) {
            log.error("Error translating menu for language {}: {}", language, e.getMessage());
            throw new CompletionException(e);
        }
    }
}