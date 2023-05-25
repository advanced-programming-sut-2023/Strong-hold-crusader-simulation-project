package org.example.model.buildings.buildingTypes;

public enum TowerType {
    LOOKOUT_TOWER(BuildingType.LOOKOUT_TOWER, 10, 6),
    PERIMETER_TOWER(BuildingType.PERIMETER_TOWER, 10, 6),
    DEFENCE_TURRET(BuildingType.DEFENCE_TURRET, 10, 4),
    SQUARE_TOWER(BuildingType.SQUARE_TOWER, 10, 4),
    ROUND_TOWER(BuildingType.ROUND_TOWER, 10, 4);
    private final BuildingType type;
    private final int damage;
    private final int range;
    TowerType(BuildingType type, int damage, int range) {
        this.type = type;
        this.damage = damage;
        this.range = range;
    }
    public BuildingType getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }
}
