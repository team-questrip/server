package com.questrip.reward.domain.place;

public record CategoryWithCount(
        CategoryGroup categoryGroup,
        Long placeCount
) {
}
