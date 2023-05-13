package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.buildings.buildingTypes.BuildingType;

public class Church extends Building {
    public Church(BuildingType type, Government government) {
        super(type, government);
    }
}
