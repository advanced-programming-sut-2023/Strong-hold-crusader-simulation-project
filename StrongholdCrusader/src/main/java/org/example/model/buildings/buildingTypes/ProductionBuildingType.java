package org.example.model.buildings.buildingTypes;

public enum ProductionBuildingType {
    INN(BuildingType.INN),
    MILL(BuildingType.MILL),
    IRON_MINE(BuildingType.IRON_MINE),
    QUARRY(BuildingType.QUARRY),
    WOODCUTTER(BuildingType.WOODCUTTER),
    PITCH_RIG(BuildingType.PITCH_RIG),
    ARMOURER(BuildingType.ARMOURER),
    BLACKSMITH(BuildingType.BLACKSMITH),
    FLETCHER(BuildingType.FLETCHER),
    POLETURNER(BuildingType.POLETURNER),
    OIL_SMELTER(BuildingType.OIL_SMELTER),
    APPLE_ORCHARD(BuildingType.APPLE_ORCHARD),
    DAIRY_FARM(BuildingType.DAIRY_FARM),
    WHEAT_FARM(BuildingType.WHEAT_FARM),
    HOP_FARM(BuildingType.HOP_FARM),
    BAKERY(BuildingType.BAKERY),
    BREWERY(BuildingType.BREWERY);
    private final BuildingType type;

    ProductionBuildingType(BuildingType type) {
        this.type = type;
    }

    public BuildingType getType() {
        return type;
    }
}
