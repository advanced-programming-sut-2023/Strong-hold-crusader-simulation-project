package org.example;

import org.example.view.LoginMenu;
import org.example.view.commands.GameMenuCommands;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginMenu loginMenu = new LoginMenu(scanner);
        loginMenu.run();
    }
}