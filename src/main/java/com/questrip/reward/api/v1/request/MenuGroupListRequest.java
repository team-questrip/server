package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.place.MenuGroup;

import java.util.List;
import java.util.stream.Collectors;

public record MenuGroupListRequest(String placeId, List<MenuGroupRequest> menuGroups) {
    public List<MenuGroup> toGroups() {
        return menuGroups.stream()
                .map(MenuGroupRequest::toMenuGroup)
                .collect(Collectors.toList());
    }
}
