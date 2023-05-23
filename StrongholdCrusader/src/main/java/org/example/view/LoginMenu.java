package org.example.view;

import org.example.controller.Controller;
import org.example.model.User;
import org.example.view.commands.LoginMenuCommands;
import org.example.controller.LoginMenuController;

import java.util.Scanner;

public class LoginMenu extends Menu {
    public LoginMenu(Scanner scanner) {
        super(scanner);
    }
    @Override
    public void run() {
        if(Controller.stayLoggedInCheck()) {
            MainMenu mainMenu = new MainMenu(scanner);
            mainMenu.run();
            if (User.isExiting()) {
                User.setExiting(false);
                return;
            }
        }
        System.out.println("Login Menu");
        while (true) {
            input = scanner.nextLine();

            if (LoginMenuCommands.getMatcher(input, LoginMenuCommands.SIGNUP) != null) {
                SignupMenu signupMenu = new SignupMenu(scanner);
                signupMenu.run();
            } else if (LoginMenuCommands.getMatcher(input, LoginMenuCommands.LOGIN) != null) {
                String result = LoginMenuController.loginUser(input);
                System.out.println(result);
                if (result.equals("user logged in successfully!")) {
                    MainMenu mainMenu = new MainMenu(scanner);
                    mainMenu.run();
                    if (User.isExiting()) {
                        User.setExiting(false);
                        return;
                    }
                }
            } else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.FORGET_PASS)) != null)
                System.out.println(forgetPassword());
            else if (input.equals("exit"))
                return;
            else System.out.println("invalid command");
        }
    }

    private String forgetPassword() {
        String username = matcher.group("username");
        User user;
        if ((user = User.getUserByUsername(username)) == null)
            return "username doesn't exist";
        System.out.println(User.getSecurityQuestions()[user.getRecoveryQuestion()]);
        String answer = scanner.nextLine();
        if (!user.isSecurityAnswerCorrect(answer))
            return "wrong answer to the security question";
        System.out.println("enter your new password");
        String newPassword = scanner.nextLine();
        while (!Controller.checkPasswordFormat(newPassword)) {
            System.out.println("Your password:\nmust be at least 6 characters\n" +
                    "must contain capital and small letters\n" +
                    "must contain numbers\n" +
                    "must contain characters not mentioned in above categories\n" +
                    "please enter a new password that has all the conditions");
            newPassword = scanner.nextLine();
        }
        System.out.println("enter your new password again");
        String confirmPassword = scanner.nextLine();
        if(!newPassword.equals(confirmPassword))
            return "entered password doesn't match";
        LoginMenuController.recoverPassword(username, newPassword);
        return "Password successfully changed";
    }
}
