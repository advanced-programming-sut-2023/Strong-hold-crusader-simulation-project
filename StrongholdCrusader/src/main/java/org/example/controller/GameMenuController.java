package org.example.controller;

import org.example.model.Map;
import org.example.model.buildings.BuildingType;
import org.example.view.commands.GameMenuCommands;

import java.util.regex.Matcher;

public class GameMenuController extends Controller {
    private static Map map;
    public static Map getMap() {
        return map;
    }
    public static void setMap(Map map) {
        GameMenuController.map = map;
    }

    public static String setTexture(Matcher matcher){
        return null;
    }
    public static String clear(Matcher matcher){
        return null;
        //maybe void
    }
    public static String dropRock(Matcher matcher){
        return null;
    }
    public static String dropTree(Matcher matcher){
        return null;
    }
    public static String dropBuilding(String input){
        Matcher matcher;
        if ((matcher = (GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_X))) == null)
            return "Enter the coordinates!";
        int x = Integer.parseInt(matcher.group("mapX"));
        if (x > map.getSize() || x < 1)
            return "Invalid x";
        if ((matcher = (GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_Y))) == null)
            return "Enter the coordinates!";
        int y = Integer.parseInt(matcher.group("mapY"));
        if (y > map.getSize() || y < 1)
            return "Invalid y";
        if ((matcher = (GameMenuCommands.getMatcher(input, GameMenuCommands.TYPE))) == null)
            return "Enter building type";
        BuildingType type = BuildingType.getTypeByName(matcher.group("type"));
        if (type == null)
            return "Invalid building type!";
        //TODO the fucking rest
    }
    public static String selectBuilding(Matcher matcher){
        return null;
    }
    public static String createUnit(Matcher matcher) {
        return null;
    }
    public static String repairBuilding() {
        return null;
    }
    public static String dropUnit(Matcher matcher){
        return null;
    }
    public static String selectUnit(Matcher matcher){
        return null;
    }
    public static String moveUnitTo(Matcher matcher){
        return null;
    }
    public static String PatrolUnit(Matcher matcher){
        return null;
    }
    public static void StopPatrolUnit(){

    }
    public static String setUnitState(Matcher matcher){
        return null;
    }
    public static String Attack(Matcher matcher){
        return null;
    }
    public static String pourOil(Matcher matcher){
        return null;
    }
    public static String digTunnel(int x , int y){
        return null;
    }
    public static String  buildEquipment(Matcher matcher){
        return null;
    }
    public static String disbandUnit(){
        return null;
    }
    public static String nextTurn() {
        return null;
    }
}
