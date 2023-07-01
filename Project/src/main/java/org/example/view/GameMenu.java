package org.example.view;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.controller.Controller;
import org.example.model.Map;
import org.example.model.MapCell;
import org.example.model.Texture;

import java.util.Arrays;

public class GameMenu extends Application {

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
    private final Map currentMap;
    private Group[][] blockImages;
    ScrollPane scrollPane;
    GridPane gridPane;
    BorderPane borderPane;

    public GameMenu() {
        this.currentMap = Controller.getCurrentGame().getMap();
        this.blockImages = new Group[100][100];
    }

    @Override
    public void start(Stage stage) throws Exception {
        borderPane = new BorderPane();
        gridPane = new GridPane();
        initializeGridPane(gridPane);
        scrollPane = new ScrollPane(gridPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);
        handleSelecting();
        handleShortcut();
        borderPane.setCenter(scrollPane);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    private void handleShortcut() {
        scrollPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!keyEvent.isControlDown())
                    return;
                String keyName = keyEvent.getCode().getName();
                if (keyName.equals("Equals"))
                    zoom();
                else if (keyName.equals("Minus"))
                    antiZoom();
            }
            private void zoom() {
                if (gridPane.getScaleX() > 2)
                    return;
                gridPane.setScaleX(gridPane.getScaleX() * 1.1d);
                gridPane.setScaleY(gridPane.getScaleY() * 1.1d);
            }
            private void antiZoom() {
                if (gridPane.getScaleX() < 1.1)
                    return;
                gridPane.setScaleX(gridPane.getScaleX() / 1.1);
                gridPane.setScaleY(gridPane.getScaleY() / 1.1);
            }
        });
    }

    private void handleSelecting() {
        final Rectangle[] mouseDrag = {null};
        final double[] startX = new double[1];
        final double[] startY = new double[1];
        gridPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!mouseEvent.isControlDown())
                    return;
                scrollPane.setPannable(false);
                startX[0] = mouseEvent.getX();
                startY[0] = mouseEvent.getY();
                mouseDrag[0] = new Rectangle(startX[0], startY[0], 0, 0);
                mouseDrag[0].setVisible(false);
                borderPane.getChildren().add(mouseDrag[0]);
            }
        });
        gridPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseDrag[0] == null)
                    return;
                double dragX = mouseEvent.getX();
                double dragY = mouseEvent.getY();
                mouseDrag[0].setX(Math.min(startX[0], dragX));
                mouseDrag[0].setY(Math.min(startY[0], dragY));
                mouseDrag[0].setWidth(Math.abs(startX[0] - dragX));
                mouseDrag[0].setHeight(Math.abs(startY[0] - dragY));
            }
        });
        gridPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseDrag[0] == null)
                    return;
                Bounds selectedBounds = mouseDrag[0].getBoundsInParent();
                for (int i = 0; i < gridPane.getChildren().size(); i++) {
                    Node cellGrid = gridPane.getChildren().get(i);
                    if (!(cellGrid instanceof Region))
                        continue;
                    ((Region) cellGrid).setBorder(null);
                    Bounds bounds = cellGrid.getBoundsInParent();
                    if (bounds.intersects(selectedBounds))
                        ((Region) cellGrid).setBorder(new Border(new BorderStroke
                                (Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                }
                borderPane.getChildren().remove(mouseDrag[0]);
                mouseDrag[0] = null;
                scrollPane.setPannable(true);
            }
        });
    }

    private void handleHovering(Group block, MapCell mapCell){
        Tooltip tooltip = new Tooltip(mapCell.toString());
        PauseTransition pause = new PauseTransition(Duration.millis(2500));
        block.setOnMouseEntered(event -> {
            pause.playFromStart();
        });
        block.setOnMouseExited(event -> {
            pause.stop();
            tooltip.hide();
        });
        pause.setOnFinished(event -> {
            Point2D screenPos = block.localToScreen
                    (block.getBoundsInLocal().getMaxX(), block.getBoundsInLocal().getMaxY());
            tooltip.show(block, screenPos.getX(), screenPos.getY() - 70);
        });
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setGridLinesVisible(true);
        gridPane.getTransforms().add(new Scale(1,1));
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
        Image[] textureImages = new Image[9];
        for (int i = 0; i < 9; i++) {
            textureImages[i] = new Image(getClass().getResource(Texture.values()[i].getImagePath()).toExternalForm());
        }
        for (int i = 0; i < mapSize; i += 1) {
            for (int j = 0; j < mapSize; j += 1) {
                Pane pane = new Pane();
                gridPane.add(pane, i, j);
                MapCell mapCell = currentMap.getCells()[i][j];
                ImageView imageView = new ImageView(textureImages[Arrays.asList(Texture.values()).indexOf
                        (mapCell.getTexture())]);
                imageView.setOpacity(0.8);
                imageView.setFitWidth(98);
                imageView.setFitHeight(98);
                blockImages[i][j] = new Group(imageView);
                gridPane.add(blockImages[i][j], i, j);
                handleHovering(blockImages[i][j], mapCell);
            }
        }
    }
}
