package model.buildings;

import model.Government;

public class IndustrialBuilding extends Building{
    private final IndustrialBuildingTypes type;
    public IndustrialBuilding(IndustrialBuildingTypes type, Government government, int HP) {
        super(government, HP);
        this.type = type;
    }

    public IndustrialBuildingTypes getType() {
        return type;
    }
}
