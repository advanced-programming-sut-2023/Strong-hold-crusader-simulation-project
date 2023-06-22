package org.example.model.buildings.buildingTypes;

public enum StoneGatehouseType {
    SMALL(BuildingType.SMALL_STONE_GATEHOUSE),
    BIG(BuildingType.BIG_STONE_GATEHOUSE);
    private final BuildingType type;

    StoneGatehouseType(BuildingType type) {
        this.type = type;
    }

    public BuildingType getType() {
        return type;
    }
}
