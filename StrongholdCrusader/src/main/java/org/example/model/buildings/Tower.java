package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.buildings.buildingTypes.BuildingType;
import org.example.model.buildings.buildingTypes.TowerType;

public class Tower extends Building {

    private final TowerType exactType;

    public Tower(BuildingType type, Government government, TowerType exactType) {
        super(type, government);
        this.exactType = exactType;
    }

    @Override
    public void work() {

    }
}
