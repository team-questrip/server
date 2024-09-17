package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.place.Category;
import com.questrip.reward.domain.place.CategoryWithCount;

import java.util.List;
import java.util.stream.Collectors;

public record CategoryGroupListResponse(List<CategoryGroupResponse> groupList) {
    public static CategoryGroupListResponse of(List<CategoryWithCount> categoryGroups, String language) {
        return new CategoryGroupListResponse(
                categoryGroups.stream()
                        .map(group -> CategoryGroupResponse.of(group, language))
                        .collect(Collectors.toList())
        );
    }

    public record CategoryGroupResponse(String groupName, String enumName, List<CategoryResponse> categories, Long placeCounts) {
        public static CategoryGroupResponse of(CategoryWithCount categoryWithCount, String language) {
            return new CategoryGroupResponse(
                    categoryWithCount.categoryGroup().getTranslation(language),
                    categoryWithCount.categoryGroup().name(),
                    categoryWithCount.categoryGroup().getCategories().stream()
                            .map(category -> CategoryResponse.of(category, language))
                            .collect(Collectors.toList()),
                    categoryWithCount.placeCount()
            );
        }
    }

    public record CategoryResponse(String category, String enumName) {
        public static CategoryResponse of(Category category, String language) {
            return new CategoryResponse(
                    category.getTranslation(language),
                    category.name()
            );
        }
    }
}