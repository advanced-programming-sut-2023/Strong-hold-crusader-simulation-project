package org.example.controller;

import org.example.model.Game;
import org.example.model.Map;
import org.example.model.User;
import org.example.model.buildings.Building;

public class Controller {
    protected static Game currentGame;
    protected static Building selectedBuilding = null;

    public static Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public static void setSelectedBuilding(Building selectedBuilding) {
        Controller.selectedBuilding = selectedBuilding;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }
    public static void setCurrentGame(Game currentGame) {
        Controller.currentGame = currentGame;
    }
    protected static Map map;
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
