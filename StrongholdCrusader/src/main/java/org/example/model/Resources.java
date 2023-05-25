package org.example.model;

import org.example.model.buildings.buildingTypes.StorageType;

public enum Resources {
    APPLE("food", "apple", 8, 4, StorageType.GRANARY),
    BREAD("food", "bread", 8, 4, StorageType.GRANARY),
    CHEESE("food", "cheese", 8, 4, StorageType.GRANARY),
    MEAT("food", "meat", 8, 4, StorageType.GRANARY),

    STONE("material", "stone", 14, 7, StorageType.STOCKPILE),
    WOOD("material", "wood", 4, 1, StorageType.STOCKPILE),
    IRON("material", "iron", 45, 23, StorageType.STOCKPILE),
    PITCH("material", "pitch", 20, 10, StorageType.STOCKPILE),
    ALE("material", "ale", 20, 10, StorageType.STOCKPILE),
    HOP("material", "hop", 15, 8, StorageType.STOCKPILE),
    WHEAT("material", "wheat", 23, 8, StorageType.STOCKPILE),
    FLOUR("material", "flour", 32, 10, StorageType.STOCKPILE),

    LEATHER_ARMOUR("weapon", "leather armour", 25, 12, StorageType.ARMOURY),
    METAL_ARMOUR("weapon", "metal armour", 58, 30, StorageType.ARMOURY),
    CROSSBOW("weapon", "crossbow", 58, 30, StorageType.ARMOURY),
    PIKE("weapon", "pike", 36, 18, StorageType.ARMOURY),
    BOW("weapon", "bow", 31, 15, StorageType.ARMOURY),
    SPEAR("weapon", "spear", 20, 10, StorageType.ARMOURY),
    SWORD("weapon", "sword", 58, 30, StorageType.ARMOURY),
    MACE("weapon", "mace", 58, 30, StorageType.ARMOURY);
    private final String type;
    private final String name;
    private final int buyPrice;
    private final int sellPrice;
    private final StorageType storageType;

    Resources(String type, String name, int buyPrice, int sellPrice, StorageType storageType) {
        this.type = type;
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.storageType = storageType;
    }
    public static Resources getResourceByName(String name) {
        for (Resources resource : Resources.values()) {
            if (resource.getName().equals(name))
                return resource;
        }
        return null;
    }

    public StorageType getStorageType() {
        return storageType;
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
