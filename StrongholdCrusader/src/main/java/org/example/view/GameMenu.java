package org.example.view;

import org.example.controller.Controller;
import org.example.controller.GameMenuController;
import org.example.model.Map;
import org.example.view.commands.GameMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu extends Menu {
    private Map currentMap;
    public GameMenu(Scanner scanner) {
        super(scanner);
    }
    @Override
    public void run() {
        currentMap = Controller.getCurrentGame().getMap();
        System.out.println("Game Menu");
        while (true) {
            input = scanner.nextLine();

            if (GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_MAP) != null)
                showMap();
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.DROP_BUILDING)) != null)
                System.out.println(GameMenuController.dropBuilding(input));
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SELECT_BUILDING)) != null) {
                String result = GameMenuController.selectBuilding(input);
                System.out.println(result);
                if (result.matches("you have.+")){
                    if (result.equals("you have successfully selected the Market building")) {
                        StoreMenu storeMenu = new StoreMenu(scanner);
                        storeMenu.run();
                        Controller.setSelectedBuilding(null);
                    } else {
                        BuildingMenu buildingMenu = new BuildingMenu(scanner);
                        buildingMenu.run();
                        Controller.setSelectedBuilding(null);
                    }
                }
            } else System.out.println("invalid command");
        }
    }
    private void showMap() {
        Matcher xMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_X);
        Matcher yMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_Y);
        assert xMatcher != null;
        int x = Integer.parseInt(xMatcher.group("mapX"));
        assert yMatcher != null;
        int y = Integer.parseInt(yMatcher.group("mapY"));
        if(x < 0 || x > currentMap.getSize() || y < 0 || y > currentMap.getSize()) {
            System.out.println("invalid coordinates");
            return;
        }
        MapMenu mapMenu = new MapMenu(scanner, x, y, currentMap);
        mapMenu.run();
    }
}
