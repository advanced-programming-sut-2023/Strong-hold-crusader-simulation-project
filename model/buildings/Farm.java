package model.buildings;

import model.Government;

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
