package org.example.view;

import org.example.controller.SignupMenuController;
import org.example.model.InputOut.Regex;
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
            if (Regex.createUser.getMatcher(command) != null) {
                System.out.println(SignupMenuController.createUser(Regex.createUser.getMatcher(command), scanner));
            } else if (Regex.save.getMatcher(command) != null) {
                User.saveFile();
            } else if (Regex.read.getMatcher(command) != null) {
                User.readFile();
            } else if (Regex.login.getMatcher(command) != null) {
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
