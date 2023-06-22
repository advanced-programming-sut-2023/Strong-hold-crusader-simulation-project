package org.example.view;

import org.example.controller.GovernmentMenuController;
import org.example.view.commands.GovernmentMenuCommands;

import java.util.Scanner;

public class GovernmentMenu extends Menu {
    public GovernmentMenu(Scanner scanner) {
        super(scanner);
    }
    @Override
    public void run() {
        System.out.println("Government Menu");
        while (true) {
            input = scanner.nextLine();

            if (GovernmentMenuCommands.getMatcher(input, GovernmentMenuCommands.SHOW_POP_FACTORS) != null)
                System.out.println(GovernmentMenuController.showPopFactors());
            else if (GovernmentMenuCommands.getMatcher(input, GovernmentMenuCommands.SHOW_POP) != null)
                System.out.println(GovernmentMenuController.showPopularity());
            else if (GovernmentMenuCommands.getMatcher(input, GovernmentMenuCommands.SHOW_FOOD_LIST) != null)
                System.out.println(GovernmentMenuController.showFoodList());
            else if ((matcher = GovernmentMenuCommands.getMatcher(input, GovernmentMenuCommands.FOOD_RATE)) != null)
                System.out.println(GovernmentMenuController.foodRate(matcher));
            else if (GovernmentMenuCommands.getMatcher(input, GovernmentMenuCommands.FOOD_RATE_SHOW) != null)
                System.out.println(GovernmentMenuController.showFoodRate());
            else if ((matcher = GovernmentMenuCommands.getMatcher(input, GovernmentMenuCommands.TAX_RATE)) != null)
                System.out.println(GovernmentMenuController.taxRate(matcher));
            else if (GovernmentMenuCommands.getMatcher(input, GovernmentMenuCommands.TAX_RATE_SHOW) != null)
                System.out.println(GovernmentMenuController.showTaxRate());
            else if ((matcher = GovernmentMenuCommands.getMatcher(input, GovernmentMenuCommands.FEAR_RATE)) != null)
                System.out.println(GovernmentMenuController.fearRate(matcher));
            else if (input.equals("back to Game Menu"))
                return;
            else System.out.println("invalid command");
        }
    }
}
