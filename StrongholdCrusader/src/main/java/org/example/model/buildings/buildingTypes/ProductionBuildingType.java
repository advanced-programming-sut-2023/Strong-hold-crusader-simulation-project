package org.example.model.buildings.buildingTypes;

import org.example.model.Resources;

public enum ProductionBuildingType {
    INN(BuildingType.INN, null, Resources.ALE),
    MILL(BuildingType.MILL, null, Resources.ALE),
    IRON_MINE(BuildingType.IRON_MINE, null, Resources.ALE),
    QUARRY(BuildingType.QUARRY, null, Resources.ALE),
    WOODCUTTER(BuildingType.WOODCUTTER, null, Resources.ALE),
    PITCH_RIG(BuildingType.PITCH_RIG, null, Resources.ALE),
    ARMOURER(BuildingType.ARMOURER, null, Resources.ALE),
    BLACKSMITH(BuildingType.BLACKSMITH, null, Resources.ALE),
    FLETCHER(BuildingType.FLETCHER, null, Resources.ALE),
    POLETURNER(BuildingType.POLETURNER, null, Resources.ALE),
    OIL_SMELTER(BuildingType.OIL_SMELTER, null, Resources.ALE),
    APPLE_ORCHARD(BuildingType.APPLE_ORCHARD, null, Resources.ALE),
    DAIRY_FARM(BuildingType.DAIRY_FARM, null, Resources.ALE),
    WHEAT_FARM(BuildingType.WHEAT_FARM, null, Resources.ALE),
    HOP_FARM(BuildingType.HOP_FARM, null, Resources.ALE),
    BAKERY(BuildingType.BAKERY, null, Resources.ALE),
    BREWERY(BuildingType.BREWERY, null, Resources.ALE);
    private final BuildingType type;
    private final Resources neededResource;
    private final Resources producedResource;

    ProductionBuildingType(BuildingType type, Resources neededResource, Resources producedResource) {
        this.type = type;
        this.neededResource = neededResource;
        this.producedResource = producedResource;
    }

    public BuildingType getType() {
        return type;
    }

    public Resources getNeededResource() {
        return neededResource;
    }

    public Resources getProducedResource() {
        return producedResource;
    }
}
