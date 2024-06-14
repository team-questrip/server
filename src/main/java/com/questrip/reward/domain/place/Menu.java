package com.questrip.reward.domain.place;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Menu {
    private String name;
    private int price;
    private String description;

    public Menu(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu menu)) return false;
        return Objects.equals(getName(), menu.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
