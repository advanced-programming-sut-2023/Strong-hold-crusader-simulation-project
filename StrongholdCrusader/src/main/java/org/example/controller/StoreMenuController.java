package org.example.controller;

import org.example.model.Resources;
import org.example.view.commands.MapMenuResponds;
import org.example.view.commands.StoreMenuResponds;

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;

public class StoreMenuController extends Controller {
    public static String showPriceList() {
        String result="";
        for (HashMap.Entry<Resources, Integer> entry : getGame().getCurrentTurn().getResourceCount().entrySet()) {
            result += "name: " + entry.getKey().getName()+
                    "\nbuy price: "+ entry.getKey().getBuyPrice()+
                    "\nsell price: "+entry.getKey().getSellPrice()+
                    "\nnumber: "+entry.getValue();
        }
        return result;
    }
    public static String buy(int amount, String resource){
        Resources resources=Resources.getByName(resource);
        if (amount<0){
            return StoreMenuResponds.INVALID.getText()+"amount";
        }
        for (HashMap.Entry<Resources, Integer> entry : getGame().getCurrentTurn().getResourceCount().entrySet()) {
            assert resources != null;
            if (Objects.equals(resources.getName(), entry.getKey().getName())){
                if (getGame().getCurrentTurn().getBalance()<(amount*resources.getBuyPrice())){
                    return StoreMenuResponds.NOT_ENOUGH_BALANCE.getText();
                }
                getGame().getCurrentTurn().changeBalance(-1*(entry.getKey().getBuyPrice()*amount));
                getGame().getCurrentTurn().getResourceCount().put(entry.getKey(), entry.getValue()+amount);
                return StoreMenuResponds.BOUGHT.getText()+resources.getName();
            }
        }
        return StoreMenuResponds.INVALID.getText()+"item's name";
    }
    public static String sell(int amount, String resource) {
        Resources resources=Resources.getByName(resource);
        if (amount<0){
            return StoreMenuResponds.INVALID.getText()+"amount";
        }
        for (HashMap.Entry<Resources, Integer> entry : getGame().getCurrentTurn().getResourceCount().entrySet()) {
            assert resources != null;
            if (Objects.equals(resources.getName(), entry.getKey().getName())){
                if (entry.getValue()<amount){
                    return StoreMenuResponds.NOT_ENOUGH_BALANCE.getText();
                }
                getGame().getCurrentTurn().changeBalance(entry.getKey().getSellPrice()*amount);
                getGame().getCurrentTurn().getResourceCount().put(entry.getKey(), entry.getValue()-amount);
                return StoreMenuResponds.SOLD.getText()+resources.getName();
            }
        }
        return StoreMenuResponds.INVALID.getText()+"item's name";
    }
}
