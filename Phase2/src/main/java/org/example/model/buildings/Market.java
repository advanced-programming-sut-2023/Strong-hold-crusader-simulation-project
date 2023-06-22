package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.buildings.buildingTypes.BuildingType;

public class Market extends Building{
    public Market(BuildingType type, Government government) {
        super(type, government);
    }

    @Override
    public void work() {

    }
}
