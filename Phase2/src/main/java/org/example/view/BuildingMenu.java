package org.example.view;

import org.example.controller.BuildingMenuController;
import org.example.view.commands.BuildingMenuCommands;

import java.util.Scanner;

public class BuildingMenu extends Menu {
    public BuildingMenu(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void run() {
        while (true) {
            input = scanner.nextLine();

            if (input.equals("exit"))
                return;
            else if (input.equals("repair"))
                System.out.println(BuildingMenuController.repair());
            else if ((matcher = (BuildingMenuCommands.getMatcher(input, BuildingMenuCommands.CREATE_UNIT))) != null)
                System.out.println(BuildingMenuController.createUnit(matcher));
            else System.out.println("invalid command");
        }
    }
}
