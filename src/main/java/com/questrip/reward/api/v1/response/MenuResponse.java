package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.place.Menu;

public record MenuResponse(
        String menuName,
        int price,
        String description
) {
    public MenuResponse(Menu menu) {
        this("%s (%s)".formatted(menu.getName(), menu.getRomanizedMenuName()), menu.getPrice(), menu.getDescription());
    }
}
