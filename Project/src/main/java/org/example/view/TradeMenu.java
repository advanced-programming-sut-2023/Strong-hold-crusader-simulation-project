package org.example.view;

import org.example.controller.TradeMenuController;
import org.example.view.commands.TradeMenuCommands;

import java.util.Scanner;

public class TradeMenu extends Menu {
    public TradeMenu(Scanner scanner) {
        super(scanner);
    }
    @Override
    public void run() {
        System.out.println("Trade Menu");
        System.out.println(TradeMenuController.tradeNotification());
        while (true) {
            input = scanner.nextLine();

            if (input.equals("trade list"))
                System.out.println(TradeMenuController.tradeList());
            else if (input.equals("trade history"))
                System.out.println(TradeMenuController.tradeHistory());
            else if ((matcher = TradeMenuCommands.getMatcher(input,TradeMenuCommands.TRADE)) != null)
                System.out.println(TradeMenuController.trade(matcher));
            else if ((matcher = TradeMenuCommands.getMatcher(input,TradeMenuCommands.TRADE_ACCEPT)) != null)
                System.out.println(TradeMenuController.tradeAccept(matcher));
            else if (input.equals("back")) {
                return;
            } else System.out.println("invalid command");
        }
    }
}
