package org.example;

import org.example.model.User;
import org.example.view.LoginMenu;
import org.example.view.commands.GameMenuCommands;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User.readFile();
        Scanner scanner = new Scanner(System.in);
        LoginMenu loginMenu = new LoginMenu(scanner);
        loginMenu.run();
        User.saveFile();
    }
}