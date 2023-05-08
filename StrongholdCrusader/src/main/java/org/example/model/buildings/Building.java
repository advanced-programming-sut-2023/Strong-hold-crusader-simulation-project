package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.Resources;

import java.util.HashMap;

public abstract class Building {
    protected BuildingType type;
    protected Government government;
    protected int HP;
    protected int populationEffect;
    protected int popularityEffect;
    protected HashMap<Resources, Integer> requiredResources;
    protected int workerCount;
    protected int rate;

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
