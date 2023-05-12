package org.example.model;

import org.example.model.buildings.Building;
import org.example.model.buildings.Tower;
import org.example.model.buildings.Wall;
import org.example.model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class MapCell {
    private Texture texture = Texture.BASE_GROUND;

    private final ArrayList<Unit> units = new ArrayList<>();

    private Building building = null;

    private final HashMap<Resources, Integer> resources = new HashMap<>();

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public char getCellState() {
        if (units.size() > 0)
            return 'S';
        if (building != null) {
            if (building instanceof Wall || building instanceof Tower)
                return 'W';
            return 'B';
        }
        return ' ';
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

    @Override
    public String toString() {
        //TODO inja havaset bashe esme resource o building o ... print koni
        String result = "Texture: " + texture.getTextureName();
        result += "\nResources:";
        for (Resources resource : resources.keySet()) {
            result += "\n" + resource.getName() + " count: " + resources.get(resource);
        }
        result += "\nTotal Unit Count: " + units.size();
        result += "\nUnits:";
        for (Unit unit : units) {
            result += "\n" + unit.getType();
        }
        result += "\nBuilding:" + "\n" + building.getType().getBuildingName();
        return result;
    }
}
