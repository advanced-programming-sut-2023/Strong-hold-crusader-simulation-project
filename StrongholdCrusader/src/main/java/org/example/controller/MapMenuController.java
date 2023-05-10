package org.example.controller;

import org.example.model.Map;

public class MapMenuController extends Controller {
    public static String showDetails(int x, int y) {
        return currentGame.getMap().getCells()[x - 1][y - 1].toString();
    }

}
