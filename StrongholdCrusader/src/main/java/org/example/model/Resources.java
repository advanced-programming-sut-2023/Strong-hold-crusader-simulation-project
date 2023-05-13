package org.example.model;

public enum Resources {
    APPLE("food", "apple", 8, 4),
    BREAD("food", "bread", 8, 4),
    CHEESE("food", "cheese", 8, 4),
    MEAT("food", "meat", 8, 4),

    STONE("material", "stone", 14, 7),
    WOOD("material", "wood", 4, 1),
    IRON("material", "iron", 45, 23),
    PITCH("material", "pitch", 20, 10),
    ALE("material", "ale", 20, 10),
    HOP("material", "hop", 15, 8),
    WHEAT("material", "wheat", 23, 8),
    FLOUR("material", "flour", 32, 10),

    LEATHER_ARMOUR("weapon", "leather armour", 25, 12),
    METAL_ARMOUR("weapon", "metal armour", 58, 30),
    CROSSBOW("weapon", "crossbow", 58, 30),
    PIKE("weapon", "pike", 36, 18),
    BOW("weapon", "bow", 31, 15),
    SPEAR("weapon", "spear", 20, 10),
    SWORD("weapon", "sword", 58, 30),
    MACE("weapon", "mace", 58, 30);
    private final String type;
    private final String name;
    private final int buyPrice;
    private final int sellPrice;

    Resources(String type, String name, int buyPrice, int sellPrice) {
        this.type = type;
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }
    public static Resources getResourceByName(String name) {
        for (Resources resource : Resources.values()) {
            if (resource.getName().equals(name))
                return resource;
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}
