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
            }
            else if (MainMenuCommands.getMatcher(input, MainMenuCommands.EXIT) != null) {
                System.out.println(MainMenuController.exit());
                return;
            }
            else if (MainMenuCommands.getMatcher(input, MainMenuCommands.SETTINGS) != null) {
                ProfileMenu profileMenu = new ProfileMenu(scanner);
                profileMenu.run();
            }
            else if (MainMenuCommands.getMatcher(input, MainMenuCommands.START_GAME) != null) {
                String result = startGame();
                System.out.println(result);
                if (result.equals("game successfully created")){
                    GameMenu gameMenu = new GameMenu(scanner);
                    gameMenu.run();
                    System.out.println("Main Menu");
                }
            }
            else System.out.println("invalid command");
        }
    }

    private String startGame() {
        System.out.println("choose how many opponents you want to play against" +
                "\nminimum players in a game is 2\nmaximum players in a game is 8");
        String playerCount = scanner.nextLine();
        String numberResult;
        if(!(numberResult = checkPlayerCount(playerCount)).equals("success"))
            return numberResult;
        System.out.println("enter the username of people you want to play with:");
        ArrayList<User> users = new ArrayList<>();
        users.add(User.getLoggedInUser());
        for (int i = 0; i < Integer.parseInt(playerCount) - 1; i++) {
            String username = scanner.nextLine();
            User user;
            if ((user = User.getUserByUsername(username)) == null){
                System.out.println("this username doesn't exist!\nplease enter another username instead");
                i--;
                continue;
            }
            users.add(user);
        }
        System.out.println("choose size of the map you want to play in:\n1. 200\n2. 400");
        String mapSize = scanner.nextLine();
        int intSize;
        try {
            intSize = Integer.parseInt(mapSize);
        } catch (Exception e) {
            return "map size has to be a number";
        }
        while (intSize != 200 && intSize != 400) {
            System.out.println("map size must either be 200 or 400\nplease enter your preferred size again:");
            mapSize = scanner.nextLine();
            intSize = Integer.parseInt(mapSize);
        }
        return MainMenuController.startGame(users, intSize);
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
