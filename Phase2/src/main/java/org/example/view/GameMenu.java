package org.example.view;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCombination;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.model.Map;

import java.util.Optional;

public class GameMenu extends Application {
    int jjjj=0;
    public static Map currentMap = new Map(100);
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
    Stage stage;
    GridPane gridPanew=new GridPane();

    Clipboard clipboard = Clipboard.getSystemClipboard();
    ClipboardContent content = new ClipboardContent();

    GridPane gridPane;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
        gridPane = new GridPane();
        Pane pane1=new Pane();

        BorderPane borderPane=new BorderPane(gridPane);



        initializeGridPane(gridPane);
  //      Undo.newMap(gridPane);
        ScrollPane scrollPane = new ScrollPane(borderPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);


        Image image = new Image(getClass().getResource("/Images/Others/menu/menu2.png").toExternalForm());
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(1537);
        imageView.setFitHeight(250);
        imageView.setY(600);

        Group group=new Group();

        pane1.getChildren().add(scrollPane);
        pane1.getChildren().add(imageView);

        Pane pane=Under.asleKar(gridPane,scrollPane);
        pane.setPrefWidth(200);
        pane.setLayoutY(700);

        Pane rep= Reports.reports();
        rep.setPrefWidth(200);
        rep.setLayoutY(700);
        pane.setVisible(true);
        rep.setVisible(false);


        group.getChildren().addAll(pane1,pane,rep,getMiniMap(scrollPane));
        scrollPane.setPrefSize(1537, 865);
        scrollPane.requestFocus();
        Scene scene = new Scene(group);

        KeyCombination keyComb = KeyCombination.keyCombination("Ctrl+Z");
        scene.getAccelerators().put(keyComb, () -> {
            gridPane.getChildren().remove(gridPane.getChildren().size()-1);

        });

        KeyCombination keyComb1 = KeyCombination.keyCombination("Ctrl+D");
        scene.getAccelerators().put(keyComb1, () -> {
            if (Under.selectedThing!=null){
                gridPane.getChildren().remove(Under.selectedThing);
            }
        });

        KeyCombination keyComb2 = KeyCombination.keyCombination("Ctrl+A");
        scene.getAccelerators().put(keyComb2, () -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Options");
            alert.setHeaderText("Quit the mission");
            alert.setResizable(false);
            alert.setContentText("Do you want to continue the mission?");

            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.CANCEL) {
                stage.close();
            }
        });

        KeyCombination keyComb3 = KeyCombination.keyCombination("Ctrl+B");
        scene.getAccelerators().put(keyComb3, () -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Briefing");
            alert.setHeaderText("Briefing");
            alert.setResizable(true);
            alert.setContentText("Do you want to restart the mission?");

            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.OK) {
                borderPane.getChildren().clear();
                for (int i=currentMap.getSize()*currentMap.getSize();i<gridPane.getChildren().size(); i++){
                    gridPane.getChildren().remove(i);
                }
            }
        });

        KeyCombination report = KeyCombination.keyCombination("Ctrl+Q");
        scene.getAccelerators().put(report, () -> {
            pane.setVisible(!pane.isVisible());
            rep.setVisible(!rep.isVisible());
        });

        KeyCombination copy = KeyCombination.keyCombination("Ctrl+C");
        scene.getAccelerators().put(copy, () -> {
            if (Under.selectedThing!=null){
                Image image1=((ImageView) Under.selectedThing).getImage();
                content.putImage(image1);
                clipboard.setContent(content);

            }
        });

        KeyCombination paste = KeyCombination.keyCombination("Ctrl+V");
        scene.getAccelerators().put(paste, () -> {

        });

        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }


    private ImageView getMiniMap(ScrollPane scrollPane) {
        ImageView miniMap = new ImageView(new Image(getClass().getResource("/Images/textures/baseGround.jpg").toExternalForm(), 142, 142, false, false));
        miniMap.setOnMouseClicked(mouseEvent -> {
            double xRatio = mouseEvent.getX() / miniMap.getBoundsInLocal().getWidth();
            double yRatio = mouseEvent.getY() / miniMap.getBoundsInLocal().getHeight();
            scrollPane.setVvalue(xRatio);
            scrollPane.setHvalue(yRatio);
        });
        miniMap.setX(1300);
        miniMap.setY(700);
        return miniMap;
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
                //System.out.println(imageView.getImage().getUrl().toString().equals(getClass().getResource("/Images/textures/baseGround.jpg").toString()));
                imageView.getOnMouseClicked();
                imageView.setOpacity(0.8);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                gridPane.add(imageView, i, j, 1, 1);
            }
        }
    }
}