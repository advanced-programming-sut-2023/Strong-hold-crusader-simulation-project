package org.example.model.buildings;

import org.example.model.Resources;

public enum BuildingType{
    //Castle Building Type
    SMALL_STONE_GATEHOUSE("small stone gatehouse", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    BIG_STONE_GATEHOUSE("big stone gatehouse", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    DRAWBRIDGE("drawbridge", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    LOOKOUT_TOWER("lookout tower", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    PERIMETER_TOWER("perimeter tower", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    DEFENCE_TURRET("defence turret", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    SQUARE_TOWER("square tower", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    ROUND_TOWER("round tower", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    ARMOURY("armoury", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    BARRACKS("barracks", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    MERCENARY_BARRACKS("mercenary barracks", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    ENGINEER_GUILD("engineer guild", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    KILLING_PIT("killing pit", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    OIL_SMELTER("oil smelter", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    PITCH_DITCH("pitch ditch", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    CAGED_WAR_DOGS("caged war dogs", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    SIEGE_TENT("siege tent", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    STABLE("stable", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    //City Structure Types
    HOVEL("hovel", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    CHURCH("church", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    CATHEDRAL("cathedral", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    //Farm Types
    APPLE_ORCHARD("apple orchard", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    DAIRY_FARM("dairy farm", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    WHEAT_FARM("wheat farm", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    HOP_FARM("hop farm", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    HUNTERS_POST("hunters post", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    //Food Processing Type
    INN("inn", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    MILL("mill", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    BAKERY("bakery", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    BREWERY("brewery", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    GRANARY("granary", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    //Industrial Types
    IRON_MINE("iron mine", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    MARKET("market", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    OX_TETHER("ox tether", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    PITCH_RIG("pitch rig", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    QUARRY("quarry", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    STOCKPILE("stockpile", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    WOODCUTTER("woodcutter", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    //Weapon Types;
    ARMOURER("armourer", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    BLACKSMITH("blacksmith", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    FLETCHER("fletcher", 0, new Resources[]{Resources.ALE}, new int[]{0}),
    POLETURNER("poleturner", 0, new Resources[]{Resources.ALE}, new int[]{0});
    private final String buildingName;
    private final int HP;
    private final Resources[] resourceNeeded;
    private final int[] resourceNeededCount;
    BuildingType(String buildingName, int HP, Resources[] resourceNeeded, int[] resourceNeededCount) {
        this.buildingName = buildingName;
        this.HP = HP;
        this.resourceNeeded = resourceNeeded;
        this.resourceNeededCount = resourceNeededCount;
    }
    public static BuildingType getTypeByName(String buildingName) {
        for (BuildingType type : BuildingType.values()) {
            if (type.buildingName.equals(buildingName))
                return type;
        }
        return null;
    }
}
