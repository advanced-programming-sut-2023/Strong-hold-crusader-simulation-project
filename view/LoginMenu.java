package view;

import controller.LoginMenuController;
import model.User;
import view.commands.LoginMenuCommands;

import java.util.Scanner;

public class LoginMenu extends Menu {
    public LoginMenu(Scanner scanner) {
        super(scanner);
    }
    @Override
    public void run() {
        if(User.isStayLoggedIn()) {
            MainMenu mainMenu = new MainMenu(scanner);
            mainMenu.run();
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
                }
            } else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.FORGET_PASS)) != null)
                forgetPassword();
            else if (input.equals("exit"))
                return;
            else System.out.println("invalid command");
        }
    }

    private void forgetPassword() {
        String username = matcher.group("username");
        User user;
        if ((user = User.getUserByUsername(username)) == null) {
            System.out.println("username doesn't exist");
            return;
        }
        System.out.println(User.getSecurityQuestions()[user.getRecoveryQuestion() - 1]);
        String answer = scanner.nextLine();
        if (!user.isSecurityAnswerCorrect(answer)) {
            System.out.println("wrong answer to the security question");
            return;
        }
        //
        // change password ke baad az inke amir signup menu ro zad zade mishe;
        //
    }
}
