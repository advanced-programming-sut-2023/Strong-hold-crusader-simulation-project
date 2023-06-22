package org.example.model.people;

import org.example.controller.Controller;
import org.example.model.DigDetails;
import org.example.model.Move;
import org.example.model.Patrol;
import org.example.model.Weapon;

import java.io.Serializable;
import java.util.ArrayList;

public class Unit extends People implements Serializable {
    private static ArrayList<Unit> units = new ArrayList<>();
    public Unit(String ownerUsername ,MilitaryType militaryType ){
        super(ownerUsername , militaryType.getHealth());
        this.type = militaryType;
        this.speed = militaryType.getSpeed();
        this.attack = militaryType.getAttack();
        this.defence = militaryType.getDefence();
        this.hasHorse = militaryType.hasHorse();
        this.hasOil = militaryType.hasOil();
        this.hasFire = militaryType.hasFire();
        this.canClimb = militaryType.canClimb();
        this.hasLadder = militaryType.hasLadder();
        this.canTunnel = militaryType.canTunnel();
        if (militaryType.getWeaponType() == null) {
            hasWeapon = false;
        }
        else {
            hasWeapon = true;
            this.weapon = militaryType.getWeaponType();
        }
    }
    public MilitaryType getType() {
        return type;
    }
    private DigDetails digDetails;
    private Move move;
    private Patrol patrol;
    private MilitaryType type;
    private Boolean isDigging;
    private Boolean isMoving ;
    private Boolean isShooting;
    private Weapon weapon;
    private UnitState unitState;
    private Boolean hasWeapon;
    private Boolean isSeen;
    private Boolean hasHorse;
    private Boolean patroling;
    private Boolean isAttacking;
    private int attack;
    private int defence;
    private int speed;
    private Boolean canClimb;
    private Boolean hasFire;
    private Boolean hasLadder;
    private Boolean canSize;
    private Boolean canTunnel;
    private Boolean hasOil;
    private Boolean isOilFull;
    public void checkMove(){
        if (isMoving){
            if (move != null){
                move.decreaseTurnsLeft();
                if (move.getTurnsLeft() == 0){
                    Controller.getCurrentGame().getMap().getCells()[posX][posY].getUnits().remove(this);
                    this.posX = move.getAimX();
                    this.posY = move.getAimY();
                    Controller.getCurrentGame().getMap().getCells()[posX][posY].getUnits().add(this);
                    isMoving = false;
                    move = null;
                }
                if (patroling == true){
                    isMoving = true;
                    if (patrol.isArrived(posX , posY)){
                        move = patrol.nextMove(speed , posX , posY);
                    }
                }
            }
        }
    }
    public Weapon getWeaponType() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Boolean getCanClimb() {
        return canClimb;
    }
    public void setCanClimb(Boolean canClimb) {
        this.canClimb = canClimb;
    }
    public Boolean getHasFire() {
        return hasFire;
    }
    public void setHasFire(Boolean hasFire) {
        this.hasFire = hasFire;
    }

    public Boolean getHasLadder() {
        return hasLadder;
    }

    public void setHasLadder(Boolean hasLadder) {
        this.hasLadder = hasLadder;
    }

    public Boolean getCanSize() {
        return canSize;
    }

    public void setCanSize(Boolean canSize) {
        this.canSize = canSize;
    }

    public Boolean getHasOil() {
        return hasOil;
    }

    public void setHasOil(Boolean hasOil) {
        this.hasOil = hasOil;
    }

    public Boolean getOilFull() {
        return isOilFull;
    }

    public void setOilFull(Boolean oilFull) {
        isOilFull = oilFull;
    }
    public static ArrayList<Unit> getUnits() {
        return units;
    }

    public static void setUnits(ArrayList<Unit> units) {
        Unit.units = units;
    }
    public UnitState getUnitState() {
        return unitState;
    }

    public void setUnitState(UnitState unitState) {
        this.unitState = unitState;
    }

    public Boolean getHasHorse() {
        return hasHorse;
    }

    public void setHasHorse(Boolean hasHorse) {
        this.hasHorse = hasHorse;
    }

    public Boolean getAttacking() {
        return isAttacking;
    }

    public void setAttacking(Boolean attacking) {
        isAttacking = attacking;
    }
    public Boolean getPatroling() {
        return patroling;
    }

    public void setPatroling(Boolean patroling) {
        this.patroling = patroling;
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
        return super.getHealth();
    }

    public void setHealth(int health) {
        super.setHealth(health);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setType(MilitaryType type) {
        this.type = type;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }

    public String getUser() {
        return this.ownerUsername;
    }

    public void setUser(String  username) {
        this.ownerUsername = username;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Boolean getHasWeapon() {
        return hasWeapon;
    }

    public void setHasWeapon(Boolean hasWeapon) {
        this.hasWeapon = hasWeapon;
    }

    public Boolean getCanTunnel() {
        return canTunnel;
    }

    public void setCanTunnel(Boolean canTunnel) {
        this.canTunnel = canTunnel;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public Boolean getMoving() {
        return isMoving;
    }

    public void setMoving(Boolean moving) {
        isMoving = moving;
    }

    public Patrol getPatrol() {
        return patrol;
    }

    public void setPatrol(Patrol patrol) {
        this.patrol = patrol;
    }

    public Boolean getShooting() {
        return isShooting;
    }

    public void setShooting(Boolean shooting) {
        isShooting = shooting;
    }

    public DigDetails getDigDetails() {
        return digDetails;
    }

    public void setDigDetails(DigDetails digDetails) {
        this.digDetails = digDetails;
    }

    public Boolean getDigging() {
        return isDigging;
    }

    public void setDigging(Boolean digging) {
        isDigging = digging;
    }
}
