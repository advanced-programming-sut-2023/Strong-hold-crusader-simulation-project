package org.example.view;

import org.example.controller.BuildingMenuController;

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
        }
    }
}
