package com.questrip.reward.batch.domain;

import com.questrip.reward.domain.place.Menu;
import com.questrip.reward.domain.place.MenuGroup;
import lombok.Getter;

import java.util.HashSet;

@Getter
public class MenuDto {
    private String placeId;
    private String menuGroup;
    private String menuName;
    private String romanizedMenuName;
    private int price;
    private String description;

    public MenuDto(String placeId, String menuGroup, String menuName, String romanizedMenuName, int price, String description) {
        this.placeId = placeId;
        this.menuGroup = menuGroup;
        this.menuName = menuName;
        this.romanizedMenuName = romanizedMenuName;
        this.price = price;
        this.description = description;
    }

    public MenuGroup toGroups() {
        Menu menu = new Menu(menuName, romanizedMenuName, price, description);
        HashSet<Menu> menus = new HashSet<>();
        menus.add(menu);
        return new MenuGroup(menuGroup, menus);
    }
}
