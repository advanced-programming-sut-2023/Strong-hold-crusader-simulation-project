package org.example.model.buildings;

import org.example.model.Government;

public class CityStructure extends Building{
    private final CityStructureTypes type;
    public CityStructure(CityStructureTypes type, Government government, int HP) {
        super(government, HP);
        this.type = type;
    }

    public CityStructureTypes getType() {
        return type;
    }
}
