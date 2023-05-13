package org.example.controller;

import org.example.model.Game;
import org.example.model.Government;
import org.example.model.Map;
import org.example.model.User;

import java.util.ArrayList;

public class MainMenuController extends Controller {
    public static String logout() {
        User.setLoggedInUser(null);
        User.stayLoggedIn(false);
        return "user logged out successfully!";
    }
    public static String exit() {
        User.setLoggedInUser(null);
        User.setExiting(true);
        return "exited the game successfully";
    }
    public static String startGame(ArrayList<User> users, int mapSize) {
        currentGame = new Game(new Map(mapSize));
        currentGame.getPlayers().add(User.getLoggedInUser());
        Government.getGovernments().add(new Government(User.getLoggedInUser()));
        for (User user : users) {
            currentGame.getPlayers().add(user);
            Government.getGovernments().add(new Government(user));
        }
        currentGame.setCurrentTurn(Government.getGovernments().get(0));
        return "game successfully created";
    }
}
