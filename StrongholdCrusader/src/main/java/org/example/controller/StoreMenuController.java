package org.example.controller;

import org.example.model.Government;
import org.example.model.Resources;

import java.util.regex.Matcher;

public class StoreMenuController extends Controller {
    public static String priceList() {
        String list = "";
        for (Resources resource : currentGame.getCurrentTurn().getResourceCount().keySet()) {
            list += resource.getName() + ":\n\tbuying price: " + resource.getBuyPrice()
                    + "\n\tselling price: " + resource.getSellPrice() + "\n\tinventory: "
                    + currentGame.getCurrentTurn().getResourceCount().get(resource) + "\n";
        }
        return list;
    }

    public static String buy(String item, int amount) {
        Resources resource = Resources.getResourceByName(item);
        if (resource == null)
            return "invalid item";
        if (amount < 1)
            return "invalid amount";
        Government buyer = currentGame.getCurrentTurn();
        if (resource.getBuyPrice() * amount > buyer.getBalance())
            return "you don't have enough gold to buy this item!";
        buyer.getResourceCount().replace(resource, buyer.getResourceCount().get(resource) + amount);
        buyer.changeBalance(-amount * resource.getBuyPrice());
        return "you have successfully purchased " + amount + " " + resource.getName() + "s";
    }

    public static String sell(String item, int amount) {
        Resources resource = Resources.getResourceByName(item);
        if (resource == null)
            return "invalid item";
        if (amount < 1)
            return "invalid amount";
        Government seller = currentGame.getCurrentTurn();
        if (seller.getResourceCount().get(resource) < amount)
            return "you don't have enough of this item!";
        seller.getResourceCount().replace(resource, seller.getResourceCount().get(resource) - amount);
        seller.changeBalance(amount * resource.getSellPrice());
        return "you have successfully sold " + amount + " " + resource.getName() + "s";
    }
}
