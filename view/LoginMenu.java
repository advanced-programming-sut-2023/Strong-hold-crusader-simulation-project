package view;

import controller.LoginMenuController;
import view.commands.LoginMenuCommands;

import java.util.Scanner;

public class LoginMenu extends Menu {
    public LoginMenu(Scanner scanner) {
        super(scanner);
    }
    @Override
    public void run() {
        System.out.println("Login Menu");
        while (true) {
            input = scanner.nextLine();

            if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.SIGNUP)) != null) {
                SignupMenu signupMenu = new SignupMenu(scanner);
                signupMenu.run();
            } else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.LOGIN)) != null) {
                String result = LoginMenuController.loginUser(input);
                System.out.println(result);
                if (result.equals("user logged in successfully!")) {
                    MainMenu mainMenu = new MainMenu(scanner);
                    mainMenu.run();
                }
            }
            else if (input.equals("exit"))
                return;
        }
    }
}
