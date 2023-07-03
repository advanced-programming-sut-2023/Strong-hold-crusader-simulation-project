package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.buildings.buildingTypes.BuildingType;
import org.example.model.buildings.buildingTypes.StorageType;

public class StorageBuilding extends Building {
    private final StorageType exactType;
    private final int maxCapacity = 10;
    private int leftCapacity;
    public StorageBuilding(BuildingType type, Government government, StorageType exactType) {
        super(type, government);
        this.exactType = exactType;
        leftCapacity = maxCapacity;
    }

    public StorageType getExactType() {
        return exactType;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getLeftCapacity() {
        return leftCapacity;
    }

    public void setLeftCapacity(int leftCapacity) {
        this.leftCapacity = leftCapacity;
    }

    @Override
    public void work() {

    }
}
