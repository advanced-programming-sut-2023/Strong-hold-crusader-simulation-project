package org.example.model.buildings.buildingTypes;

public enum ChurchType {
    CHURCH(BuildingType.CHURCH),
    CATHEDRAL(BuildingType.CATHEDRAL);
    private final BuildingType type;
    ChurchType(BuildingType type) {
        this.type = type;
    }

    public BuildingType getType() {
        return type;
    }
}
