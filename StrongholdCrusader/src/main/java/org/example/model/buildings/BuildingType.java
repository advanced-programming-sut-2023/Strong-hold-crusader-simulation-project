package org.example.model.buildings;

public enum BuildingType{
    //Castle Building Type
    SMALL_STONE_GATEHOUSE,
    BIG_STONE_GATEHOUSE,
    DRAWBRIDGE,
    LOOKOUT_TOWER,
    PERIMETER_TOWER,
    DEFENCE_TURRET,
    SQUARE_TOWER,
    ROUND_TOWER,
    ARMOURY,
    BARRACK,
    MERCENARY_BARRACK,
    ENGINEER_GUILD(),
    KILLING_PIT,
    OIL_SMELTER,
    PITCH_DITCH,
    CAGED_WAR_DOGS,
    SIEGE_TENT,
    STABLE,
    //City Structure Types
    HOVEL,
    CHURCH,
    CATHEDRAL,
    //Farm Types
    APPLE_ORCHARD,
    DAIRY_FARM,
    WHEAT_FARM,
    HOP_FARM,
    HUNTERS_POST,
    //Food Processing Type
    INN,
    MILL,
    BAKERY,
    BREWERY,
    GRANARY,
    //Industrial Types
    IRON_MINE,
    MARKET,
    OX_TETHER,
    PITCH_RIG,
    QUARRY,
    STOCKPILE,
    WOODCUTTER,
    //Weapon Types;
    ARMOURER,
    BLACKSMITH,
    FLETCHER,
    POLETURNER;
    private String buildingName;

    BuildingType(String buildingName) {
        this.buildingName = buildingName;
    }

    public static BuildingType getTypeByName(String buildingName) {
        for (BuildingType type :
                BuildingType.values()) {
            if (type.buildingName.equals(buildingName))
                return type;
        }
        return null;
    }
}
