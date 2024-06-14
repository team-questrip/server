package com.questrip.reward.domain.place;

import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
public class MenuGroup {
    private String groupName;
    private Set<Menu> menus;

    public MenuGroup(String groupName, Set<Menu> menus) {
        this.groupName = groupName;
        this.menus = menus;
    }

    public void addMenu(Menu menu) {
        if(ObjectUtils.isEmpty(menus)) {
            this.menus = new HashSet<>();
        }

        this.menus.add(menu);
    }

    public void addMenu(Set<Menu> menus) {
        if(ObjectUtils.isEmpty(menus)) {
            this.menus = new HashSet<>();
        }

        this.menus.addAll(menus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuGroup menuGroup)) return false;
        return Objects.equals(getGroupName(), menuGroup.getGroupName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupName());
    }
}
