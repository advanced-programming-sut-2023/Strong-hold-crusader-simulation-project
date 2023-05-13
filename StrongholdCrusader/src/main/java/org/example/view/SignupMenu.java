package org.example.view;

import org.example.controller.SignupMenuController;
import org.example.view.commands.SignupMenuCommands;
import org.example.model.User;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SignupMenu extends Menu {
    public SignupMenu(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void run() {
        String command;
        while (true) {
            command = scanner.nextLine();
            if (SignupMenuCommands.createUser.getMatcher(command) != null) {
                System.out.println(SignupMenuController.createUser(SignupMenuCommands.createUser.getMatcher(command), scanner));
            } else if (SignupMenuCommands.save.getMatcher(command) != null) {
                User.saveFile();
            } else if (SignupMenuCommands.read.getMatcher(command) != null) {
                User.readFile();
            } else if (SignupMenuCommands.login.getMatcher(command) != null) {
                System.out.println("Login Menu");
                return;
            } else {
                System.out.println("hey , you better watch your moves paul !!!");
            }
        }
    }

    private void testData(Matcher matcher) {
        matcher.find();
        System.out.println("username :" + matcher.group("username"));
        System.out.println("password :" + matcher.group("password"));
        System.out.println("password confirm :" + matcher.group("confirm"));
        System.out.println("slogan :" + matcher.group("slogan"));
        System.out.println("nickname :" + matcher.group("nickname"));
        System.out.println("email :" + matcher.group("email"));
    }
}
