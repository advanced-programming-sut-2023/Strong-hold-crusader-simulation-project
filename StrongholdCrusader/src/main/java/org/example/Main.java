package org.example;

import org.example.view.LoginMenu;
import org.example.view.SignupMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SignupMenu signupMenu = new SignupMenu(scanner);
        signupMenu.run();
    }
}