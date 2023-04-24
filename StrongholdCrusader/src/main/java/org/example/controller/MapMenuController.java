package StrongholdCrusader.src.main.java.org.example.controller;

import StrongholdCrusader.src.main.java.org.example.model.Map;
import StrongholdCrusader.src.main.java.org.example.model.MapCell;
import StrongholdCrusader.src.main.java.org.example.model.Resources;
import StrongholdCrusader.src.main.java.org.example.model.buildings.*;
import StrongholdCrusader.src.main.java.org.example.model.people.Unit;

public class MapMenuController extends Controller {
    private static Map map;
    public static String showDetails(int x, int y) {
        //TODO inja havaset bashe esme resource o building o ... print koni
        MapCell cell = map.getCells()[x - 1][y - 1];
        String result = "Texture: " + cell.getTexture().getTextureName();
        result += "\nResources:";
        for (Resources resource : cell.getResources().keySet()) {
            result += "\n" + resource + " count: " + cell.getResources().get(resource);
        }
        result += "\nTotal Unit Count: " + cell.getUnits().size();
        result += "\nUnits:";
        for (Unit unit : cell.getUnits()) {
            result += "\n" + unit.getType();
        }
        result += "\nBuildings:";
        for (Building building : cell.getBuildings()) {
            switch (building.getBuildingType()) {
                case "Castle":
                    result += "\n" + ((CastleBuilding) building).getType();
                    break;
                case "City Structure":
                    result += "\n" + ((CityStructure) building).getType();
                    break;
                case "Farm":
                    result += "\n" + ((Farm) building).getType();
                    break;
                case "Food Processing":
                    result += "\n" + ((FoodProcessBuilding) building).getType();
                    break;
                case "Industrial":
                    result += "\n" + ((IndustrialBuilding) building).getType();
                    break;
                case "Weapon":
                    result += "\n" + ((WeaponBuilding) building).getType();
                    break;
            }
        }
        return result;
    }

    public static Map getMap() {
        return map;
    }

    public static void setMap(Map map) {
        MapMenuController.map = map;
    }
}
