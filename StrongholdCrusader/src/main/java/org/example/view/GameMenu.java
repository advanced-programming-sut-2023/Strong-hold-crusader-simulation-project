package org.example.view;

import org.example.controller.GameMenuController;
import org.example.controller.GameMenuOutputs;
import org.example.model.Game;
import org.example.model.Map;
import org.example.model.User;
import org.example.view.commands.GameMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu extends Menu {
    private Map currentMap;
    public GameMenu(Scanner scanner) {
        super(scanner);
    }
    private User currentUser;
    @Override
    public void run() {
        System.out.println("Game Menu");
        while (true) {
            input = scanner.nextLine();
            if (GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_MAP) != null)
                showMap();
            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.SELECT_UNIT) != null)
                System.out.println(GameMenuController.selectUnit(currentUser , input));
            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.MOVE_UNIT) != null)
                System.out.println(GameMenuController.moveUnitTo(input));
            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.PATROL_UNIT) != null)
                System.out.println(GameMenuController.PatrolUnit(GameMenuCommands.getMatcher(input , GameMenuCommands.PATROL_UNIT)));
            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.STOP_PATROL) != null)
                System.out.println(GameMenuController.StopPatrolUnit());
            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.SET_STATE) != null)
                System.out.println(GameMenuController.setUnitState(input));
            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.ATTACK_ENEMY) != null)
                System.out.println(GameMenuController.Attack(GameMenuCommands.getMatcher(input , GameMenuCommands.ATTACK_ENEMY)));
            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.ATTACK_BYSHOOT) != null)
                System.out.println(GameMenuController.AttackByShoot(input));
            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.CEASEFIRE) != null)
                System.out.println(GameMenuController.ceasefire());
            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.POUR_OIL) != null)
                System.out.println(GameMenuController.pourOil(GameMenuCommands.getMatcher(input , GameMenuCommands.POUR_OIL)));
            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.DIG_TUNNEL) != null)
                System.out.println(GameMenuController.digTunnel(input));
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
