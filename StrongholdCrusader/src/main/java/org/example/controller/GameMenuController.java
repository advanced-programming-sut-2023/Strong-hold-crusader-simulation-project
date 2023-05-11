package org.example.controller;

import org.example.controller.Dfs.A_Star;
import org.example.model.Map;
import org.example.model.Move;
import org.example.model.User;
import org.example.model.buildings.Building;
import org.example.model.people.MilitaryType;
import org.example.model.people.Unit;
import org.example.model.people.UnitState;
import org.example.view.commands.GameMenuCommands;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameMenuController extends Controller  {
    private static Unit selectedUnit ;
    private static Building selectedBuilding;
    public static String showPopularityFactors() {
        return null;
    }
    public static int showPopularity() {
        return 0;
    }
    public static String showFoodList() {
        return null;
    }
    public static String setFoodRate(Matcher matcher) {
        return null;
    }
    public static String showFoodRate() {
        return null;
    }
    public static String setTaxRate(Matcher matcher) {
        return null;
    }
    public static String showTaxRate() {
        return null;
    }
    public static String setFearRate(Matcher matcher) {
        return null;
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
    public static String dropBuilding(Matcher matcher){
        return null;
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
    public static String selectUnit(User currenUser , String input) {
        Matcher xMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_X);
        Matcher yMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_Y);
        assert xMatcher != null;
        int x = Integer.parseInt(xMatcher.group("mapX"));
        assert yMatcher != null;
        int y = Integer.parseInt(yMatcher.group("mapY"));
        if (x < 0 || y < 0 || x > MapMenuController.getMap().getSize()-1 ||
                                        y > MapMenuController.getMap().getSize()-1){
            return GameMenuOutputs.invalidCoordinate.getOutput();
        }
        Unit temp = null;
        ArrayList<Unit> units = MapMenuController.getMap().getUnits(x ,y);
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
        if (x < 0 || y < 0 || x > MapMenuController.getMap().getSize()-1 ||
                y > MapMenuController.getMap().getSize()-1){
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
        if ((x2 >= MapMenuController.getMap().getSize() || y2 >= MapMenuController.getMap().getSize())
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
            if (MapMenuController.getMap().getCells()[x1+i][y1].isBlocked()){
                return GameMenuOutputs.invalidPatrol.getOutput();
            }
        }
        for (int i = 0 ; i < y2 - y1 ; i++){
            if (MapMenuController.getMap().getCells()[x1][y1+i].isBlocked()){
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
        if (x < 0 || y < 0 || x > MapMenuController.getMap().getSize()-1 ||
                y > MapMenuController.getMap().getSize()-1){
            return GameMenuOutputs.invalidCoordinate.getOutput();
        }
        ArrayList<Unit> units = MapMenuController.getMap().getUnits(x , y);
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
        if (x < 0 || y < 0 || x > MapMenuController.getMap().getSize()-1 ||
                y > MapMenuController.getMap().getSize()-1){
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
        if (x < 0 || y < 0 || x > MapMenuController.getMap().getSize()-1 ||
                y > MapMenuController.getMap().getSize()-1){
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
        if (x+dirX < 0 || y+dirY < 0 || x+dirX > MapMenuController.getMap().getSize()-1 ||
                y+dirY > MapMenuController.getMap().getSize()-1){
            return GameMenuOutputs.invalidCoordinate.getOutput();
        }
        MapMenuController.getMap().getCells()[x+dirX][y+dirY].setBurning(true);
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
        if (x < 0 || y < 0 || x > MapMenuController.getMap().getSize()-1 ||
                y > MapMenuController.getMap().getSize()-1){
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
        A_Star aStar = new A_Star(MapMenuController.getMap());
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
        MapMenuController.getMap().getCells()[oldX][oldY].getUnits().remove(selectedUnit);
        MapMenuController.getMap().getCells()[x][y].getUnits().add(selectedUnit);
        return "success";
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
    public static void main(String[] args) {
        selectUnit( null, "select unit -y -9089 -x -92548");
    }
}
