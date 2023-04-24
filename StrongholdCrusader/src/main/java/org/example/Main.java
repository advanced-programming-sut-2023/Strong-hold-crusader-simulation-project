package StrongholdCrusader.src.main.java.org.example;

import StrongholdCrusader.src.main.java.org.example.view.LoginMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginMenu loginMenu = new LoginMenu(scanner);
        loginMenu.run();
    }
}