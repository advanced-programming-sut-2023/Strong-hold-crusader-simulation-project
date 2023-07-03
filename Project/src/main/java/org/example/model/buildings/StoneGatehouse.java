package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.buildings.buildingTypes.BuildingType;
import org.example.model.buildings.buildingTypes.StoneGatehouseType;

public class StoneGatehouse extends Building {
    private final StoneGatehouseType exactType;
    public StoneGatehouse(BuildingType type, Government government, StoneGatehouseType exactType) {
        super(type, government);
        this.exactType = exactType;
    }

    @Override
    public void work() {

    }
//    private final int maximumResidents;
//    private int currentResidents;
//
//
//    public int getMaximumResidents() {
//        return maximumResidents;
//    }
//    public int getCurrentResidents() {
//        return currentResidents;
//    }
//
//    public void setCurrentResidents(int currentResidents) {
//        this.currentResidents = currentResidents;
//    }
}
