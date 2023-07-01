package org.example.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.model.User;

import java.util.Optional;

public class Scoreboard extends Application {
    private static Stage stage;
    private ScrollPane scrollPane;
    private static Pane pane;
    private static User currentUser;
    public Scoreboard(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Pane pane = FXMLLoader.load(Scoreboard.class.getResource("/FXML/Scoreboard.fxml"));
        this.pane = pane;
        this.scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        scrollPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT , null , null)));
        pane.setBackground(Signup.BackgroundMaker(new Image(Scoreboard.class.getResource("/Images/LoginBackground1.jpg").toExternalForm())));
        VBox vBox = new VBox(); vBox.setSpacing(15);
        vBox.setLayoutX(600); vBox.setLayoutY(150);
        scrollPane.setPrefHeight(350); scrollPane.setPrefWidth(572);
        scrollPane.setLayoutX( 450); scrollPane.setLayoutY(40);
        for (int i = 0 ; i < User.getAllUsers().size() ; i++){
            HBox hBox = new HBox(); hBox.setSpacing(10);
            VBox profile = new VBox(); profile.setAlignment(Pos.CENTER);
            VBox username = new VBox(); username.setAlignment(Pos.CENTER);
            Circle circle = new Circle(30);
            circle.setFill(new ImagePattern(new Image(User.getAllUsers().get(i).getProfilePicture().toExternalForm())));
            int finalI = i;
            circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    errorAlert.setHeaderText("changing profile.");
                    errorAlert.setContentText("if you want to set this avatar as your profile picture click ok.");
                    Optional<ButtonType> result = errorAlert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK){
                        currentUser.setProfilePicture(User.getAllUsers().get(finalI).getProfilePicture());
                    }
                }
            });
            profile.getChildren().add(circle);
            StackPane stackPane = new StackPane();
            Text text = new Text("username : " + User.getAllUsers().get(i).getUsername() +
                    "   high score : " + User.getAllUsers().get(i).getHighScore() +  "   rank : " + getRank(i) );
            text.setFont(Font.font(20)); text.setFill(Color.BROWN);
            ImageView iv = new ImageView(Scoreboard.class.getResource("/Images/LabelBackground1.jpg").toExternalForm());
            iv.setFitHeight(30); iv.setFitWidth(500);
            stackPane.getChildren().add(iv); stackPane.getChildren().add(text);
            if (User.getAllUsers().get(i).getUsername().equals(currentUser.getUsername())){
                text.setFill(Color.GOLD);
            }
            hBox.getChildren().addAll(profile , stackPane);
            vBox.getChildren().add(hBox);
        }
        scrollPane.setContent(vBox);
        Button back = new Button("back"); back.setBackground(Signup.gray); back.setMaxWidth(700);
        VBox whole = new VBox(); whole.getChildren().addAll(scrollPane , back); whole.setSpacing(5);
        whole.setLayoutX(600); whole.setLayoutY(150);
        whole.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String name = keyEvent.getCode().getName();
                if (name.equals("Esc")){
                    Main main = new Main(currentUser);
                    try {
                        main.start(Main.stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Main main = new Main(currentUser);
                try {
                    main.start(Main.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pane.getChildren().add(whole);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    public static String getRank(int i) {
        switch (i){
            case 0:{
                return "1st";
            }
            case 1:{
                return "2nd";
            }
            case 2:{
                return "3rd";
            }
            default:{
                return i+"th";
            }
        }
    }
}
