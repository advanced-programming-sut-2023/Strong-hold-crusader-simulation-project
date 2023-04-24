package org.example.model;

import java.util.ArrayList;

public class Game {
    private ArrayList<User> players;
    private final Map map;
    private int turnNumber = 0;
    private Government currentTurn;

    public Game(Map map) {
        this.map = map;
    }

    public ArrayList<User> getPlayers() {
        return players;
    }

    public Map getMap() {
        return map;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public Government getCurrentTurn() {
        return currentTurn;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public void setCurrentTurn(Government government) {
        this.currentTurn = government;
    }
}
