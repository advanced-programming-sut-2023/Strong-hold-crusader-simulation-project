package org.example.model;

public enum Resources {
    APPLE("food"),
    BREAD("food"),
    CHEESE("food"),
    MEAT("food"),
    STONE(""),
    WOOD(""),
    IRON(""),
    GOLD(""),
    ALE(""),
    HOP(""),
    WHEAT("pre_food"),
    FLOUR("pre_food"),
    PITCH(""),
    LEATHER(""),
    HORSE(""),
    BOW(""),
    SPEAR(""),
    SWORD(""),
    MACE(""),
    ARMOUR("");
    private final String text;
    public String getText() {
        return text;
    }
    Resources(String regex) {
        this.text = regex;
    }
}
