package view;

import model.Map;
import model.MapCell;

import java.util.Scanner;

public class MapMenu extends Menu {
    private int x, y;
    private Map map;
    public MapMenu(Scanner scanner, int x, int y, Map map) {
        super(scanner);
        this.x = possibleX(x);
        this.y = possibleY(y);
        this.map = map;
    }
    @Override
    public void run() {
        System.out.println("Map Menu");
        printMap();
        while (true) {
            input = scanner.nextLine();
        }
    }

    private void printMap() {
        for (int i = 0; i < 5 * 4 + 1; i++) {
            for (int j = 0; j < 15 * 7 + 1; j++) {
                if (i % 4 == 0) {
                    System.out.print("-");
                    continue;
                }
                if (j % 7 == 0)
                    System.out.print("|");
                else {
                    MapCell cell = map.getCells()[x - 8 + i / 4][y - 4 + j / 7];
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
