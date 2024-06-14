package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.place.Menu;

import java.util.Set;

public record MenuRequest(
        String menuName,
        int price,
        String description
) {
    public Menu toMenu() {
        return new Menu(menuName, price, description);
    }
}
