import controller.Controller;
import model.User;
import view.LoginMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User.getAllUsers().add(new User("Salam","password"));
        Scanner scanner = new Scanner(System.in);
        LoginMenu loginMenu = new LoginMenu(scanner);
        loginMenu.run();
    }
}