package org.example.view;

import org.example.controller.StoreMenuController;
import org.example.view.commands.LoginMenuCommands;
import org.example.view.commands.StoreMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class StoreMenu extends Menu{
    public StoreMenu(Scanner scanner) {
        super(scanner);
    }
    @Override
    public void run() {
        System.out.println("Market");
        while (true){
            input = scanner.nextLine();

            if (StoreMenuCommands.getMatcher(input, StoreMenuCommands.SHOW_PRICE_LIST) != null)
                System.out.println(StoreMenuController.priceList());
            else if (StoreMenuCommands.getMatcher(input, StoreMenuCommands.BUY) != null)
                System.out.println(buyResource());
            else if (StoreMenuCommands.getMatcher(input,StoreMenuCommands.SELL) != null)
                System.out.println(sellResource());
            else if (input.equals("exit market")) {
                return;
            }
            else System.out.println("invalid command");
        }
    }

    private String sellResource() {
        Matcher itemMatcher = StoreMenuCommands.getMatcher(input, StoreMenuCommands.ITEM);
        Matcher amountMatcher = StoreMenuCommands.getMatcher(input, StoreMenuCommands.AMOUNT);
        assert itemMatcher != null;
        String item = itemMatcher.group("item");
        assert amountMatcher != null;
        int amount = Integer.parseInt(amountMatcher.group("amount"));
        return StoreMenuController.sell(item, amount);
    }

    private String buyResource() {
        Matcher itemMatcher = StoreMenuCommands.getMatcher(input, StoreMenuCommands.ITEM);
        Matcher amountMatcher = StoreMenuCommands.getMatcher(input, StoreMenuCommands.AMOUNT);
        assert itemMatcher != null;
        String item = itemMatcher.group("item");
        assert amountMatcher != null;
        int amount = Integer.parseInt(amountMatcher.group("amount"));
        return StoreMenuController.buy(item, amount);
    }
}
