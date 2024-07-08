package com.questrip.reward.domain.place;

import java.util.*;

public class PlaceTranslationUtil {
    public static List<String> createPlaceTranslationRequest(Place place) {
        List<String> request = new ArrayList<>(List.of(
                place.getPlaceName(),
                place.getPrimaryType(),
                place.getFormattedAddress(),
                place.getContent().getRecommendationReason(),
                place.getContent().getActivity()
        ));
        request.addAll(place.getOpeningHours());
        return request;
    }

    public static TranslatedInfo createTranslatedPlaceInfo(List<String> translatedTexts) {
        int basicInfoCount = 5;
        List<String> translatedOpeningHours = translatedTexts.subList(basicInfoCount, translatedTexts.size());

        return TranslatedInfo.builder()
                .placeName(translatedTexts.get(0))
                .primaryType(translatedTexts.get(1))
                .formattedAddress(translatedTexts.get(2))
                .content(new PlaceContent(translatedTexts.get(3), translatedTexts.get(4)))
                .openingHours(translatedOpeningHours)
                .build();
    }

    public static List<String> createMenuGroupTranslationRequest(Set<MenuGroup> menuGroups) {
        List<String> request = new ArrayList<>();
        for (MenuGroup group : menuGroups) {
            request.add(group.getGroupName());
            for (Menu menu : group.getMenus()) {
                request.add(menu.getName());
                request.add(menu.getDescription());
            }
        }
        return request;
    }

    public static Set<MenuGroup> createTranslatedMenuGroups(Set<MenuGroup> originalMenuGroups, List<String> translatedTexts) {
        Set<MenuGroup> translatedMenuGroups = new HashSet<>();
        Iterator<String> textIterator = translatedTexts.iterator();

        for (MenuGroup originalGroup : originalMenuGroups) {
            String translatedGroupName = textIterator.next();
            Set<Menu> translatedMenus = new HashSet<>();

            for (Menu originalMenu : originalGroup.getMenus()) {
                String translatedName = textIterator.next();
                String translatedDescription = textIterator.next();
                translatedMenus.add(new Menu(translatedName, originalMenu.getPrice(), translatedDescription));
            }

            translatedMenuGroups.add(new MenuGroup(translatedGroupName, translatedMenus));
        }

        return translatedMenuGroups;
    }
}