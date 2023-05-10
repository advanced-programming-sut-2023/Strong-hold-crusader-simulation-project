package org.example.model;

import org.example.model.buildings.Building;
import org.example.model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class MapCell {
    private Texture texture = Texture.BASE_GROUND;

    private final ArrayList<Unit> units = new ArrayList<>();

    private final ArrayList<Building> buildings = new ArrayList<>();

    private char cellState;

    private final HashMap<Resources, Integer> resources = new HashMap<>();

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
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

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public HashMap<Resources, Integer> getResources() {
        return resources;
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
        for (Building building : buildings) {
            result += "\n" + building.getType();
        }
        return result;
    }
}
