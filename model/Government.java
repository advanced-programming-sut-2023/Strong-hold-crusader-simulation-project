package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Government {
    private static final String[] popularityFactors = {"Food", "Tax", "Religion", "Fear Factor"};
    private User owner;
    private HashMap<Resources, Integer> resourceCount;
    private int popularity;
    private int taxRate;
    private int fearRate;
    private int foodRate;
    private int population;
    private int balance;

    public void changeBalance(int change) {
        this.balance += change;
    }

    public int getBalance() {
        return balance;
    }

    public ArrayList<Trade> getTradeHistory() {
        return tradeHistory;
    }

    public ArrayList<Trade> getTradeNotification() {
        return tradeNotification;
    }

    private ArrayList<Trade> tradeHistory;
    private ArrayList<Trade> tradeNotification;

    public Government() {

    }

    public static String[] getPopularityFactors(){
        return popularityFactors;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void changePopularity(int change) {
        this.popularity += change;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public void changePopulation(int change) {
        this.population += change;
    }

    public HashMap<Resources, Integer> getResourceCount() {
        return resourceCount;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public int getFearRate() {
        return fearRate;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public int getPopulation() {
        return population;
    }
}