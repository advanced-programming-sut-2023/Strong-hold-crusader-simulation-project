package model.buildings;

import model.Government;

public class WeaponBuilding extends Building{
    private final WeaponBuildingTypes type;
    public WeaponBuilding(WeaponBuildingTypes type, Government government, int HP) {
        super(government, HP);
        this.type = type;
    }

    public WeaponBuildingTypes getType() {
        return type;
    }
}
