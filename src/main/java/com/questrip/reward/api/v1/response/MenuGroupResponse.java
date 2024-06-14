package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.place.MenuGroup;

import java.util.List;
import java.util.stream.Collectors;

public record MenuGroupResponse(
        String groupName,
        List<MenuResponse> menus
) {
    public MenuGroupResponse(MenuGroup group) {
        this(group.getGroupName(),
                group.getMenus()
                        .stream()
                        .map(MenuResponse::new)
                        .collect(Collectors.toList())
        );
    }
}
