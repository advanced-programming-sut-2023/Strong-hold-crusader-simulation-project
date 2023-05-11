package org.example.model;

public enum Weapon {
    Swords(80 , 1),
    Arch(60 , 10),
    Hand(20 ,1),
    Sling(20 , 4),
    Club(40 , 1),
    Pike(40 , 1),
    Crossbow(100 , 8),
    Skills(100 , 1),
    FireBall(150 , 6);
    private int damage;
    private int range;
    Weapon(int damage , int range){
        this.damage = damage;
        this.range = range;
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
