package org.example.view;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.controller.SignupController;
import org.example.model.User;
import org.example.view.Responds.SignupMenuCommands;
import org.example.view.Responds.SignupMenuResponds;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Signup extends Application {
    public static Stage stage;
    public static Pane pane;
    public static HBox capcha;
    private static String CaptchaNumber;
    private TextField username;
    private PasswordField password , confirm;
    public static Background gray = new Background(new BackgroundFill(Color.GRAY , null , null));
    public static Background red = new Background(new BackgroundFill(Color.DARKRED , null , null));
    public static Background green = new Background(new BackgroundFill(Color.GREEN , null , null));
    private static VBox errors ;
    private ArrayList<FadeTransition> fadeTransitions = new ArrayList<>();



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
        Image image = new Image(Login.class.getResource("/Images/SignupBackground2.jpg").toExternalForm());
        Background background = BackgroundMaker(image);
        pane.setBackground(background);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        TextField username = new TextField(); username.setBackground(gray);
        PasswordField password = new PasswordField(); password.setBackground(gray);
        PasswordField confirm = new PasswordField(); confirm.setBackground(gray);
        TextField email = new TextField(); email.setBackground(gray);
        TextField slogan = new TextField(); slogan.setBackground(gray);
        TextField nickname = new TextField(); nickname.setBackground(gray);
        TextField Captcha = new TextField(); Captcha.setBackground(gray);
        TextField RecoveryAnswer = new TextField(); RecoveryAnswer.setBackground(gray);
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(User.securityQuestions));
        choiceBox.setBackground(gray); choiceBox.setMaxWidth(500);
        Label captcher = LabelMaker("captcha : " , 20 , Color.BEIGE);
        username.setFont(Font.font(14)); Label Username = LabelMaker("username : " , 20 , Color.BEIGE);
        password.setFont(Font.font(14)); Label Password = LabelMaker("password : " , 20 , Color.BEIGE);
        confirm.setFont(Font.font(14)); Label Confirm = LabelMaker("confirm password : " , 20 , Color.BEIGE);
        email.setFont(Font.font(14)); Label Email = LabelMaker("email : " , 20 , Color.BEIGE);
        slogan.setFont(Font.font(14)); Label Slogan = LabelMaker("slogan : " , 20 , Color.BEIGE);
        nickname.setFont(Font.font(14)); Label Nickname = LabelMaker("nickname : " , 20 , Color.BEIGE);
        Label recoveryQ = LabelMaker("recovery question : " , 20 , Color.BEIGE);
        Label recoveryA = LabelMaker("recovery answer : " , 20 , Color.BEIGE);
        this.username = username; this.password = password; this.confirm = confirm;
        HBox hBox0 = new HBox(); capcha = hBox0;
        capchaMake();
        VBox vBox = new VBox(); vBox.getChildren().addAll(username , password , confirm , email , slogan , nickname ,choiceBox, RecoveryAnswer , hBox0 , Captcha);
        VBox vBox1 = new VBox(); vBox1.getChildren().addAll(Username , Password , Confirm , Email , Slogan , Nickname , recoveryQ , recoveryA , captcher); vBox.setSpacing(18); vBox1.setSpacing(18);
        VBox vBox2 = new VBox(); vBox2.getChildren().addAll(LabelMaker("" , 14 , Color.RED) , LabelMaker("" , 14 , Color.RED) , LabelMaker("" , 14 , Color.RED)); vBox2.setLayoutX(240); vBox2.setLayoutY(72); vBox2.setSpacing(25);
        HBox hBox = new HBox( ); hBox.getChildren().addAll(vBox1 , vBox  ); hBox.setSpacing(7); hBox.setLayoutX(70); hBox.setLayoutY(90); errors = vBox2; pane.getChildren().add(vBox2);
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
                        Label label1 = (Label) errors.getChildren().get(0); label1.setText("");
                        Label label2 = (Label) errors.getChildren().get(1); label2.setText("");
                        Label label3 = (Label) errors.getChildren().get(2); label3.setText("");
                        User.saveFile();
                        capchaMake();
                    }
                }
                else if (name.equals("Esc")){
                    Login login = new Login();
                    try {
                        login.start(Signup.stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        pane.getChildren().addAll(hBox);
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
                username.setBackground(red);
                Label label = (Label) errors.getChildren().get(0);
                label.setText(SignupMenuResponds.usernameAlreadyExist.getResponse());
                label.setTextFill(Color.RED);
                label.setOpacity(100);
            }
            else {
                userValid.set(true);
                username.setBackground(gray);
            }
            if (User.usernameCheck(new TextField(newText)).equals("fail")) {
                userValid.set(false);
                username.setBackground(red);
                Label label = (Label) errors.getChildren().get(0);
                label.setText(SignupMenuResponds.invalidUsername.getResponse());
                label.setTextFill(Color.RED);
                label.setOpacity(100);
            }
            else {
                userValid.set(true);
                username.setBackground(gray);
                Label label = (Label) errors.getChildren().get(0);
                label.setText("username is ok now.");
                label.setTextFill(Color.GREEN);
                label.setOpacity(100);
            }
            if (username.getText().length() == 0){
                userValid.set(true);
                username.setBackground(gray);
            }
        });
        password.textProperty().addListener((observable, oldText, newText)->{
            password.setText(newText);
            if (!isStrong(password)){
                passValid.set(false);
                password.setBackground(red);
                Label label = (Label) errors.getChildren().get(1);
                label.setText("password is weak");
                label.setTextFill(Color.RED);
                label.setOpacity(100);
            }
            else {
                passValid.set(true);
                password.setBackground(green);
                Label label = (Label) errors.getChildren().get(1);
                label.setText("password is ok now");
                label.setTextFill(Color.GREEN);
                label.setOpacity(100);
            }
            if (password.getText().length() == 0){
                passValid.set(true);
                password.setBackground(gray);
            }
            if (password.getText().length() == 0 || (confirmValid.get() && passValid.get())){
                confirm.setBackground(gray);
                password.setBackground(gray);
            }
        });
        confirm.textProperty().addListener((observable, oldText, newText)->{
            if (!password.getText().equals(confirm.getText())){
                confirmValid.set(false);
                confirm.setBackground(red);
                Label label = (Label) errors.getChildren().get(2);
                label.setText("confirm must be equal to password.");
                label.setTextFill(Color.RED);
                label.setOpacity(100);
            }
            else {
                confirmValid.set(true);
                confirm.setBackground(green);
                Label label = (Label) errors.getChildren().get(2);
                label.setText("confirm is ok now.");
                label.setTextFill(Color.GREEN);
                label.setOpacity(100);
            }
            if (confirm.getText().length() == 0 || (confirmValid.get() && passValid.get())){
                confirm.setBackground(gray);
                password.setBackground(gray);
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
    public static Label LabelMaker(String header , int font , Color color){
        Label label = new Label(header);
        label.setFont(Font.font(font));
        label.setTextFill(color);
        return label;
    }
    public static Background BackgroundMaker(Image image){
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false , false, true, false);
        Background background = new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize));
        return background;
    }
}
