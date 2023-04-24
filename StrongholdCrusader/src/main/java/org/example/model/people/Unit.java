package org.example.model.people;

import org.example.model.Weapon;

import java.util.ArrayList;

public class Unit extends People{
    private static ArrayList<Unit> units = new ArrayList<>();
    public Unit(MilitaryType militaryType){

    }

    public MilitaryType getType() {
        return type;
    }

    private MilitaryType type;
    private Weapon weapon;
    private UnitState unitState;
    private Boolean doHaveHorse;
    private Boolean isAttacking;
    private Boolean getDoHaveOil;
    private Boolean patrol;
    private int numberOfSoldiers;
    private int posX;
    private int posY;
    private int posAimX;
    private int posAimY;
    private int attack;
    private int defence;
    private int health;
    private int speed;

    public static ArrayList<Unit> getUnits() {
        return units;
    }

    public static void setUnits(ArrayList<Unit> units) {
        Unit.units = units;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public UnitState getUnitState() {
        return unitState;
    }

    public void setUnitState(UnitState unitState) {
        this.unitState = unitState;
    }

    public Boolean getDoHaveHorse() {
        return doHaveHorse;
    }

    public void setDoHaveHorse(Boolean doHaveHorse) {
        this.doHaveHorse = doHaveHorse;
    }

    public Boolean getAttacking() {
        return isAttacking;
    }

    public void setAttacking(Boolean attacking) {
        isAttacking = attacking;
    }

    public Boolean getGetDoHaveOil() {
        return getDoHaveOil;
    }

    public void setGetDoHaveOil(Boolean getDoHaveOil) {
        this.getDoHaveOil = getDoHaveOil;
    }

    public Boolean getPatrol() {
        return patrol;
    }

    public void setPatrol(Boolean patrol) {
        this.patrol = patrol;
    }

    public int getNumberOfSoldiers() {
        return numberOfSoldiers;
    }

    public void setNumberOfSoldiers(int numberOfSoldiers) {
        this.numberOfSoldiers = numberOfSoldiers;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosAimX() {
        return posAimX;
    }

    public void setPosAimX(int posAimX) {
        this.posAimX = posAimX;
    }

    public int getPosAimY() {
        return posAimY;
    }

    public void setPosAimY(int posAimY) {
        this.posAimY = posAimY;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
