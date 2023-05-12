package org.example.model.buildings.buildingTypes;

public enum TowerType {
    LOOKOUT_TOWER(BuildingType.LOOKOUT_TOWER),
    PERIMETER_TOWER(BuildingType.PERIMETER_TOWER),
    DEFENCE_TURRET(BuildingType.DEFENCE_TURRET),
    SQUARE_TOWER(BuildingType.SQUARE_TOWER),
    ROUND_TOWER(BuildingType.ROUND_TOWER);
    private final BuildingType type;
    TowerType(BuildingType type) {
        this.type = type;
    }

    public BuildingType getType() {
        return type;
    }
}
