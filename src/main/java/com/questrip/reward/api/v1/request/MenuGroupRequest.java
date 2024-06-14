package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.place.Menu;
import com.questrip.reward.domain.place.MenuGroup;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record MenuGroupRequest(
        String menuGroupName,
        List<MenuRequest> menus
) {
    public MenuGroup toMenuGroup() {
        Set<Menu> menuSet = menus.stream().map(MenuRequest::toMenu).collect(Collectors.toSet());
        return new MenuGroup(menuGroupName, menuSet);
    }
}
