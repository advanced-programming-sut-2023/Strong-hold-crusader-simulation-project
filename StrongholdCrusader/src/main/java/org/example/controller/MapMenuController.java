package org.example.controller;

import org.example.model.Map;
import org.example.model.Rock;
import org.example.model.Texture;
import org.example.model.Tree;
import org.example.model.buildings.Building;
import org.example.model.buildings.BuildingType;
import org.example.model.people.MilitaryType;
import org.example.model.people.Unit;
import org.example.view.commands.MapMenuResponds;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;

public class MapMenuController extends Controller {
    public static String showDetails(int x, int y) {
        return map.getCells()[x - 1][y - 1].toString();
    }
    public static boolean setTexture(int x1,int y1,String type,boolean a){
        if (Texture.findTexture(type)!=null){
            String what;
            if ((what=empty(x1,y1))!=null){
                if (!a){
                    System.out.println(MapMenuResponds.CANT_BE_CHANGED+what);
                }
                return false;
            }
            else {
                getGame().getMap().getCells()[x1][y1].setTexture(Texture.findTexture(type));
                if (!a){
                    System.out.println(MapMenuResponds.TEXTURE_CHANGED);
                }
                return true;
            }
        }
        System.out.println(MapMenuResponds.INVALID.getRegex()+" (for type)");
        return false;
    }
    public static String setTexture(int x1,int y1,int x2,int y2,String type){
        x1--;
        y1--;
        x2--;
        y2--;
        if ((x1>x2)||(y1>y2)||(x1>=getGame().getMap().getSize())||(y1>=getGame().getMap().getSize())||
                (x2>=getGame().getMap().getSize())||(y2>=getGame().getMap().getSize())){
            return MapMenuResponds.INVALID_POSITION.getRegex();
        }
        for ( int i=x1;i<=x2-x1+1;i++){
            for ( int j=y1;j<=y2-y1+1;j++){
                setTexture(i,j,type,!(x2-x1==0));
            }
        }
        return MapMenuResponds.DONE.getRegex();
    }
    public static String empty(int x, int y){
        if (getGame().getMap().getCells()[x][y].getRock()!=null)
            return "rock";
        if(getGame().getMap().getCells()[x][y].getBuilding()!=null)
            return "building";
        if(getGame().getMap().getCells()[x][y].getTree()!=null)
            return "tree";
        if (getGame().getMap().getCells()[x][y].getUnits()!=null)
            return "unit";
        return null;
    }
    public static String clear(int x,int y){
        x--;
        y--;
        if ((x>=getGame().getMap().getSize())||(y>=getGame().getMap().getSize())){
            return MapMenuResponds.INVALID_POSITION.getRegex();
        }
        if (empty(x,y)==null|| Objects.equals(empty(x, y), "unit")){
            getGame().getMap().getCells()[x][y].clearUnits();
            getGame().getMap().getCells()[x][y].setTexture(Texture.BASE_GROUND);
            return MapMenuResponds.CLEAR.getRegex();
        }
        else {
            return MapMenuResponds.CANT_BE_CLEARED.getRegex()+empty(x,y);
        }
        //TODO DEL UNIT
    }
    public static char random(String string){
        Random random=new Random();
        int a=random.nextInt(4);
        return string.charAt(a);
    }
    public static String dropRock(int x,int y,String direction){
        x--;
        y--;
        if (!((x>=getGame().getMap().getSize())||(y>=getGame().getMap().getSize()))){
            if (Objects.equals(direction, "r") || Objects.equals(direction, "w") || Objects.equals(direction, "s") || Objects.equals(direction, "n") || Objects.equals(direction, "e")){
                String what;
                if ((what=empty(x,y))!=null){
                    return MapMenuResponds.ERROR.getRegex()+"/a "+what;
                }
                if (Objects.equals(direction, "r")){
                    direction=String.valueOf(random("wens"));
                }
                getGame().getMap().getCells()[x][y].setRock(Rock.findDir(direction));
                return MapMenuResponds.SET_Rock.getRegex();
            }
            return MapMenuResponds.INVALID.getRegex()+" (for direction)";
        }
        return MapMenuResponds.INVALID_POSITION.getRegex();
    }
    public static String dropTree(int x,int y,String type){
        x--;
        y--;
        if (!((x>=getGame().getMap().getSize())||(y>=getGame().getMap().getSize()))){
            if (Tree.findType(type)!=null){
                String what;
                if ((what=empty(x,y))!=null){
                    return MapMenuResponds.ERROR.getRegex()+"/a "+what;
                }
                if (getGame().getMap().getCells()[x][y].getTexture()==Texture.GRASS||getGame().getMap().getCells()[x][y].getTexture()==Texture.DENSE_OASIS){
                    getGame().getMap().getCells()[x][y].setTree(Tree.findType(type));
                    return MapMenuResponds.SET_TREE.getRegex();
                }
                else {
                    return MapMenuResponds.SET_ERROR.getRegex()+"tree";
                }
            }
            return MapMenuResponds.INVALID.getRegex()+" (for type)";
        }
        return MapMenuResponds.INVALID_POSITION.getRegex();
    }
    public static boolean textureAndBuilding(BuildingType buildingType,Texture texture){
        if (buildingType== BuildingType.DAIRY_FARM||
                buildingType== BuildingType.HOP_FARM||
                buildingType== BuildingType.WHEAT_FARM||
                buildingType== BuildingType.APPLE_ORCHARD||
                buildingType== BuildingType.HUNTERS_POST){
            return texture == Texture.GRASS || texture == Texture.DENSE_OASIS;
        }
        else if (buildingType==BuildingType.PITCH_RIG){
            return texture == Texture.PLAIN;
        }
        else {
            return texture == Texture.BASE_GROUND;
        }
    }
    public static String dropBuilding(int x,int y,String type){
        x--;
        y--;
        if ((x>=getGame().getMap().getSize())||(y>=getGame().getMap().getSize())){
            return MapMenuResponds.INVALID_POSITION.getRegex();
        }
        if (textureAndBuilding(BuildingType.getTypeByName(type),getGame().getMap().getCells()[x][y].getTexture())){
            //TODO sakht building
            //getGame().getMap().getCells()[x][y].set
        }
        else {

        }
    }
    public static String dropUnit(int x,int y,String type,int count){
        x--;
        y--;
        ArrayList<Unit> units=getGame().getCurrentTurn().getUnitByType(type);
        if ((x>=getGame().getMap().getSize())||(y>=getGame().getMap().getSize())){
            return MapMenuResponds.INVALID_POSITION.getRegex();
        }
        if (count>units.size()){
            return MapMenuResponds.OUT_OF_NUMBER.getRegex();
        }
        for (MilitaryType militaryType: MilitaryType.values()){
            if (Objects.equals(militaryType.toString().toLowerCase(), type.toLowerCase())){
                for (int i=0;i<count;i++){
                    if (units.get(i).getType().toString().equalsIgnoreCase(type))
                        getGame().getMap().getCells()[x][y].addUnits(units.get(i));
                }
                return MapMenuResponds.SET_UNIT.getRegex();
            }
        }
        return MapMenuResponds.INVALID+"(for type)";
    }
    public static void setMap(Map map) {
        MapMenuController.map = map;
    }
}
