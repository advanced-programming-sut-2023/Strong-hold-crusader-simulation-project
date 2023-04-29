package org.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    public HashMap<Resources,Integer> food(){
        HashMap<Resources,Integer> foods=new HashMap<>();
        for (HashMap.Entry<Resources, Integer> entry : resourceCount.entrySet()) {
            if (Objects.equals(entry.getKey().getText(), "food")&&entry.getValue()>0){
                foods.put(entry.getKey(),entry.getValue());
            }
        }
        return foods;
    }
    public ArrayList<Integer> rateToPop(){
        ArrayList<Integer> result=new ArrayList<>();
        result.add(fearRate*4);
        if (taxRate<=0){
            result.add((taxRate*(-2))-1);
        }
        else {
            result.add(taxRate*(2));
        }
        result.add(fearRate);
        return result;
    }
}