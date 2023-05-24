package org.example.controller;

import org.example.controller.Dfs.A_Star;
import org.example.model.Government;
import org.example.model.MapCell;
import org.example.model.User;
import org.example.model.buildings.*;
import org.example.model.buildings.buildingTypes.*;
import org.example.model.people.MilitaryType;
import org.example.model.people.Unit;
import org.example.model.people.UnitState;
import org.example.view.GovernmentMenu;
import org.example.view.commands.GameMenuCommands;
import org.example.view.commands.GameMenuOutputs;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameMenuController extends Controller {
    private static Unit selectedUnit;

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
                return new Church(type, currentGame.getCurrentTurn(),exactType);
        for (ProductionBuildingType exactType : ProductionBuildingType.values())
            if (exactType.getType().equals(type))
                return new ProductionBuilding(type, currentGame.getCurrentTurn(), exactType);
        for (StoneGatehouseType exactType : StoneGatehouseType.values())
            if (exactType.getType().equals(type))
                return new StoneGatehouse(type, currentGame.getCurrentTurn(),exactType);
        for (StorageType exactType : StorageType.values())
            if (exactType.getType().equals(type))
                return new StorageBuilding(type, currentGame.getCurrentTurn(),exactType);
        for (TowerType exactType : TowerType.values())
            if (exactType.getType().equals(type))
                return new Tower(type, currentGame.getCurrentTurn(),exactType);
        for (WallType exactType : WallType.values())
            if (exactType.getType().equals(type))
                return new Wall(type, currentGame.getCurrentTurn(),exactType);
        for (WorkerProduceType exactType : WorkerProduceType.values())
            if (exactType.getType().equals(type))
                return new WorkerProduceBuilding(type, currentGame.getCurrentTurn(),exactType);
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


    public static String selectUnit(User currenUser , String input) {
        Matcher xMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_X);
        Matcher yMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_Y);
        assert xMatcher != null;
        int x = Integer.parseInt(xMatcher.group("mapX"));
        assert yMatcher != null;
        int y = Integer.parseInt(yMatcher.group("mapY"));
        if (x < 0 || y < 0 || x > currentGame.getMap().getSize()-1 ||
                y > currentGame.getMap().getSize()-1){
            return GameMenuOutputs.invalidCoordinate.getOutput();
        }
        Unit temp = null;
        ArrayList<Unit> units = currentGame.getMap().getCells()[x][y].getUnits();
        for (int i = 0 ; i < units.size() ; i++){
            if (units.get(i).getUser().toUpperCase().equals(currenUser.getUsername())){
                temp = units.get(i);
                break;
            }
        }
        if (temp == null){
            return GameMenuOutputs.youHaveNoUnit.getOutput();
        }
        selectedUnit = temp;
        return "unit " + temp.getType() + " selected successfully!";
    }
    public static String moveUnitTo(String input){
        if (selectedUnit == null){
            return GameMenuOutputs.SelectedNull.getOutput();
        }
        Matcher xMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_X);
        Matcher yMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_Y);
        assert xMatcher != null;
        int x = Integer.parseInt(xMatcher.group("mapX"));
        assert yMatcher != null;
        int y = Integer.parseInt(yMatcher.group("mapY"));
        if (x < 0 || y < 0 || x > currentGame.getMap().getSize()-1 ||
                y > currentGame.getMap().getSize()-1){
            return GameMenuOutputs.invalidCoordinate.getOutput();
        }
        String result = Mover(x , y);
        if (!result.equals("success")){
            return result;
        }
        return GameMenuOutputs.success.getOutput();
    }
    private static double getDistance(int x1 , int y1 , int x2 , int y2){
        double z = Math.sqrt(((x1 -x2)*(x1-x2)) + ((y1-y2)*(y1-y2)));
        return z;
    }
    public static String PatrolUnit(Matcher matcher) {
        if (selectedUnit == null){
            return GameMenuOutputs.SelectedNull.getOutput();
        }
        matcher.find();
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        if (selectedUnit.getPosX() != x1 || selectedUnit.getPosY() != y1){
            GameMenuOutputs.invalidPatrolCoordinate.getOutput();
        }
        if (x1 != x2 && y1 != y2 || x1 == x2 && y1 == y2){
            return GameMenuOutputs.invalidPatrol.getOutput();
        }
        String res = checkPatrolCoordinate(x1 , y1 , x2 , y2);
        if (!res.equals("ok")){
            return res;
        }
        selectedUnit.setPatroling(true);
        return null;
    }
    private static String checkPatrolCoordinate(int x1 , int y1 , int x2 , int y2){
        if ((x2 >= currentGame.getMap().getSize() || y2 >= currentGame.getMap().getSize())
                ||(x2 < 0 || y2 < 0)){
            return GameMenuOutputs.invalidCoordinate.getOutput();
        }
        if (x1 > x2){
            int temp = x2;
            x2 = x1 ;
            x1 = temp;
        }
        if (y1 > y2){
            int temp = y2;
            y2 = y1;
            y1 = temp;
        }
        for (int i = 0 ; i < x2 - x1 ; i++){
            if (currentGame.getMap().getCells()[x1+i][y1].isBlocked()){
                return GameMenuOutputs.invalidPatrol.getOutput();
            }
        }
        for (int i = 0 ; i < y2 - y1 ; i++){
            if (currentGame.getMap().getCells()[x1][y1+i].isBlocked()){
                return GameMenuOutputs.invalidPatrol.getOutput();
            }
        }
        return "ok";
    }
    public static String StopPatrolUnit(){
        if (selectedUnit == null){
            return GameMenuOutputs.SelectedNull.getOutput();
        }
        if (selectedUnit.getPatroling()) {
            selectedUnit.setPatroling(false);
            selectedUnit.setMoving(false);
        }
        return GameMenuOutputs.unitStoppedPatrol.getOutput();
    }
    public static String setUnitState(String input){
        Matcher xMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_X);
        Matcher yMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_Y);
        assert xMatcher != null;
        int x = Integer.parseInt(xMatcher.group("mapX"));
        assert yMatcher != null;
        int y = Integer.parseInt(yMatcher.group("mapY"));
        if (x < 0 || y < 0 || x > currentGame.getMap().getSize()-1 ||
                y > currentGame.getMap().getSize()-1){
            return GameMenuOutputs.invalidCoordinate.getOutput();
        }
        ArrayList<Unit> units = currentGame.getMap().getCells()[x][y].getUnits();
        Matcher matcher = GameMenuCommands.getMatcher(input , GameMenuCommands.SET_STATE);
        matcher.find();
        UnitState unitState = UnitState.getStateByString(matcher.group("state"));
        if (unitState == null){
            return GameMenuOutputs.invalidState.getOutput()+matcher.group("state");
        }
        for (int i = 0 ; i < units.size() ; i++){
            if (selectedUnit.getUser().equals(units.get(i).getUser()))
                units.get(i).setUnitState(unitState);
        }
        return GameMenuOutputs.success.getOutput();
    }
    public static String Attack(Matcher matcher){
        if (selectedUnit == null){
            return GameMenuOutputs.SelectedNull.getOutput();
        }
        matcher.find();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (x < 0 || y < 0 || x > currentGame.getMap().getSize()-1 ||
                y > currentGame.getMap().getSize()-1){
            return GameMenuOutputs.invalidCoordinate.getOutput();
        }
        String result = Mover(x , y);
        if (!result.equals("success")){
            return result;
        }
        selectedUnit.setAttacking(true);
        selectedUnit.setPosAimX(x); selectedUnit.setPosAimY(y);
        return GameMenuOutputs.isAttacking.getOutput();
    }
    public static String AttackByShoot(String input) {
        if (selectedUnit == null){
            return GameMenuOutputs.SelectedNull.getOutput();
        }
        Matcher xMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_X);
        Matcher yMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_Y);
        assert xMatcher != null;
        int x = Integer.parseInt(xMatcher.group("mapX"));
        assert yMatcher != null;
        int y = Integer.parseInt(yMatcher.group("mapY"));
        if (x < 0 || y < 0 || x > currentGame.getMap().getSize()-1 ||
                y > currentGame.getMap().getSize()-1){
            return GameMenuOutputs.invalidCoordinate.getOutput();
        }
        if (!selectedUnit.getHasWeapon()){
            return GameMenuOutputs.noWeaponUnitWantToShoot.getOutput();
        }
        if (selectedUnit.getWeapon().getRange() < getDistance(x , y , selectedUnit.getPosX(), selectedUnit.getPosY())){
            return GameMenuOutputs.invalidRange.getOutput();
        }
        selectedUnit.setShooting(true);
        selectedUnit.setPosAimX(x); selectedUnit.setPosAimY(y);
        return null;
    }
    public static String ceasefire() {
        if (selectedUnit == null){
            return GameMenuOutputs.SelectedNull.getOutput();
        }
        selectedUnit.setShooting(false);
        selectedUnit.setAttacking(false);
        selectedUnit.setPosAimY(-1); selectedUnit.setPosAimX(-1);
        return GameMenuOutputs.success.getOutput();
    }
    public static String pourOil(Matcher matcher){
        if (selectedUnit == null){
            return GameMenuOutputs.SelectedNull.getOutput();
        }
        int dirY = 0;
        int dirX = 0;
        matcher.find();
        String dir = matcher.group("dir");
        switch (dir){
            case "w" : dirX =-1; break;
            case "e" : dirX = 1; break;
            case "n" : dirY = 1; break;
            case "s" : dirY =-1; break;
        }
        int x = selectedUnit.getPosX(); int y = selectedUnit.getPosY();
        if (x+dirX < 0 || y+dirY < 0 || x+dirX > currentGame.getMap().getSize()-1 ||
                y+dirY > currentGame.getMap().getSize()-1){
            return GameMenuOutputs.invalidCoordinate.getOutput();
        }
        currentGame.getMap().getCells()[x+dirX][y+dirY].setBurning(true);
        return GameMenuOutputs.success.getOutput();
    }
    public static String digTunnel(String input){
        if (selectedUnit == null){
            return GameMenuOutputs.SelectedNull.getOutput();
        }
        if (!selectedUnit.getType().equals(MilitaryType.Tunneler)){
            return GameMenuOutputs.notTunneler.getOutput();
        }
        Matcher xMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_X);
        Matcher yMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_Y);
        assert xMatcher != null;
        int x = Integer.parseInt(xMatcher.group("mapX"));
        assert yMatcher != null;
        int y = Integer.parseInt(yMatcher.group("mapY"));
        if (x < 0 || y < 0 || x > currentGame.getMap().getSize()-1 ||
                y > currentGame.getMap().getSize()-1){
            return GameMenuOutputs.invalidCoordinate.getOutput();
        }
        String result = Mover(x , y);
        if (!result.equals("success")){
            return result;
        }
        selectedUnit.setDigging(true);
        return GameMenuOutputs.isDigging.getOutput();
    }
    public static String Mover(int x , int y){
        A_Star aStar = new A_Star(currentGame.getMap());
        String res = aStar.Astar(selectedUnit.getPosX() , selectedUnit.getPosY() , x , y , "");
        if (res.length() > selectedUnit.getSpeed()){
            return GameMenuOutputs.farEnemy.getOutput();
        }
        if (res.equals("fail")){
            GameMenuOutputs.noWay.getOutput();
        }
        System.out.println("unit way: " + res);
        int oldX = selectedUnit.getPosX(); int oldY = selectedUnit.getPosY();
        selectedUnit.setPosX(x); selectedUnit.setPosY(y);
        currentGame.getMap().getCells()[oldX][oldY].getUnits().remove(selectedUnit);
        currentGame.getMap().getCells()[x][y].getUnits().add(selectedUnit);
        return "success";
    }

    public static String nextTurn() {
        currentGame.setTurnNumber(currentGame.getTurnNumber() + 1);
        int playerNumber = currentGame.getTurnNumber() % currentGame.getPlayers().size();
        if (playerNumber == 0)
            nextRound();
        currentGame.setCurrentTurn(Government.getGovernments().get(playerNumber));
        return currentGame.getCurrentTurn().getOwner().getUsername() + "'s turn:";
    }

    private static void nextRound() {
        for (Building building : Building.getAllBuildings()) {
            building.work();
        }
    }

    public static String endGame() {
        User winner = currentGame.getCurrentTurn().getOwner();
        String result = "Winner: " + winner.getUsername();
        winner.setHighScore(winner.getHighScore() + 10);
        currentGame = null;
        Building.getAllBuildings().clear();
        Government.getGovernments().clear();
        return result;
    }
}
