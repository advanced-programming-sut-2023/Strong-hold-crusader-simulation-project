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
    protected Building(Government government, int HP) {
        this.government = government;
        this.HP = HP;
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

    public String getBuildingType() {
        if (this instanceof CastleBuilding)
            return "Castle";
        if (this instanceof CityStructure)
            return "City Structure";
        if (this instanceof Farm)
            return "Farm";
        if (this instanceof FoodProcessBuilding)
            return "Food Processing";
        if (this instanceof IndustrialBuilding)
            return "Industrial";
        return "Weapon";
    }
}
