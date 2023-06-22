package org.example.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.model.User;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    private static User currentUser;
    public static Stage stage;
    public static Pane pane;
    private static Timer timer;
    private long lastTrueTime;
    private Label noBalls = Signup.LabelMaker("don't you dear touch my balls kid." , 20 , Color.RED);
    public Main(User currentUser){
        this.currentUser = currentUser;
    }
    @Override
    public void start(Stage stage) throws Exception {
        pane = FXMLLoader.load(Main.class.getResource("/FXML/Signup.fxml"));
        this.stage = stage;
        Background background = Signup.BackgroundMaker(new Image(Main.class.getResource("/Images/MainBackground1.jpg").toExternalForm()));
        pane.setBackground(background);
        timer();
        VBox Buttons = new VBox(); Buttons.setLayoutX(700); Buttons.setLayoutY(120); Buttons.setSpacing(15);
        Button profileMenu = new Button("profile menu"); profileMenu.setBackground(Signup.gray); profileMenu.setPrefWidth(300);
        Button scoreboard = new Button("score board"); scoreboard.setBackground(Signup.gray); scoreboard.setPrefWidth(300);
        profileMenu.setFont(Font.font(20));
        scoreboard.setFont(Font.font(20));
        noBalls.setLayoutX(240); noBalls.setLayoutY(90); noBalls.setVisible(false);
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getScreenX() >= 280 && mouseEvent.getSceneX() <= 380 &&
                        mouseEvent.getScreenY() >= 740 && mouseEvent.getScreenY() <= 820){
                    long now= System.currentTimeMillis();
                    noBalls.setVisible(true);
                    lastTrueTime=now;
                }
            }
        });
        scoreboard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Scoreboard scoreboard1 = new Scoreboard(currentUser);
                try {
                    scoreboard1.start(Main.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        profileMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Profile profile = new Profile(currentUser);
                try {
                    profile.start(Main.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Buttons.getChildren().addAll(profileMenu , scoreboard);
        pane.getChildren().addAll(Buttons  , noBalls);
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String name = keyEvent.getCode().getName();
                if (name.equals("Esc")){
                    Login login = new Login();
                    try {
                        login.start(Main.stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    public void timer(){
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long now= System.currentTimeMillis();
                if (lastTrueTime+3000<now) {
                    noBalls.setVisible(false);
                }
            }
        }, 000 , 100);
    }

}
