package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.place.MenuGroup;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record MenuGroupListResponse(
        List<MenuGroupResponse> menuGroups
) {
    public MenuGroupListResponse(Set<MenuGroup> menuGroups) {
        this(menuGroups.stream().map(MenuGroupResponse::new).collect(Collectors.toList()));
    }
}
