module phase2 {
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.controls;
    requires java.logging;

    exports org.example.view;
    opens org.example.view to javafx.controls , javafx.media , javafx.fxml;
    exports org.example.controller;
    opens org.example.controller to javafx.controls , javafx.media , javafx.fxml;
}