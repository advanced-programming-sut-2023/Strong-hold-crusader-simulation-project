package model.buildings;

import model.Government;

public class FoodProcessBuilding extends Building{
    private final FoodProcessBuildingTypes type;
    public FoodProcessBuilding(FoodProcessBuildingTypes type, Government government, int HP) {
        super(government, HP);
        this.type = type;
    }
}
