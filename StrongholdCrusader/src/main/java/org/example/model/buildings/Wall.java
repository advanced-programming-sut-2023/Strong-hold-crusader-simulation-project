package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.buildings.buildingTypes.BuildingType;

public class Wall extends Building{

    public Wall(BuildingType type, Government government) {
        super(type, government);
    }
}
