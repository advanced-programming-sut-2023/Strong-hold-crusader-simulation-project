package org.example.view;

import org.example.controller.MapMenuController;
import org.example.model.Map;
import org.example.model.MapCell;
import org.example.view.commands.GameMenuCommands;
import org.example.view.commands.MapMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu extends Menu {
    private int middleX, middleY;
    private final Map map;
    public MapMenu(Scanner scanner, int middleX, int middleY, Map map) {
        super(scanner);
        this.middleX = possibleX(middleX);
        this.middleY = possibleY(middleY);
        this.map = map;
    }
    @Override
    public void run() {
        MapMenuController.setMap(map);
        System.out.println("Map Menu");
        printMap();
        while (true) {
            input = scanner.nextLine();

            if ((matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.MOVE_MAP)) != null)
                moveMap();
            else if (MapMenuCommands.getMatcher(input, MapMenuCommands.SHOW_DETAILS) != null)
                System.out.println(showDetails());
            else if (input.equals("exit")) {
                MapMenuController.setMap(null);
                return;
            }
            else System.out.println("invalid command");
        }
    }

    private String showDetails() {
        Matcher xMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_X);
        Matcher yMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_Y);
        assert xMatcher != null;
        int x = Integer.parseInt(xMatcher.group("mapX"));
        assert yMatcher != null;
        int y = Integer.parseInt(yMatcher.group("mapY"));
        if(x < middleX - 7 || x > middleX + 7 || y < middleY - 3 || y > middleY + 3)
            return "invalid coordinates";
        return MapMenuController.showDetails(x, y);
    }

    private void moveMap() {
        int number = 1;
        if(matcher.group("number") != null)
            number = Integer.parseInt(matcher.group("number"));
        int xMove, yMove;
        xMove = yMove = 0;
        if (MapMenuCommands.getMatcher(input, MapMenuCommands.UP) != null)
            yMove++;
        if (MapMenuCommands.getMatcher(input, MapMenuCommands.DOWN) != null)
            yMove--;
        if (MapMenuCommands.getMatcher(input, MapMenuCommands.RIGHT) != null)
            xMove++;
        if (MapMenuCommands.getMatcher(input, MapMenuCommands.LEFT) != null)
            xMove--;
        this.middleX = possibleX(this.middleX + number * xMove);
        this.middleY = possibleY(this.middleY + number * yMove);
        printMap();
    }

    private void printMap() {
        for (int i = 0; i < 7 * 4 + 1; i++) {
            for (int j = 0; j < 15 * 7 + 1; j++) {
                if (i % 4 == 0) {
                    System.out.print("-");
                    continue;
                }
                if (j % 7 == 0)
                    System.out.print("|");
                else {
                    MapCell cell = map.getCells()[middleX - 8 + j / 7][middleY - 4 + i / 4];
                    System.out.print(cell.getTexture().getColor());
                    System.out.print(cell.getCellState());
                }
                System.out.print("\033[0m");
            }
            System.out.println();
        }
    }

    private int possibleX(int x) {
        if (x < 8)
            x = 8;
        else if (x > map.getSize() - 7)
            x = map.getSize() - 7;
        return x;
    }

    private int possibleY(int y) {
        if(y < 4)
            y = 4;
        else if(y > map.getSize() - 3)
            y = map.getSize() - 3;
        return y;
    }
}
