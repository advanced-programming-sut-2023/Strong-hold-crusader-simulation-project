import controller.Controller;
import model.User;
import view.LoginMenu;
import view.ProfileMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginMenu loginMenu = new LoginMenu(scanner);
        loginMenu.run();
    }
}