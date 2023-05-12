package org.example.model.buildings.buildingTypes;

import org.example.model.Resources;
import org.example.model.Texture;

public enum BuildingType{
    //Castle Building Type
    LOW_WALL("low wall", 40, Resources.STONE, 1, 0, 0, null),
    STONE_WALL("low wall", 80, Resources.STONE, 2, 0, 0, null),
    SMALL_STONE_GATEHOUSE("small stone gatehouse", 100, null, 0, 0, 0, null),
    BIG_STONE_GATEHOUSE("big stone gatehouse", 110, Resources.STONE, 20, 0, 0, null),
    DRAWBRIDGE("drawbridge", 100, Resources.WOOD, 10, 0, 0, null),//
    LOOKOUT_TOWER("lookout tower", 100, Resources.STONE, 10, 0, 0, null),
    PERIMETER_TOWER("perimeter tower", 100, Resources.STONE, 10, 0, 0, null),
    DEFENCE_TURRET("defence turret", 100, Resources.STONE, 15, 0, 0, null),
    SQUARE_TOWER("square tower", 100, Resources.STONE, 35, 0, 0, null),
    ROUND_TOWER("round tower", 100, Resources.STONE, 40, 0, 0, null),
    ARMOURY("armoury", 100, Resources.WOOD, 5, 0, 0, null),
    BARRACKS("barracks", 100, Resources.STONE, 15, 0, 0, null),
    MERCENARY_BARRACKS("mercenary barracks", 100, Resources.WOOD, 10, 0, 0, null),
    ENGINEER_GUILD("engineer guild", 100, Resources.WOOD, 10, 100, 0, null),
    KILLING_PIT("killing pit", 100, Resources.WOOD, 6, 0, 0, null),//
    OIL_SMELTER("oil smelter", 100, Resources.IRON, 10, 100, 0, null),//
    CAGED_WAR_DOGS("caged war dogs", 100, Resources.WOOD, 10, 100, 0, null),//
    SIEGE_TENT("siege tent", 100, null, 0, 0, 0, null),//
    STABLE("stable", 100, Resources.WOOD, 20, 400, 0, null),//
    //City Structure Types
    HOVEL("hovel", 100, Resources.WOOD, 6, 0, 0, null),//
    CHURCH("church", 100, null, 0, 250, 0, null),
    CATHEDRAL("cathedral", 120, null, 0, 1000, 0, null),
    //Farm Types
    APPLE_ORCHARD("apple orchard", 100, Resources.WOOD, 5, 0, 1, Texture.GRASS),
    DAIRY_FARM("dairy farm", 100, Resources.WOOD, 10, 0, 1, Texture.GRASS),
    WHEAT_FARM("wheat farm", 100, Resources.WOOD, 15, 0, 1, Texture.GRASS),
    HOP_FARM("hop farm", 100, Resources.WOOD, 15, 0, 1, Texture.GRASS),
    HUNTERS_POST("hunters post", 100, Resources.WOOD, 5, 0, 1, null),
    //Food Processing Type
    INN("inn", 100, Resources.WOOD, 20, 100, 1, null),
    MILL("mill", 100, Resources.WOOD, 20, 0, 3, null),
    BAKERY("bakery", 100, Resources.WOOD, 10, 0, 1, null),
    BREWERY("brewery", 100, Resources.WOOD, 10, 0, 1, null),
    GRANARY("granary", 100, Resources.WOOD, 5, 0, 0, null),
    //Industrial Types
    IRON_MINE("iron mine", 100, Resources.WOOD, 20, 0, 2, Texture.IRON),
    MARKET("market", 100, Resources.WOOD, 5, 0, 1, null),//
    OX_TETHER("ox tether", 100, Resources.WOOD, 5, 0, 1, null),//
    PITCH_RIG("pitch rig", 100, Resources.WOOD, 20, 0, 1, Texture.OIL),
    QUARRY("quarry", 100, Resources.WOOD, 20, 0, 3, Texture.STONE),
    STOCKPILE("stockpile", 100, null, 0, 0, 0, null),
    WOODCUTTER("woodcutter", 100, Resources.WOOD, 3, 0, 1, null),
    //Weapon Types;
    ARMOURER("armourer", 100, Resources.WOOD, 20, 100, 1, null),
    BLACKSMITH("blacksmith", 100, Resources.WOOD, 20, 100, 1, null),
    FLETCHER("fletcher", 100, Resources.WOOD, 20, 100, 1, null),
    POLETURNER("poleturner", 100, Resources.WOOD, 10, 100, 1, null);
    private final String buildingName;
    private final int HP;
    private final Resources resourceNeeded;
    private final int resourceCost;
    private final int goldCost;
    private final int workerCount;
    private final Texture possibleDrop;
    BuildingType(String buildingName, int HP, Resources resourceNeeded, int resourceCost, int goldCost, int workerCount, Texture possibleDrop) {
        this.buildingName = buildingName;
        this.HP = HP;
        this.resourceNeeded = resourceNeeded;
        this.resourceCost = resourceCost;
        this.goldCost = goldCost;
        this.workerCount = workerCount;
        this.possibleDrop = possibleDrop;
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
}
