package view;

<<<<<<< HEAD
=======
import controller.GameMenuController;
import model.Game;
import model.Map;
import view.commands.GameMenuCommands;

>>>>>>> parent of 790ce96 (map menu complete)
import java.util.Scanner;

public class GameMenu extends Menu {
    public GameMenu(Scanner scanner) {
        super(scanner);
    }
    @Override
    public void run() {

<<<<<<< HEAD
=======
            if (GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_MAP) != null)
                showMap();
        }
    }
    private void showMap() {
        Matcher xMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_X);
        Matcher yMatcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAP_Y);
        int x = Integer.parseInt(xMatcher.group("mapX"));
        int y = Integer.parseInt(yMatcher.group("mapY"));
        if(x < 0 || x > currentMap.getSize() || y < 0 || y > currentMap.getSize()) {
            System.out.println("invalid coordinates");
            return;
        }
        MapMenu mapMenu = new MapMenu(scanner, x, y, currentMap);
        mapMenu.run();
>>>>>>> parent of 790ce96 (map menu complete)
    }
}
