package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.place.Category;
import com.questrip.reward.domain.place.CategoryGroup;

import java.util.List;
import java.util.stream.Collectors;

public record CategoryGroupListResponse(List<CategoryGroupResponse> groupList) {
    public static CategoryGroupListResponse of(List<CategoryGroup> categoryGroups, String language) {
        return new CategoryGroupListResponse(
                categoryGroups.stream()
                        .map(group -> CategoryGroupResponse.of(group, language))
                        .collect(Collectors.toList())
        );
    }

    public record CategoryGroupResponse(String groupName, String enumName, List<CategoryResponse> categories) {
        public static CategoryGroupResponse of(CategoryGroup group, String language) {
            return new CategoryGroupResponse(
                    group.getTranslation(language),
                    group.name(),
                    group.getCategories().stream()
                            .map(category -> CategoryResponse.of(category, language))
                            .collect(Collectors.toList())
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