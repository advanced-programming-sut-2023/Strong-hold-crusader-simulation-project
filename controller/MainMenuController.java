package controller;

import model.User;

import java.util.regex.Matcher;

public class MainMenuController extends Controller {
    public static String logout() {
        User.setLoggedInUser(null);
        User.stayLoggedIn(false);
        return "user logged out successfully!";
    }
    public static String startGame(Matcher matcher) {
        return null;
    }
}
