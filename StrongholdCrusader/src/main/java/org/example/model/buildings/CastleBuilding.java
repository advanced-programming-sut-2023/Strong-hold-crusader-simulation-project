package StrongholdCrusader.src.main.java.org.example.model.buildings;

import StrongholdCrusader.src.main.java.org.example.model.Government;

public class CastleBuilding extends Building{
    private final CastleBuildingTypes type;
    public CastleBuilding(CastleBuildingTypes type, Government government, int HP) {
        super(government, HP);
        this.type = type;
    }

    public CastleBuildingTypes getType() {
        return type;
    }
}
