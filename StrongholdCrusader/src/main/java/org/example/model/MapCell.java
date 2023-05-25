package org.example.model;

import org.example.model.buildings.Building;
import org.example.model.buildings.Tower;
import org.example.model.buildings.Wall;
import org.example.model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class MapCell {
    private Texture texture = Texture.BASE_GROUND;

    private ArrayList<Unit> units = new ArrayList<>();

    private Building building = null;
    private Rock rock = null;
    private Tree tree = null;

    private final HashMap<Resources, Integer> resources = new HashMap<>();

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    public Rock getRock() {
        return rock;
    }

    public void setRock(Rock rock) {
        this.rock = rock;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public void clearUnits() {
        units.clear();
    }
    public ArrayList<Unit> getUnits() {
        return units;
    }
    private Boolean hasTunnel;
    private Boolean isBurning;

    public Boolean getHasTunnel() {
        return hasTunnel;
    }

    public void setHasTunnel(Boolean hasTunnel) {
        this.hasTunnel = hasTunnel;
    }

    public Boolean getBurning() {
        return isBurning;
    }

    public void setBurning(Boolean burning) {
        isBurning = burning;
    }

    public boolean isBlocked(){
        if (texture == Texture.ROCK || texture == Texture.IRON || building != null || isBurning){
            return true;
        }
        return false;
    }


    public char getCellState() {
        if (units.size() > 0)
            return 'S';
        if (building != null) {
            if (building instanceof Wall || building instanceof Tower)
                return 'W';
            return 'B';
        }
        if (tree != null)
            return 'T';
        if (rock != null)
            return 'R';
        return ' ';
    }
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public HashMap<Resources, Integer> getResources() {
        return resources;
    }

    @Override
    public String toString() {
        String result = "Texture: " + texture.getTextureName();
        result += "\nResources:";
        for (Resources resource : resources.keySet()) {
            result += "\n" + resource.getName() + " count: " + resources.get(resource);
        }
        result += "\nTotal Unit Count: " + units.size();
        result += "\nUnits:";
        for (Unit unit : units) {
            result += "\n" + unit.getType().getName();
        }
        if (building != null)
            result += "\nBuilding:" + "\n" + building.getType().getBuildingName();
        return result;
    }
}
