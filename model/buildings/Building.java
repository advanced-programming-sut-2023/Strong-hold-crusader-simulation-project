package model.buildings;

import model.Government;
import model.Recourses;

import java.util.HashMap;

public abstract class Building {
    protected Government government;
    protected int HP;
    protected int populationEffect;
    protected int popularityEffect;
    protected HashMap<Recourses, Integer> requiredRecourses;
    protected int workerCount;
    protected int rate;
    protected Building(Government government, int HP) {
        this.government = government;
        this.HP = HP;
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

    public HashMap<Recourses, Integer> getRequiredRecourses() {
        return requiredRecourses;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public int getRate() {
        return rate;
    }
}
