package org.example.model.buildings.buildingTypes;

import org.example.model.Resources;

public enum ProductionBuildingType {
    INN(BuildingType.INN, Resources.ALE, null),
    MILL(BuildingType.MILL, Resources.WHEAT, Resources.FLOUR),
    IRON_MINE(BuildingType.IRON_MINE, null, Resources.IRON),
    QUARRY(BuildingType.QUARRY, null, Resources.STONE),
    WOODCUTTER(BuildingType.WOODCUTTER, null, Resources.WOOD),
    PITCH_RIG(BuildingType.PITCH_RIG, null, Resources.PITCH),
    ARMOURER(BuildingType.ARMOURER, Resources.WOOD, Resources.METAL_ARMOUR),
    BLACKSMITH(BuildingType.BLACKSMITH, Resources.WOOD, Resources.SWORD),
    FLETCHER(BuildingType.FLETCHER, Resources.WOOD, Resources.BOW),
    POLETURNER(BuildingType.POLETURNER, Resources.WOOD, Resources.SPEAR),
    OIL_SMELTER(BuildingType.OIL_SMELTER, null, null),
    APPLE_ORCHARD(BuildingType.APPLE_ORCHARD, null, Resources.APPLE),
    DAIRY_FARM(BuildingType.DAIRY_FARM, null, Resources.CHEESE),
    WHEAT_FARM(BuildingType.WHEAT_FARM, null, Resources.WHEAT),
    HOP_FARM(BuildingType.HOP_FARM, null, Resources.HOP),
    BAKERY(BuildingType.BAKERY, Resources.FLOUR, Resources.BREAD),
    BREWERY(BuildingType.BREWERY, Resources.HOP, Resources.ALE);
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
