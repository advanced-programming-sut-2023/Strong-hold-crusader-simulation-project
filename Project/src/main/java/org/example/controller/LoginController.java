package org.example.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.model.User;
import org.example.view.Login;
import org.example.view.Main;

public class LoginController {
    public static void login(TextField username, PasswordField password) throws Exception {
        if (!User.doesUsernameExist(username.getText())){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("invalid username.");
            error.setContentText("username you entered doesn't exist.");
            error.show();
        }
        else if (!User.getUserByUsername(username.getText()).isPasswordCorrect(password.getText())){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("incorrect password.");
            error.setContentText("password you entered doesn't match with the original.");
            error.show();
        }
        else {
            Main main = new Main(User.getUserByUsername(username.getText()));
            main.start(Login.stage);
        }

    }
}
