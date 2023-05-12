package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.Resources;
import org.example.model.buildings.buildingTypes.BuildingType;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Building {
    private static ArrayList<Building> allBuildings = new ArrayList<>();
    protected BuildingType type;
    protected Government government;
    protected int HP;
    protected int populationEffect;
    protected int popularityEffect;
    protected HashMap<Resources, Integer> requiredResources;
    protected int workerCount;
    protected int rate;

    public static ArrayList<Building> getAllBuildings() {
        return allBuildings;
    }

    public BuildingType getType() {
        return type;
    }

    public void changeHP(int damage) {
        this.HP -= damage;
    }

    public Government getGovernment() {
        return government;
    }

    public int getHP() {
        return HP;
    }

    public int getPopulationEffect() {
        return populationEffect;
    }

    public int getPopularityEffect() {
        return popularityEffect;
    }

    public HashMap<Resources, Integer> getRequiredResources() {
        return requiredResources;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public int getRate() {
        return rate;
    }

}
