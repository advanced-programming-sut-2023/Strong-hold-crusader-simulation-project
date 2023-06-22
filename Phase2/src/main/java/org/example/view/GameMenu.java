package org.example.view;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import org.example.model.Map;

public class GameMenu extends Application {
    private Map currentMap = new Map(100);
//    public GameMenu(Scanner scanner) {
//        super(scanner);
//    }
//    @Override
//    public void run() {
//        currentMap = Controller.getCurrentGame().getMap();
//        System.out.println("Game Menu");
//        while (true) {
//            input = scanner.nextLine();
//
//            if (GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_MAP) != null)
//                showMap();
//            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.SELECT_UNIT) != null)
//                System.out.println(GameMenuController.selectUnit(Controller.getCurrentGame().getCurrentTurn().getOwner() , input));
//            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.MOVE_UNIT) != null)
//                System.out.println(GameMenuController.moveUnitTo(input));
//            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.PATROL_UNIT) != null)
//                System.out.println(GameMenuController.PatrolUnit(GameMenuCommands.getMatcher(input , GameMenuCommands.PATROL_UNIT)));
//            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.STOP_PATROL) != null)
//                System.out.println(GameMenuController.StopPatrolUnit());
//            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.SET_STATE) != null)
//                System.out.println(GameMenuController.setUnitState(input));
//            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.ATTACK_ENEMY) != null)
//                System.out.println(GameMenuController.Attack(GameMenuCommands.getMatcher(input , GameMenuCommands.ATTACK_ENEMY)));
//            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.ATTACK_BYSHOOT) != null)
//                System.out.println(GameMenuController.AttackByShoot(input));
//            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.CEASEFIRE) != null)
//                System.out.println(GameMenuController.ceasefire());
//            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.POUR_OIL) != null)
//                System.out.println(GameMenuController.pourOil(GameMenuCommands.getMatcher(input , GameMenuCommands.POUR_OIL)));
//            else if (GameMenuCommands.getMatcher(input , GameMenuCommands.DIG_TUNNEL) != null)
//                System.out.println(GameMenuController.digTunnel(input));
//            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.DROP_BUILDING)) != null)
//                System.out.println(GameMenuController.dropBuilding(input));
//            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SELECT_BUILDING)) != null) {
//                String result = GameMenuController.selectBuilding(input);
//                System.out.println(result);
//                if (result.matches("you have.+")){
//                    if (result.equals("you have successfully selected the Market building")) {
//                        StoreMenu storeMenu = new StoreMenu(scanner);
//                        storeMenu.run();
//                        Controller.setSelectedBuilding(null);
//                    } else {
//                        BuildingMenu buildingMenu = new BuildingMenu(scanner);
//                        buildingMenu.run();
//                        Controller.setSelectedBuilding(null);
//                    }
//                }
//            } else if (input.equals("enter government menu")) {
//                GovernmentMenu governmentMenu = new GovernmentMenu(scanner);
//                governmentMenu.run();
//                System.out.println("Game Menu");
//            } else if (input.equals("enter Trade menu")) {
//                TradeMenu tradeMenu = new TradeMenu(scanner);
//                tradeMenu.run();
//                System.out.println("Game Menu");
//            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.NEXT_TURN) != null)
//                System.out.println(GameMenuController.nextTurn());
//            else if (input.equals("CHEAT CODE: WIN")) {
//                System.out.println(GameMenuController.endGame());
//                return;
//            } else System.out.println("invalid command");
//        }
//    }


    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);
        Scene scene = new Scene(scrollPane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setGridLinesVisible(true);
        final int mapSize = currentMap.getSize();
        for (int i = 0; i < mapSize; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            RowConstraints rowConst = new RowConstraints();
            colConst.setPrefWidth(100);
            rowConst.setPrefHeight(100);
            colConst.setHalignment(HPos.CENTER);
            rowConst.setValignment(VPos.CENTER);
            gridPane.getColumnConstraints().add(colConst);
            gridPane.getRowConstraints().add(rowConst);
        }
        Image image = new Image(getClass().getResource("/Images/textures/baseGround.jpg").toExternalForm());
        for (int i = 0; i < mapSize; i += 1) {
            for (int j = 0; j < mapSize; j += 1) {
                ImageView imageView = new ImageView(image);
                imageView.setOpacity(0.8);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                gridPane.add(imageView, i, j, 1, 1);
            }
        }
    }
}
