package org.example.model;

import java.util.Objects;

public enum Resources {
    APPLE("apple", 8, 4),
    BREAD("bread", 8, 4),
    CHEESE("cheese", 8, 4),
    MEAT("meat", 8, 4),

    STONE("stone", 14, 7),
    WOOD("wood", 4, 1),
    IRON("iron", 45, 23),
    PITCH("pitch", 20, 10),

    ALE("ale", 20, 10),
    HOP("hop", 15, 8),
    WHEAT("wheat", 23, 8),
    FLOUR("flour", 32, 10),

    LEATHER_ARMOUR("leather armour", 25, 12),
    METAL_ARMOUR("metal armour", 58, 30),
    CROSSBOW("crossbow", 58, 30),
    PIKE("pike", 36, 18),
    BOW("bow", 31, 15),
    SPEAR("spear", 20, 10),
    SWORD("sword", 58, 30),
    MACE("mace", 58, 30),

    GOLD("gold", 1, 1);
    private final String name;
    private final int buyPrice;
    private final int sellPrice;

    Resources(String name, int buyPrice, int sellPrice) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public String getName() {
        return name;
    }

    public int getBuyPrice() {
        return buyPrice;
    }
    public static Resources getByName(String string){
        for (Resources resources: Resources.values()){
            if (Objects.equals(resources.getName(), string)){
                return resources;
            }
        }
        return null;
    }
    public int getSellPrice() {
        return sellPrice;
    }
}
