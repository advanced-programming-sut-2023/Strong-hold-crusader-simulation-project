package org.example.model.buildings.buildingTypes;

public enum WorkerProduceType {
    BARRACKS(BuildingType.BARRACKS),
    MERCENARY_BARRACKS(BuildingType.MERCENARY_BARRACKS),
    ENGINEER_GUILD(BuildingType.ENGINEER_GUILD);
    private final BuildingType type;

    WorkerProduceType(BuildingType type) {
        this.type = type;
    }

    public BuildingType getType() {
        return type;
    }
}
