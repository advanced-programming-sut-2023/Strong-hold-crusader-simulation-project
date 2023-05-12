package org.example.model;

import org.example.model.buildings.Building;
import org.example.model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class MapCell {
    private Texture texture = Texture.BASE_GROUND;

    private ArrayList<Unit> units = new ArrayList<>();

    private Building building ;
    private Rock rock;
    private Tree tree;

    private char cellState;

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
        this.units = null;
    }
    public void setCellState(char cellState) {
        this.cellState = cellState;
    }

    public char getCellState() {
        return cellState;
    }

    public ArrayList<Unit> getUnits() {
        return units;
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

    public void addUnits(Unit unit) {
        this.units.add(unit);
    }

    @Override
    public String toString() {
        //TODO inja havaset bashe esme resource o building o ... print koni
        String result = "Texture: " + texture.getTextureName();
        result += "\nResources:";
        for (Resources resource : resources.keySet()) {
            result += "\n" + resource + " count: " + resources.get(resource);
        }
        result += "\nTotal Unit Count: " + units.size();
        result += "\nUnits:";
        for (Unit unit : units) {
            result += "\n" + unit.getType();
        }
        result += "\nBuildings:";
        result += "\n" + building.getType();

        return result;
    }
}
