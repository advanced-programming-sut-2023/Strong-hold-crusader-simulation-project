module phase2 {
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.controls;
    requires java.logging;

    exports org.example.View;
    opens org.example.View to javafx.controls , javafx.media , javafx.fxml;
    exports org.example.Controller;
    opens org.example.Controller to javafx.controls , javafx.media , javafx.fxml;
}