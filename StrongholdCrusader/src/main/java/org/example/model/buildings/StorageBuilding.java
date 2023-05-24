package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.buildings.buildingTypes.BuildingType;
import org.example.model.buildings.buildingTypes.StorageType;

public class StorageBuilding extends Building {
    private final StorageType exactType;

    public StorageBuilding(BuildingType type, Government government, StorageType exactType) {
        super(type, government);
        this.exactType = exactType;
    }

    @Override
    public void work() {

    }
}
