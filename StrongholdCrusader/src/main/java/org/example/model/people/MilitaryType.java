package org.example.model.people;

import org.example.model.Weapon;

public enum MilitaryType {
    Archer(100 , 9 , 15 , 12 , Weapon.Arch),
    Crossbowman(100 , 7 , 15 , 16 , Weapon.Crossbow),
    Spearman(100 , 8 , 20 , 8 , null),
    Pikeman(150 , 7 , 20 , 20 , Weapon.Pike),
    Maceman(100 , 8 , 25 , 16 , Weapon.Skills),
    Swordsman(150 , 6 , 30 , 8 , Weapon.Swords),
    Knight(100 , 10 , 30 , 20 , Weapon.Skills),
    Tunneler(100 , 9 , 20 , 8 , null),
    Ladderman(70 , 9 , 0 , 8 , null),
    Engineer(100 , 8 , 0 , 8 , null),
    BlackMonk(100 , 7 , 20 , 16 , Weapon.Club),
    ArcherBow(100 , 9 , 15 , 12 , Weapon.Arch),
    Slaves(100 , 9 , 10 , 4 , Weapon.Hand),
    Slingers(100 , 9 , 15 , 8 , Weapon.Sling),
    Assassins(100 , 8 , 20 , 16 , Weapon.Skills),
    HorseArchers(100 , 10 , 15 , 16 , Weapon.Arch),
    ArabianSwordsmen(125 , 10 , 25 , 20 , Weapon.Swords),
    FireThrowers(100 , 10 , 25 , 12 , Weapon.FireBall);
    private int health;
    private int speed;
    private int attack;
    private int defence;
    private Weapon weapon;
    MilitaryType(int health , int speed , int attack , int defence , Weapon weapon){
        this.health = health;
        this.speed = speed;
        this.attack = attack;
        this.defence = defence;
        this.weapon = weapon;
    }

    public int getHealth() {
        return health;
    }
    public int getSpeed() {
        return speed;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefence() {
        return defence;
    }

    public Weapon getWeaponType() {
        return weapon;
    }

    public Boolean hasHorse(){
        switch (this) {
            case HorseArchers:
            case Knight: {
                return true;
            }
        }
        return false;
    }
    public Boolean canClimb(){
        switch (this){
            case Assassins: {
                return true;
            }
        }
        return false ;
    }
    public Boolean canSize(){
        switch (this){
            case Spearman: {
                return true;
            }
        }
        return false;
    }
    public Boolean hasLadder(){
        switch (this){
            case Ladderman: {
                return true;
            }
        }
        return false;
    }
    public Boolean hasOil(){
        switch (this){

        }
        return false;
    }
    public Boolean canTunnel(){
        switch (this){
            case Tunneler: {
                return true;
            }
        }
        return false;
    }
    public Boolean hasFire(){
        switch (this){
            case Slaves: {
                return true;
            }
        }
        return false;
    }
}
