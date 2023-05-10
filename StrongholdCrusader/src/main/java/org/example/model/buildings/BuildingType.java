package org.example.model.buildings;

import org.example.model.Resources;

public enum BuildingType{
    //Castle Building Type
    SMALL_STONE_GATEHOUSE("small stone gatehouse", 100, null, 0, 0),
    BIG_STONE_GATEHOUSE("big stone gatehouse", 110, Resources.STONE, 20, 0),
    DRAWBRIDGE("drawbridge", 100, Resources.WOOD, 10, 0),
    LOOKOUT_TOWER("lookout tower", 100, Resources.STONE, 10, 0),
    PERIMETER_TOWER("perimeter tower", 100, Resources.STONE, 10, 0),
    DEFENCE_TURRET("defence turret", 100, Resources.STONE, 15, 0),
    SQUARE_TOWER("square tower", 100, Resources.STONE, 35, 0),
    ROUND_TOWER("round tower", 100, Resources.STONE, 40, 0),
    ARMOURY("armoury", 100, Resources.WOOD, 5, 0),
    BARRACKS("barracks", 100, Resources.STONE, 15, 0),
    MERCENARY_BARRACKS("mercenary barracks", 100, Resources.WOOD, 10, 0),
    ENGINEER_GUILD("engineer guild", 100, Resources.WOOD, 10, 100),
    KILLING_PIT("killing pit", 100, Resources.WOOD, 6, 0),
    OIL_SMELTER("oil smelter", 100, Resources.IRON, 10, 100),
    CAGED_WAR_DOGS("caged war dogs", 100, Resources.WOOD, 10, 100),
    SIEGE_TENT("siege tent", 100, null, 0, 0),
    STABLE("stable", 100, Resources.WOOD, 20, 400),
    //City Structure Types
    HOVEL("hovel", 100, Resources.WOOD, 6, 0),
    CHURCH("church", 100, null, 0, 250),
    CATHEDRAL("cathedral", 120, null, 0, 1000),
    //Farm Types
    APPLE_ORCHARD("apple orchard", 100, Resources.WOOD, 5, 0),
    DAIRY_FARM("dairy farm", 100, Resources.WOOD, 10, 0),
    WHEAT_FARM("wheat farm", 100, Resources.WOOD, 15, 0),
    HOP_FARM("hop farm", 100, Resources.WOOD, 15, 0),
    HUNTERS_POST("hunters post", 100, Resources.WOOD, 5, 0),
    //Food Processing Type
    INN("inn", 100, Resources.WOOD, 20, 100),
    MILL("mill", 100, Resources.WOOD, 20, 0),
    BAKERY("bakery", 100, Resources.WOOD, 10, 0),
    BREWERY("brewery", 100, Resources.WOOD, 10, 0),
    GRANARY("granary", 100, Resources.WOOD, 5, 0),
    //Industrial Types
    IRON_MINE("iron mine", 100, Resources.WOOD, 20, 0),
    MARKET("market", 100, Resources.WOOD, 5, 0),
    OX_TETHER("ox tether", 100, Resources.WOOD, 5, 0),
    PITCH_RIG("pitch rig", 100, Resources.WOOD, 20, 0),
    QUARRY("quarry", 100, Resources.WOOD, 20, 0),
    STOCKPILE("stockpile", 100, null, 0, 0),
    WOODCUTTER("woodcutter", 100, Resources.WOOD, 3, 0),
    //Weapon Types;
    ARMOURER("armourer", 100, Resources.WOOD, 20, 100),
    BLACKSMITH("blacksmith", 100, Resources.WOOD, 20, 100),
    FLETCHER("fletcher", 100, Resources.WOOD, 20, 100),
    POLETURNER("poleturner", 100, Resources.WOOD, 10, 100);
    private final String buildingName;
    private final int HP;
    private final Resources resourceNeeded;
    private final int resourceCost;
    private final int goldCost;
    BuildingType(String buildingName, int HP, Resources resourceNeeded, int resourceCost, int goldCost) {
        this.buildingName = buildingName;
        this.HP = HP;
        this.resourceNeeded = resourceNeeded;
        this.resourceCost = resourceCost;
        this.goldCost = goldCost;
    }
    public static BuildingType getTypeByName(String buildingName) {
        for (BuildingType type : BuildingType.values()) {
            if (type.buildingName.equals(buildingName))
                return type;
        }
        return null;
    }
}
