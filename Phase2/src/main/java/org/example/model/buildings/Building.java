package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.buildings.buildingTypes.BuildingType;

import java.util.ArrayList;

public abstract class Building {
    private static ArrayList<Building> allBuildings = new ArrayList<>();
    protected final BuildingType type;
    protected final Government government;
    protected int HP;
    protected final int workerCount;
    protected int rate;
    protected int x;
    protected int y;
    protected Building(BuildingType type, Government government) {
        this.type = type;
        this.government = government;
        this.workerCount = type.getWorkerCount();
        this.HP = type.getHP();
        government.getBuildings().add(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract void work();
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

    public int getWorkerCount() {
        return workerCount;
    }

    public int getRate() {
        return rate;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
