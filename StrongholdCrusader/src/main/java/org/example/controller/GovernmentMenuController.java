package org.example.controller;

import org.example.model.Government;
import org.example.model.Resources;
import org.example.view.GovernmentMenu;
import org.example.view.commands.GovernmentMenuResponds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

import static org.example.controller.Controller.currentGame;

public class GovernmentMenuController {

    public static String showPopFactors() {
        ArrayList<Integer> rates= currentGame.getCurrentTurn().rateToPop();
        int sum = 0;
        for (int i = 3; i < rates.size(); i++) {
            sum += rates.get(i);
        }
        return "Food: " + rates.get(0) + "\n" +
                "Tax: " + rates.get(1) + "\n" +
                "Fear: " + rates.get(2) + "\n" +
                "Religion: " + sum;
    }
    public static String showPopularity() {
        ArrayList<Integer> rates= currentGame.getCurrentTurn().rateToPop();
        int popularity=0;
        for (Integer rate : rates) {
            popularity += rate;
        }
        return "Popularity: "+ popularity;
    }
    public static HashMap<Resources,Integer> showFoodList() {
        return currentGame.getCurrentTurn().food();
    }
    public static String foodRate(Matcher matcher) {
        int number=Integer.parseInt(matcher.group("rateNumber"));
        if (number<=2&&number>=-2){
            currentGame.getCurrentTurn().setFoodRate(number);
            return "food rate"+ GovernmentMenuResponds.SET.getText();
        }
        else {
            return GovernmentMenuResponds.INVALID.getText()+"food rate";
        }
    }

    public static String showFoodRate() {
        return "food rate: "+currentGame.getCurrentTurn().getFoodRate();
    }

    public static String taxRate(Matcher matcher) {
        int number=Integer.parseInt(matcher.group("rateNumber"));
        if (number<=8&&number>=-3){
            currentGame.getCurrentTurn().setTaxRate(number);
            return "tax rate"+ GovernmentMenuResponds.SET.getText();
        }
        else {
            return GovernmentMenuResponds.INVALID.getText()+"tax rate";
        }
    }

    public static String showTaxRate() {
        return "tax rate: "+currentGame.getCurrentTurn().getTaxRate();
    }

    public static String fearRate(Matcher matcher) {
        int number=Integer.parseInt(matcher.group("rateNumber"));
        if (number<=5&&number>=-5){
            currentGame.getCurrentTurn().setFearRate(number);
            return "fear rate"+ GovernmentMenuResponds.SET.getText();
        }
        else {
            return GovernmentMenuResponds.INVALID.getText()+"fear rate";
        }
    }
}
