package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.buildings.buildingTypes.BuildingType;
import org.example.model.buildings.buildingTypes.ChurchType;

public class Church extends Building {
    private final ChurchType exactType;
    public Church(BuildingType type, Government government, ChurchType exactType) {
        super(type, government);
        this.exactType = exactType;
    }

    @Override
    public void work() {

    }
}
