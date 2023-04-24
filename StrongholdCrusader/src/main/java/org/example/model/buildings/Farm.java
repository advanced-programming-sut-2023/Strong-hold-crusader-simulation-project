package org.example.model.buildings;

import org.example.model.Government;

public class Farm extends Building{
    private final FarmTypes type;
    public Farm(FarmTypes type, Government government, int HP) {
        super(government, HP);
        this.type = type;
    }

    public FarmTypes getType() {
        return type;
    }
}
