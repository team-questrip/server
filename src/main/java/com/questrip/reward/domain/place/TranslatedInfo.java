package com.questrip.reward.domain.place;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Getter
@NoArgsConstructor
public class TranslatedInfo {
    private String placeName;
    private String primaryType;
    private String formattedAddress;
    private PlaceContent content;
    private List<String> openingHours;
    private Set<MenuGroup> menuGroups;

    public void setMenuGroups(Set<MenuGroup> menuGroups) {
        this.menuGroups = menuGroups;
    }

    public void addMenuGroup(MenuGroup group) {
        if (ObjectUtils.isEmpty(group)) {
            menuGroups = new HashSet<>();
        }

        findMenuGroup(group.getGroupName()).ifPresentOrElse(
                findGroup -> findGroup.addMenu(group.getMenus()),
                () -> menuGroups.add(group)
        );
    }

    private Optional<MenuGroup> findMenuGroup(String groupName) {
        return menuGroups.stream()
                .filter(g -> g.getGroupName().equals(groupName))
                .findFirst();
    }

    @Builder
    public TranslatedInfo(String placeName, String primaryType, String formattedAddress, PlaceContent content, List<String> openingHours, Set<MenuGroup> menuGroups) {
        this.placeName = placeName;
        this.primaryType = primaryType;
        this.formattedAddress = formattedAddress;
        this.content = content;
        this.openingHours = openingHours;
        this.menuGroups = menuGroups == null ? new HashSet<>() : menuGroups;
    }
}