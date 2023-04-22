package controller;

import model.Map;
import model.MapCell;
import model.Resources;
import model.buildings.*;
import model.people.Unit;

public class MapMenuController extends Controller {
    private static Map map;
    public static String showDetails(int x, int y) {
        //inja havaset bashe esme resource o building o ... print koni
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
                case "Castle" -> result += "\n" + ((CastleBuilding) building).getType();
                case "City Structure" -> result += "\n" + ((CityStructure) building).getType();
                case "Farm" -> result += "\n" + ((Farm) building).getType();
                case "Food Processing" -> result += "\n" + ((FoodProcessBuilding) building).getType();
                case "Industrial" -> result += "\n" + ((IndustrialBuilding) building).getType();
                case "Weapon" -> result += "\n" + ((WeaponBuilding) building).getType();
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
