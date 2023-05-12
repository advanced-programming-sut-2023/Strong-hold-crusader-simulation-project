package org.example.model.buildings.buildingTypes;

public enum WallType {
    LOW(BuildingType.LOW_WALL),
    STONE(BuildingType.STONE_WALL);
    private final BuildingType type;

    WallType(BuildingType type) {
        this.type = type;
    }

    public BuildingType getType() {
        return type;
    }
}
