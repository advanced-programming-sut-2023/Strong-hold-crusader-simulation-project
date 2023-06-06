package org.example.View;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.Controller.SignupController;
import org.example.Model.User;
import org.example.View.Responds.SignupMenuCommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Signup extends Application {
    public static Stage stage;
    public static Pane pane;
    private static ArrayList<Background> backgrounds = new ArrayList<>();
    public static HBox capcha;
    private static String CaptchaNumber;
    private TextField username;
    private PasswordField password , confirm;

    public static boolean isValidCapthca(String text) {
        if (text.equals(CaptchaNumber)){
            return true;
        }
        return false;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Pane pane = FXMLLoader.load(Signup.class.getResource("/FXML/Signup.fxml")); this.pane = pane;
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false , false, true, false);
        Image image = new Image(Signup.class.getResource("/Images/SignupBackground2.jpg").toExternalForm());
        Background background = new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize));
        Image image1 = new Image(Signup.class.getResource("/Images/SignupBackground.jpg").toExternalForm());
        Background background2 =  new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize));
        backgrounds.add(background); backgrounds.add(background2);
        pane.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN , null , null)));
        pane.setBackground(background);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        TextField username = new TextField(); username.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
        username.setFont(Font.font(14)); Label Username = new Label("username : "); Username.setTextFill(Color.BEIGE); Username.setFont(Font.font(20));
        PasswordField password = new PasswordField(); password.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
        password.setFont(Font.font(14)); Label Password = new Label("password : "); Password.setTextFill(Color.BEIGE); Password.setFont(Font.font(20));
        PasswordField confirm = new PasswordField(); confirm.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
        confirm.setFont(Font.font(14)); Label Confirm = new Label("confirm password : "); Confirm.setTextFill(Color.BEIGE); Confirm.setFont(Font.font(20));
        TextField email = new TextField(); email.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
        email.setFont(Font.font(14)); Label Email = new Label("email : "); Email.setTextFill(Color.BEIGE); Email.setFont(Font.font(20));
        TextField slogan = new TextField(); slogan.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
        slogan.setFont(Font.font(14)); Label Slogan = new Label("Slogan : "); Slogan.setTextFill(Color.BEIGE); Slogan.setFont(Font.font(20));
        TextField nickname = new TextField(); nickname.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
        nickname.setFont(Font.font(14)); Label Nickname = new Label("nickname : "); Nickname.setTextFill(Color.BEIGE); Nickname.setFont(Font.font(20));
        Label captcher = new Label("captcha : "); captcher.setTextFill(Color.BEIGE); captcher.setFont(Font.font(20));
        HBox hBox0 = new HBox(); capcha = hBox0; capchaMake();
        TextField Captcha = new TextField(); Captcha.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(User.securityQuestions)); choiceBox.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null))); choiceBox.setMaxWidth(500);
        Label recoveryQ = new Label("recovery question : "); recoveryQ.setFont(Font.font(20)); recoveryQ.setTextFill(Color.BEIGE);
        Label recoveryA = new Label("recovery answer : "); recoveryA.setFont(Font.font(20)); recoveryA.setTextFill(Color.BEIGE);
        TextField RecoveryAnswer = new TextField(); RecoveryAnswer.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
        VBox vBox = new VBox(); vBox.getChildren().addAll(username , password , confirm , email , slogan , nickname ,choiceBox, RecoveryAnswer , hBox0 , Captcha);
        VBox vBox1 = new VBox(); vBox1.getChildren().addAll(Username , Password , Confirm , Email , Slogan , Nickname , recoveryQ , recoveryA , captcher); vBox.setSpacing(18); vBox1.setSpacing(18);
        Label userError = new Label(""); userError.setFont(Font.font(20)); Label passError = new Label(""); passError.setFont(Font.font(20));
        this.username = username; this.password = password; this.confirm = confirm;
        HBox hBox = new HBox( ); hBox.getChildren().addAll(vBox1 , vBox ); hBox.setSpacing(7); hBox.setLayoutX(70); hBox.setLayoutY(90);
        textHandle();
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String name = event.getCode().getName();
                if (name.equals("Enter")){
                    String res = SignupController.userMaker(username , password , confirm , email , slogan , nickname , Captcha , choiceBox , RecoveryAnswer);
                    if (res.equals("success")){
                        User user = new User(username.getText() , password.getText() , email.getText()  , nickname.getText(), slogan.getText());
                        user.setSecurityQuestion(User.getQuestionNumber((String)choiceBox.getValue()) , RecoveryAnswer.getText());
                        username.clear(); password.clear(); confirm.clear(); email.clear(); slogan.clear(); nickname.clear(); Captcha.clear(); choiceBox.getSelectionModel().clearSelection(); RecoveryAnswer.clear();
                        System.out.println(user);
                        capchaMake();
                    }
                }
            }
        });
        pane.getChildren().add(hBox);
        stage.setMaximized(true);
        stage.show();
    }

    private void textHandle() {
        AtomicBoolean userValid = new AtomicBoolean(false);
        AtomicBoolean passValid = new AtomicBoolean(false);
        AtomicBoolean confirmValid = new AtomicBoolean(false);
        username.textProperty().addListener((observable, oldText, newText)->{
            if (User.doesUsernameExist(newText)){
                userValid.set(false);
                username.setBackground(new Background(new BackgroundFill(Color.RED , null , null)));
            }
            else {
                userValid.set(true);
                username.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
            }
            if (User.usernameCheck(new TextField(newText)).equals("fail")){
                userValid.set(false);
                username.setBackground(new Background(new BackgroundFill(Color.RED , null , null)));
            }
            else {
                userValid.set(true);
                username.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
            }
            if (username.getText().length() == 0){
                userValid.set(true);
                username.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
            }
        });
        password.textProperty().addListener((observable, oldText, newText)->{
            password.setText(newText);
            if (!isStrong(password)){
                passValid.set(false);
                password.setBackground(new Background(new BackgroundFill(Color.RED , null , null)));
            }
            else {
                passValid.set(true);
                password.setBackground(new Background(new BackgroundFill(Color.GREEN , null , null)));
            }
            if (password.getText().length() == 0){
                passValid.set(true);
                password.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
            }
            if (password.getText().length() == 0 || (confirmValid.get() && passValid.get())){
                confirm.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
                password.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
            }
        });
        confirm.textProperty().addListener((observable, oldText, newText)->{
            if (!password.getText().equals(confirm.getText())){
                confirmValid.set(false);
                confirm.setBackground(new Background(new BackgroundFill(Color.RED , null , null)));
            }
            else {
                confirmValid.set(true);
                confirm.setBackground(new Background(new BackgroundFill(Color.GREEN , null , null)));
            }
            if (confirm.getText().length() == 0 || (confirmValid.get() && passValid.get())){
                confirm.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
                password.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD , null , null)));
            }

        });
    }

    public void capchaMake() {
        String number = "" ;
        Random random = new Random();
        String address = Signup.class.getResource("/Images/Captcha/").toExternalForm();
        ArrayList<Image> images = new ArrayList<>();
        Circle cir0 = new Circle(30);
        Circle cir1 = new Circle(30);
        Circle cir2 = new Circle(30);
        Circle cir3 = new Circle(30);
        for (int i = 0 ; i < 4 ; i++ ){
            int x = Math.abs(random.nextInt() * random.nextInt())%10;
            int y = Math.abs(random.nextInt() * random.nextInt())%3;
            Image temp = new Image(address+x+y+".jpeg");
            images.add(temp);
            number += (x+"");
        }
        CaptchaNumber = number;
        cir0.setFill(new ImagePattern(images.get(0))); cir1.setFill(new ImagePattern(images.get(1)));
        cir2.setFill(new ImagePattern(images.get(2))); cir3.setFill(new ImagePattern(images.get(3)));
        if (capcha.getChildren().size() > 0) {
            capcha.getChildren().remove(0);
            capcha.getChildren().add(0, cir0);
            capcha.getChildren().remove(1);
            capcha.getChildren().add(1, cir1);
            capcha.getChildren().remove(2);
            capcha.getChildren().add(2, cir2);
            capcha.getChildren().remove(3);
            capcha.getChildren().add(3, cir3);
        }
        else {
            capcha.getChildren().addAll(cir0 , cir1 , cir2 , cir3);
        }
        capcha.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                capchaMake();
            }
        });
    }
    @FXML
    public void initialize(){

    }
    private boolean isStrong(PasswordField password) {
        if (SignupMenuCommands.password.getMatcher(password.getText()) == null){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
