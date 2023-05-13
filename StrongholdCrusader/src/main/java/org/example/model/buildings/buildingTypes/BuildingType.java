package org.example.model.buildings.buildingTypes;

import org.example.model.Resources;
import org.example.model.Texture;

public enum BuildingType{
    //Castle Building Type
    LOW_WALL("low wall", 40, Resources.STONE, 1, 0, 0, null, true),
    STONE_WALL("low wall", 80, Resources.STONE, 2, 0, 0, null, true),
    SMALL_STONE_GATEHOUSE("small stone gatehouse", 100, null, 0, 0, 0, null, true),
    BIG_STONE_GATEHOUSE("big stone gatehouse", 110, Resources.STONE, 20, 0, 0, null, true),
    DRAWBRIDGE("drawbridge", 100, Resources.WOOD, 10, 0, 0, null, true),//
    LOOKOUT_TOWER("lookout tower", 100, Resources.STONE, 10, 0, 0, null, true),
    PERIMETER_TOWER("perimeter tower", 100, Resources.STONE, 10, 0, 0, null, true),
    DEFENCE_TURRET("defence turret", 100, Resources.STONE, 15, 0, 0, null, true),
    SQUARE_TOWER("square tower", 100, Resources.STONE, 35, 0, 0, null, true),
    ROUND_TOWER("round tower", 100, Resources.STONE, 40, 0, 0, null, true),
    ARMOURY("armoury", 100, Resources.WOOD, 5, 0, 0, null, true),
    BARRACKS("barracks", 100, Resources.STONE, 15, 0, 0, null, true),
    MERCENARY_BARRACKS("mercenary barracks", 100, Resources.WOOD, 10, 0, 0, null, true),
    ENGINEER_GUILD("engineer guild", 100, Resources.WOOD, 10, 100, 0, null, true),
    KILLING_PIT("killing pit", 100, Resources.WOOD, 6, 0, 0, null, true),//
    OIL_SMELTER("oil smelter", 100, Resources.IRON, 10, 100, 0, null, true),//
    CAGED_WAR_DOGS("caged war dogs", 100, Resources.WOOD, 10, 100, 0, null, true),//
    SIEGE_TENT("siege tent", 100, null, 0, 0, 0, null, true),//
    STABLE("stable", 100, Resources.WOOD, 20, 400, 0, null, true),//
    //City Structure Types
    HOVEL("hovel", 100, Resources.WOOD, 6, 0, 0, null, false),//
    CHURCH("church", 100, null, 0, 250, 0, null, false),
    CATHEDRAL("cathedral", 120, null, 0, 1000, 0, null, false),
    //Farm Types
    APPLE_ORCHARD("apple orchard", 100, Resources.WOOD, 5, 0, 1, Texture.GRASS, false),
    DAIRY_FARM("dairy farm", 100, Resources.WOOD, 10, 0, 1, Texture.GRASS, false),
    WHEAT_FARM("wheat farm", 100, Resources.WOOD, 15, 0, 1, Texture.GRASS, false),
    HOP_FARM("hop farm", 100, Resources.WOOD, 15, 0, 1, Texture.GRASS, false),
    HUNTERS_POST("hunters post", 100, Resources.WOOD, 5, 0, 1, null, false),
    //Food Processing Type
    INN("inn", 100, Resources.WOOD, 20, 100, 1, null, false),
    MILL("mill", 100, Resources.WOOD, 20, 0, 3, null, false),
    BAKERY("bakery", 100, Resources.WOOD, 10, 0, 1, null, false),
    BREWERY("brewery", 100, Resources.WOOD, 10, 0, 1, null, false),
    GRANARY("granary", 100, Resources.WOOD, 5, 0, 0, null, false),
    //Industrial Types
    IRON_MINE("iron mine", 100, Resources.WOOD, 20, 0, 2, Texture.IRON, false),
    MARKET("market", 100, Resources.WOOD, 5, 0, 1, null, false),//
    OX_TETHER("ox tether", 100, Resources.WOOD, 5, 0, 1, null, false),//
    PITCH_RIG("pitch rig", 100, Resources.WOOD, 20, 0, 1, Texture.OIL, false),
    QUARRY("quarry", 100, Resources.WOOD, 20, 0, 3, Texture.STONE, false),
    STOCKPILE("stockpile", 100, null, 0, 0, 0, null, false),
    WOODCUTTER("woodcutter", 100, Resources.WOOD, 3, 0, 1, null, false),
    //Weapon Types;
    ARMOURER("armourer", 100, Resources.WOOD, 20, 100, 1, null, false),
    BLACKSMITH("blacksmith", 100, Resources.WOOD, 20, 100, 1, null, false),
    FLETCHER("fletcher", 100, Resources.WOOD, 20, 100, 1, null, false),
    POLETURNER("poleturner", 100, Resources.WOOD, 10, 100, 1, null, false);
    private final String buildingName;
    private final int HP;
    private final Resources resourceNeeded;
    private final int resourceCost;
    private final int goldCost;
    private final int workerCount;
    private final Texture possibleDrop;
    private final boolean repairable;
    BuildingType(String buildingName, int HP, Resources resourceNeeded, int resourceCost, int goldCost, int workerCount, Texture possibleDrop, boolean repairable) {
        this.buildingName = buildingName;
        this.HP = HP;
        this.resourceNeeded = resourceNeeded;
        this.resourceCost = resourceCost;
        this.goldCost = goldCost;
        this.workerCount = workerCount;
        this.possibleDrop = possibleDrop;
        this.repairable = repairable;
    }
    public static BuildingType getTypeByName(String buildingName) {
        for (BuildingType type : BuildingType.values()) {
            if (type.buildingName.equals(buildingName))
                return type;
        }
        return null;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public int getHP() {
        return HP;
    }

    public Resources getResourceNeeded() {
        return resourceNeeded;
    }

    public int getResourceCost() {
        return resourceCost;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public Texture getPossibleDrop() {
        return possibleDrop;
    }

    public boolean isRepairable() {
        return repairable;
    }
}
