package org.example.view;

import org.example.controller.MainMenuController;
import org.example.model.User;
import org.example.view.commands.MainMenuCommands;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu extends Menu {
    public MainMenu(Scanner scanner) {
        super(scanner);
    }
    @Override
    public void run() {
        System.out.println("Main Menu");
        while (true) {
            input = scanner.nextLine();

            if (MainMenuCommands.getMatcher(input, MainMenuCommands.LOGOUT) != null) {
                System.out.println(MainMenuController.logout());
                return;
            } else if (MainMenuCommands.getMatcher(input, MainMenuCommands.EXIT) != null) {
                System.out.println(MainMenuController.exit());
                return;
            } else if (MainMenuCommands.getMatcher(input, MainMenuCommands.START_GAME) != null) {
//
//                System.out.println(result);
//                if (result.equals("game successfully created")) {
////                    GameMenu gameMenu = new GameMenu(scanner);
////                    gameMenu.run();
//                    System.out.println("Main Menu");
//                }
            } else System.out.println("invalid command");
        }
    }
    private String checkPlayerCount(String number) {

        int intNumber;
        try {
            intNumber = Integer.parseInt(number);
        } catch (Exception e) {
            return "invalid number of players";
        }
        if (intNumber > 8 || intNumber < 2)
            return "invalid number of players";
        if (intNumber > User.getAllUsers().size())
            return "these many players doesn't exist!";
        else return "success";
    }
}
