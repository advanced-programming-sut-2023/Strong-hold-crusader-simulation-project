package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.buildings.buildingTypes.BuildingType;
import org.example.model.buildings.buildingTypes.WorkerProduceType;

public class WorkerProduceBuilding extends Building {
    private final WorkerProduceType exactType;

    public WorkerProduceBuilding(BuildingType type, Government government, WorkerProduceType exactType) {
        super(type, government);
        this.exactType = exactType;
    }

    @Override
    public void work() {

    }
}
