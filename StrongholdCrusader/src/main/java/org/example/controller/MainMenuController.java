package StrongholdCrusader.src.main.java.org.example.controller;

import StrongholdCrusader.src.main.java.org.example.model.User;

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
