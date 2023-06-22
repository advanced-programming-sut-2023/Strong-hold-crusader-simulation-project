package org.example.model.buildings.buildingTypes;

public enum StorageType {
    STOCKPILE(BuildingType.STOCKPILE),
    ARMOURY(BuildingType.ARMOURY),
    GRANARY(BuildingType.GRANARY);
    private final BuildingType type;

    StorageType(BuildingType type) {
        this.type = type;
    }

    public BuildingType getType() {
        return type;
    }
}
