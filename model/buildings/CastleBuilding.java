package model.buildings;

import model.Government;

public class CastleBuilding extends Building{
    private final CastleBuildingType type;
    public CastleBuilding(CastleBuildingType type, Government government, int HP) {
        super(government, HP);
        this.type = type;
    }
}
