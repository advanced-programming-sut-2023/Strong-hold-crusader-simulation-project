package org.example.controller;

import org.example.model.Game;
import org.example.model.Map;
import org.example.model.User;

public class Controller {
    protected static Map map;
    private static Game game;
    public static Game getGame() {
        return game;
    }
    public static Map getMap() {
        return map;
    }
    public static void setMap(Map map) {
        Controller.map = map;
    }
    public static boolean stayLoggedInCheck() {
        return User.isStayLoggedIn();
    }
    public static String captcha() {
        return null;
    }
    public static String doubleQuoteRemover(String string) {
        if (string.charAt(0) == '\"')
            return string.substring(1,string.length() - 1);
        return string;
    }

    public static boolean checkPasswordFormat(String password) {
        return
                password.length() >= 6
                && password.matches(".*[A-Z].*")
                && password.matches(".*[0-9]")
                && password.matches(".*[a-z].*")
                && password.matches(".*[^0-9A-Za-z].*");
    }
}
