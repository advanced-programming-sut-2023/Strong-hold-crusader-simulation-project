package StrongholdCrusader.src.main.java.org.example.view;

import StrongholdCrusader.src.main.java.org.example.controller.MainMenuController;
import StrongholdCrusader.src.main.java.org.example.view.commands.MainMenuCommands;

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

            if ((matcher = MainMenuCommands.getMatcher(input, MainMenuCommands.LOGOUT)) != null) {
                System.out.println(MainMenuController.logout());
                return;
            }
        }
    }
}
