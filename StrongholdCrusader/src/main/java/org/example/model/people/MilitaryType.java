package org.example.model.people;

import org.example.model.Weapon;

public enum MilitaryType {
    Archer(100 , 9 , 15 , 12 , Weapon.Arch, "archer"),
    Crossbowman(100 , 7 , 15 , 16 , Weapon.Crossbow, "crossbowman"),
    Spearman(100 , 8 , 20 , 8 , null, "spearman"),
    Pikeman(150 , 7 , 20 , 20 , Weapon.Pike, "pikeman"),
    Maceman(100 , 8 , 25 , 16 , Weapon.Skills, "maceman"),
    Swordsman(150 , 6 , 30 , 8 , Weapon.Swords, "swordman"),
    Knight(100 , 10 , 30 , 20 , Weapon.Skills, "knight"),
    Tunneler(100 , 9 , 20 , 8 , null, "tunneller"),
    Ladderman(70 , 9 , 0 , 8 , null, "ladderman"),
    Engineer(100 , 8 , 0 , 8 , null, "engineer"),
    BlackMonk(100 , 7 , 20 , 16 , Weapon.Club, "black monk"),
    ArcherBow(100 , 9 , 15 , 12 , Weapon.Arch, "archer bow"),
    Slaves(100 , 9 , 10 , 4 , Weapon.Hand, "slave"),
    Slingers(100 , 9 , 15 , 8 , Weapon.Sling, "slinger"),
    Assassins(100 , 8 , 20 , 16 , Weapon.Skills, "assassin"),
    HorseArchers(100 , 10 , 15 , 16 , Weapon.Arch, "horse archer"),
    ArabianSwordsmen(125 , 10 , 25 , 20 , Weapon.Swords, "arabian swordman"),
    FireThrowers(100 , 10 , 25 , 12 , Weapon.FireBall, "fire thrower");
    private int health;
    private int speed;
    private int attack;
    private int defence;
    private Weapon weapon;
    private final String name;
    MilitaryType(int health , int speed , int attack , int defence , Weapon weapon, String name){
        this.health = health;
        this.speed = speed;
        this.attack = attack;
        this.defence = defence;
        this.weapon = weapon;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
