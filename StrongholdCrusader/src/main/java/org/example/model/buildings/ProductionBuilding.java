package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.buildings.buildingTypes.BuildingType;

public class ProductionBuilding extends Building {
    public ProductionBuilding(BuildingType type, Government government) {
        super(type, government);
    }
}
