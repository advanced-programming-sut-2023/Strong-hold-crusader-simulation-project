package StrongholdCrusader.src.main.java.org.example.model;

import StrongholdCrusader.src.main.java.org.example.model.buildings.CastleBuilding;

import java.util.ArrayList;


public class Animal {
    private static ArrayList<Animal> animals = new ArrayList<>();
    private int Health;
    private int damage;
    private AnimalType animalType;
    private CastleBuilding castleBuilding;
    private int posX;
    private int poxY;
    public Animal(AnimalType animalType , CastleBuilding castleBuilding){

    }

    public static ArrayList<Animal> getAnimals() {
        return animals;
    }

    public static void setAnimals(ArrayList<Animal> animals) {
        Animal.animals = animals;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public CastleBuilding getCastleBuilding() {
        return castleBuilding;
    }

    public void setCastleBuilding(CastleBuilding castleBuilding) {
        this.castleBuilding = castleBuilding;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPoxY() {
        return poxY;
    }

    public void setPoxY(int poxY) {
        this.poxY = poxY;
    }
}
