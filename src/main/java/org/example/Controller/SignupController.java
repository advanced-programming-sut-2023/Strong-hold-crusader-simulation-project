package org.example.Controller;

import javafx.scene.control.*;
import org.example.Model.User;
import org.example.View.Responds.SignupMenuResponds;
import org.example.View.Signup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

public class SignupController {
    public static String userMaker(TextField username , PasswordField password , PasswordField confirm , TextField email ,
                                   TextField slogan , TextField nickname, TextField captcha , ChoiceBox choiceBox , TextField recoveryA) {
        if (password.getText().length() == 0 || username.getText().length() == 0 || email.getText().length() == 0){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("empty Fields");
            error.setContentText(SignupMenuResponds.emptyField.getResponse());
            error.show();
            return "fail";
        }
        if (User.doesUsernameExist(username.getText())){
            Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
            String newUsername = User.randomUsername(username.getText());
            errorAlert.setHeaderText("old username already exist , your new one is : " + newUsername);
            errorAlert.setContentText("if you agree enter click ok to continue with this username else click cancel.");
            Optional<ButtonType> result = errorAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                username.setText(newUsername);
            }
            else {
                return "fail";
            }
        }
        if (password.getText().equals("random")){
            Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
            String newPassword = generatePass();
            errorAlert.setHeaderText("your random password is : " + newPassword);
            errorAlert.setContentText("if you agree enter click ok to continue with this password else click cancel.");
            Optional<ButtonType> result = errorAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                password.setText(newPassword);
            }
            else {
                return "fail";
            }
        }
        if (User.isEmailDuplicate(email.getText())){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("invalid email");
            error.setContentText(SignupMenuResponds.emailAlreadyExist.getResponse());
            error.show();
            return "fail";
        }
        if (slogan.getText().equals("random")){
            String randomSlogan = randomSlogan();
            Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
            errorAlert.setHeaderText("your random slogan is : " + randomSlogan);
            errorAlert.setContentText("if you agree enter click ok to continue with this password else click cancel.");
            Optional<ButtonType> result = errorAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                slogan.setText(randomSlogan);
            }
            else {
                return "fail";
            }
        }
        if (User.usernameCheck(username).equals("fail")){
            Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
            errorAlert.setHeaderText("invalid username");
            errorAlert.setContentText(SignupMenuResponds.invalidUsername.getResponse());
            errorAlert.showAndWait();
            return "fail";
        }
        else if (User.checkPassword(password , confirm).equals("fail")){
            return "fail";
        }
        else if (User.checkEmail(email.getText()) != null){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("invalid email");
            error.setContentText(User.checkEmail(email.getText()));
            error.show();
            return "fail";
        }
        if (choiceBox.getValue() == null){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("empty field");
            error.setContentText("you should pick a recovery question.");
            error.show();
            return "fail";
        }
        else {
            if (recoveryA.getText().length() == 0){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("invalid answer");
                error.setContentText("you should enter an answer to recovery question.");
                error.show();
                return "fail";
            }
        }
        if (Signup.isValidCapthca(captcha.getText())) {
            return "success";
        }
        else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("invalid captcha");
            error.setContentText("please reEnter the captcha correctly.");
            error.show();
            return "fail";
        }

    }
    public static String generatePass() {
        Random random = new Random();
        String ss = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String ls = "qwertyuioplkjhgfdsazxcvbnm";
        String sp = "!@#$%^&*";
        String nm = "1234567890";
        String fn = ss + ls + sp + nm;
        ArrayList<Character> source = new ArrayList<>();
        source.add(ss.charAt(Math.abs(random.nextInt()) % 26));
        source.add(ls.charAt(Math.abs(random.nextInt()) % 26));
        source.add(sp.charAt(Math.abs(random.nextInt()) % 7));
        source.add(nm.charAt(Math.abs(random.nextInt()) % 10));
        source.add(fn.charAt(Math.abs(random.nextInt()) % 69));
        source.add(fn.charAt(Math.abs(random.nextInt()) / 2 % 69));
        Collections.shuffle(source, random);
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += source.get(i);
        }
        return result;
    }
    public static String randomSlogan() {
        Random random = new Random();
        String[] randomSlogans = {
                "I am not in danger skylar, I AM THE DANGER",
                "I shall have my revenge, in this life or the next",
                "May the force be with me",
                "My name is Inigo Montoya. You killed my father. Prepare to die",
                "They may take our lives, but they'll never take our freedom",
                "Ezekiel 25:17",
                "I will look for you, I will find you, and I will kill you",
                "Winter Is Coming",
                "King Kong ain't got sh*t on me",
                "today is a gift that's why it is called \"the present\"",
                "keep your friends close, but your enemies closer",
                "I am the warrior that built this town",
                "are you ready, kids?"
        };
        return randomSlogans[((random.nextInt()%100)+100)%randomSlogans.length];
    }
}
