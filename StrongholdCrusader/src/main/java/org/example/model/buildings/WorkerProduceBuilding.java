package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.buildings.buildingTypes.BuildingType;

public class WorkerProduceBuilding extends Building {
    public WorkerProduceBuilding(BuildingType type, Government government) {
        super(type, government);
    }
}
