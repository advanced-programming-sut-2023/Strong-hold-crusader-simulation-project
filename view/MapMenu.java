package view;

<<<<<<< HEAD
import java.util.Scanner;

public class MapMenu extends Menu {
    public MapMenu(Scanner scanner, int x, int y) {
        super(scanner);
    }
    @Override
    public void run() {

=======
import model.Map;
import model.MapCell;
import view.commands.MapMenuCommands;

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

            if ((matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.MOVE_MAP)) != null)
                moveMap();
        }
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
        this.x = possibleX(this.x + number * xMove);
        this.y = possibleY(this.y + number * yMove);
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
                    MapCell cell = map.getCells()[x - 8 + j / 7][y - 4 + i / 4];
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
>>>>>>> parent of 790ce96 (map menu complete)
    }
}
