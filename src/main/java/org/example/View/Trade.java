package org.example.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Trade extends Application {
    private static Pane pane;
    private static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        pane = FXMLLoader.load(Trade.class.getResource("/FXML/Trade.fxml"));
        this.stage = stage;

    }

    public static void main(String[] args) {
        launch(args);
    }
}

