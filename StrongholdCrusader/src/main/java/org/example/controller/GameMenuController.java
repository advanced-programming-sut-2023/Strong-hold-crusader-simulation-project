package org.example.controller;

import org.example.model.MapCell;
import org.example.model.User;
import org.example.model.buildings.*;
import org.example.model.buildings.buildingTypes.*;
import org.example.view.MapMenu;
import org.example.view.commands.GameMenuCommands;
import org.example.view.commands.LoginMenuCommands;

import java.util.regex.Matcher;

public class GameMenuController extends Controller {

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
            return "You have to enter the coordinates!";
        int x = Integer.parseInt(matcher.group("mapX"));
        if (x > currentGame.getMap().getSize() || x < 1)
            return "Invalid x";
        if ((matcher = (GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_Y))) == null)
            return "You have to enter the coordinates!";
        int y = Integer.parseInt(matcher.group("mapY"));
        if (y > currentGame.getMap().getSize() || y < 1)
            return "Invalid y";
        if ((matcher = (GameMenuCommands.getMatcher(input, GameMenuCommands.TYPE))) == null)
            return "You have to enter the building type!";
        BuildingType type = BuildingType.getTypeByName(matcher.group("type"));
        if (type == null)
            return "Invalid building type!";
        MapCell cell = currentGame.getMap().getCells()[x - 1][y - 1];
        if (cell.getCellState() != ' ')
            return "This coordinate is not empty!";
        if (type.getPossibleDrop() != null && !cell.getTexture().equals(type.getPossibleDrop()))
            return "You can't drop this building in this texture!";

        Building building = createBuilding(type);
        cell.setBuilding(building);
        if (building != null)
            Building.getAllBuildings().add(building);
        return "You have successfully dropped a building!";
    }

    private static Building createBuilding(BuildingType type) {
        for (ChurchType exactType : ChurchType.values())
            if (exactType.getType().equals(type))
                return new Church(type, currentGame.getCurrentTurn());
        for (ProductionBuildingType exactType : ProductionBuildingType.values())
            if (exactType.getType().equals(type))
                return new ProductionBuilding(type, currentGame.getCurrentTurn());
        for (StoneGatehouseType exactType : StoneGatehouseType.values())
            if (exactType.getType().equals(type))
                return new StoneGatehouse(type, currentGame.getCurrentTurn());
        for (StorageType exactType : StorageType.values())
            if (exactType.getType().equals(type))
                return new StorageBuilding(type, currentGame.getCurrentTurn());
        for (TowerType exactType : TowerType.values())
            if (exactType.getType().equals(type))
                return new Tower(type, currentGame.getCurrentTurn());
        for (WallType exactType : WallType.values())
            if (exactType.getType().equals(type))
                return new Wall(type, currentGame.getCurrentTurn());
        for (WorkerProduceType exactType : WorkerProduceType.values())
            if (exactType.getType().equals(type))
                return new WorkerProduceBuilding(type, currentGame.getCurrentTurn());
        if (BuildingType.MARKET.equals(type))
            return new Market(type, currentGame.getCurrentTurn());
        return null;
    }

    public static String selectBuilding(String input){
        Matcher xMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_X);
        Matcher yMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_Y);
        assert xMatcher != null;
        int x = Integer.parseInt(xMatcher.group("mapX"));
        assert yMatcher != null;
        int y = Integer.parseInt(yMatcher.group("mapY"));
        if(x < 0 || x > currentGame.getMap().getSize() || y < 0 || y > currentGame.getMap().getSize())
            return "invalid coordinates";
        MapCell cell = currentGame.getMap().getCells()[x - 1][y - 1];
        if (cell.getBuilding() == null)
            return "There is no building in this place!";
        if (!cell.getBuilding().getGovernment().equals(currentGame.getCurrentTurn()))
            return "This building doesn't belong to you!";
        setSelectedBuilding(cell.getBuilding());
        if (selectedBuilding.getType().equals(BuildingType.MARKET))
            return "you have successfully selected the Market building";
        return "you have successfully selected the building";
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
