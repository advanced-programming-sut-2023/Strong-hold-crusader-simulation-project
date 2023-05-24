package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.buildings.buildingTypes.BuildingType;
import org.example.model.buildings.buildingTypes.WallType;

public class Wall extends Building{

    private final WallType exactType;

    public Wall(BuildingType type, Government government, WallType exactType) {
        super(type, government);
        this.exactType = exactType;
    }

    @Override
    public void work() {

    }
}
