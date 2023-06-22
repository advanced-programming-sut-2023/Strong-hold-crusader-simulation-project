package org.example.controller;

import org.example.model.Rock;
import org.example.model.Texture;
import org.example.model.Tree;
import org.example.model.people.MilitaryType;
import org.example.model.people.Unit;
import org.example.view.commands.MapMenuResponds;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MapMenuController extends Controller {
    public static String showDetails(int x, int y) {
        return currentGame.getMap().getCells()[x - 1][y - 1].toString();
    }
    public static String empty(int x, int y){

        if (getMap().getCells()[x][y].getRock()!=null)
            return "rock";
        if(getMap().getCells()[x][y].getBuilding()!=null)
            return "building";
        if(getMap().getCells()[x][y].getTree()!=null)
            return "tree";
        if(getMap().getCells()[x][y].getUnits().size()>=1)
            return "unit";

        return null;
    }
    public static boolean setTexture(int x1,int y1,String type,boolean a){
        if (Texture.findTexture(type)!=null){
            String what;
            if ((what = empty(x1,y1))!=null){
                System.out.println(MapMenuResponds.CANT_BE_CHANGED.getRegex()+what);
                return false;
            }
            else {
                getMap().getCells()[x1][y1].setTexture(Texture.findTexture(type));
                if (!a){
                    System.out.println(MapMenuResponds.TEXTURE_CHANGED.getRegex());
                }
                return true;
            }
        }
        System.out.println(MapMenuResponds.INVALID.getRegex() + " (for type)");
        return false;
    }
    public static String setTexture(int x1,int y1,int x2,int y2,String type){
        x1--;
        y1--;
        x2--;
        y2--;
        if ((x1>x2)||(y1>y2)||(x1>= getMap().getSize())||(y1>= getMap().getSize())||
                (x2>= getMap().getSize())||(y2>= getMap().getSize())){
            return MapMenuResponds.INVALID_POSITION.getRegex();
        }
        for ( int i=x1;i<=x2;i++){
            for ( int j=y1;j<=y2;j++){
                setTexture(i,j,type,!(x2-x1==0));
            }
        }

        return MapMenuResponds.DONE.getRegex();
    }
    public static String clear(int x,int y){
        x--;
        y--;
        int size=getMap().getSize();
        if ((x>= size)||(y>= size)){
            return MapMenuResponds.INVALID_POSITION.getRegex();
        }
        if (empty(x,y)==null|| Objects.equals(empty(x, y), "unit")){
            getMap().getCells()[x][y].clearUnits();
            getMap().getCells()[x][y].setTexture(Texture.BASE_GROUND);
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
        if (!((x>= getMap().getSize())||(y>= getMap().getSize()))){
            if (Objects.equals(direction, "r") || Objects.equals(direction, "w") || Objects.equals(direction, "s") || Objects.equals(direction, "n") || Objects.equals(direction, "e")){
                String what;
                if ((what=empty(x,y))!=null){
                    return MapMenuResponds.ERROR.getRegex()+"/a "+what;
                }
                if (Objects.equals(direction, "r")){
                    direction=String.valueOf(random("wens"));
                }
                getMap().getCells()[x][y].setRock(Rock.E);
                return MapMenuResponds.SET_Rock.getRegex();
            }
            return MapMenuResponds.INVALID.getRegex()+" (for direction)";
        }
        return MapMenuResponds.INVALID_POSITION.getRegex();
    }
    public static String dropTree(int x,int y,String type){
        x--;
        y--;
        if (!((x>= getMap().getSize())||(y>= getMap().getSize()))){
            if (Tree.findType(type)!=null){
                String what;
                if ((what=empty(x,y))!=null){
                    return MapMenuResponds.ERROR.getRegex()+"/a "+what;
                }
                if (getMap().getCells()[x][y].getTexture()==Texture.GRASS||
                        getMap().getCells()[x][y].getTexture()==Texture.DENSE_OASIS){
                    getMap().getCells()[x][y].setTree(Tree.findType(type));
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
    public static boolean unitAndTexture(Texture texture){
        if (texture==Texture.PLAIN||
                texture==Texture.RIVER||
                texture==Texture.BIG_POND||
                texture==Texture.SMALL_POND||
                texture==Texture.SEA){
            System.out.println(MapMenuResponds.BAD_TEXTURE_UNIT);
            return false;
        }
        return true;
    }
    public static String dropUnit(int x,int y,String type,int count){
        //todo amir
        x--;
        y--;
        ArrayList<Unit> units= currentGame.getCurrentTurn().getUnitByType(type);
        if ((x>= currentGame.getMap().getSize())||(y>= currentGame.getMap().getSize())){
            return MapMenuResponds.INVALID_POSITION.getRegex();
        }
        if (count>units.size()){
            return MapMenuResponds.OUT_OF_NUMBER.getRegex();
        }
        String what=empty(x,y);
        if (what!=null){
            return MapMenuResponds.ERROR.getRegex()+"/a "+what;
        }
        for (MilitaryType militaryType: MilitaryType.values()){
            if (Objects.equals(militaryType.toString().toLowerCase(), type.toLowerCase())){
                for (int i=0;i<count;i++){
                    if (units.get(i).getType().toString().equalsIgnoreCase(type))
                        if (unitAndTexture(currentGame.getMap().getCells()[x][y].getTexture()))
                            currentGame.getMap().getCells()[x][y].getUnits().add(units.get(i));
                }
                return MapMenuResponds.SET_UNIT.getRegex();
            }
        }
        return MapMenuResponds.INVALID+"(for type)";
    }
}
