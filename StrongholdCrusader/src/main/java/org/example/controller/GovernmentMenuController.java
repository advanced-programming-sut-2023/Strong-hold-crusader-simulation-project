package org.example.controller;

import org.example.model.Government;
import org.example.model.Resources;
import org.example.view.GovernmentMenu;
import org.example.view.commands.GovernmentMenuResponds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class GovernmentMenuController {
    private static Government currentGovernment;
    public static void setCurrentGovernment(Government currentGovernment) {
        GovernmentMenuController.currentGovernment = currentGovernment;
    }

    public static String showPopFactors() {
        ArrayList<Integer> rates=currentGovernment.rateToPop();
        return "Food: " + rates.get(0)+
                "Tax: " + rates.get(1)+
                "Fear: " + rates.get(2);
    }
    public static String showPopularity() {
        ArrayList<Integer> rates=currentGovernment.rateToPop();
        int popularity=0;
        for (Integer rate : rates) {
            popularity += rate;
        }
        return "Popularity :"+ popularity;
    }
    public static HashMap<Resources,Integer> showFoodList() {
        return currentGovernment.food();
    }
    public static String foodRate(Matcher matcher) {
        int number=Integer.parseInt(matcher.group("rateNumber"));
        if (number<=2&&number>=-2){
            currentGovernment.setFoodRate(number);
            return "food rate"+ GovernmentMenuResponds.SET.getText();
        }
        else {
            return GovernmentMenuResponds.INVALID.getText()+"food rate";
        }
    }

    public static String showFoodRate() {
        return "food rate: "+currentGovernment.getFoodRate();
    }

    public static String taxRate(Matcher matcher) {
        int number=Integer.parseInt(matcher.group("rateNumber"));
        if (number<=8&&number>=-3){
            currentGovernment.setTaxRate(number);
            return "tax rate"+ GovernmentMenuResponds.SET.getText();
        }
        else {
            return GovernmentMenuResponds.INVALID.getText()+"tax rate";
        }
    }

    public static String showTaxRate() {
        return "tax rate: "+currentGovernment.getTaxRate();
    }

    public static String fearRate(Matcher matcher) {
        int number=Integer.parseInt(matcher.group("rateNumber"));
        if (number<=5&&number>=-5){
            currentGovernment.setFearRate(number);
            return "fear rate"+ GovernmentMenuResponds.SET.getText();
        }
        else {
            return GovernmentMenuResponds.INVALID.getText()+"fear rate";
        }
    }
}
