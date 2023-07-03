package org.example.view;

import javafx.application.Application;
import javafx.event.EventHandler;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.LoginController;
import org.example.controller.SignupController;
import org.example.model.User;
import org.example.view.Responds.SignupMenuResponds;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class Login extends Application {

    private int state = 0 ;
    public static Stage stage;
    public static Pane pane;
    public static User user;
    private static TextField username ;
    private static Text recoveryQ = null ;
    private static String captchasContent;
    private static TextField recoveryA = null , captchaField = new TextField();
    private static PasswordField password , newPassword = new PasswordField(), newPasswordConfirm = new PasswordField();
    private static Label RecoveryA , newPass = Signup.LabelMaker("new password : " , 20 , Color.BEIGE) , newPassCon = Signup.LabelMaker("new password confirm : " , 20 , Color.BEIGE)
            , forgotPassword , dontHaveAccount , Password , Username , captcher = Signup.LabelMaker("captcha" , 20 , Color.BEIGE);
    private static VBox errors , vBox , vBox1;
    private static HBox captcha = new HBox();
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Pane pane = FXMLLoader.load(Signup.class.getResource("/FXML/Signup.fxml")); this.pane = pane;
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false , false, true, false);
        Image image = new Image(Signup.class.getResource("/Images/LoginBackground2.jpg").toExternalForm());
        Background background = Signup.BackgroundMaker(image);
        pane.setBackground(background);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        TextField username = new TextField(); username.setBackground(Signup.gray);
        PasswordField password = new PasswordField(); password.setBackground(Signup.gray);
        TextField RecoveryAnswer = new TextField(); RecoveryAnswer.setBackground(Signup.gray); RecoveryAnswer.setVisible(false); this.recoveryA = RecoveryAnswer;
        username.setFont(Font.font(14)); Label Username = Signup.LabelMaker("username : " , 20 , Color.BEIGE);
        password.setFont(Font.font(14)); Label Password = Signup.LabelMaker("password : " , 20 , Color.BEIGE);
        Text recoveryQ = new Text(""); recoveryQ.setFont(Font.font(20)); recoveryQ.setFill(Color.BEIGE); recoveryQ.setVisible(false);
        this.recoveryQ = recoveryQ;
        Label RecoveryA = Signup.LabelMaker("recovery answer : " , 20 , Color.BEIGE); this.RecoveryA = RecoveryA; RecoveryA.setVisible(false);
        Label dontHaveAccount = Signup.LabelMaker("don't have an account ?? make one" , 14 , Color.BLACK);
        Label forgotPassword = Signup.LabelMaker("forgot your password ?? reset it" , 14 , Color.BLACK);
        this.username = username; this.password = password; this.forgotPassword = forgotPassword; this.dontHaveAccount = dontHaveAccount; this.Password = Password; this.Username = Username;
        capchaMake(); captchaField.setBackground(Signup.gray);
        VBox vBox = new VBox(); vBox.getChildren().addAll(username , password , captcha , captchaField , forgotPassword , dontHaveAccount);
        VBox vBox1 = new VBox(); vBox1.getChildren().addAll(Username , Password , captcher); vBox.setSpacing(18); vBox1.setSpacing(18);
        VBox vBox2 = new VBox(); vBox2.getChildren().addAll(Signup.LabelMaker("" , 14 , Color.RED) , Signup.LabelMaker("" , 14 , Color.RED) , Signup.LabelMaker("" , 14 , Color.RED)); vBox2.setLayoutX(240); vBox2.setLayoutY(72); vBox2.setSpacing(25);
        HBox hBox = new HBox( ); hBox.getChildren().addAll(vBox1 , vBox ); hBox.setSpacing(7); hBox.setLayoutX(70); hBox.setLayoutY(90); errors = vBox2; pane.getChildren().add(vBox2);
        this.vBox = vBox ; this.vBox1 = vBox1;
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String name = event.getCode().getName();
                if (name.equals("Enter") && state == 0){
                    if (isValidCapthca(captchaField.getText())){
                        try {
                            LoginController.login(username , password);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                else if (name.equals("Esc") && state == 1){
                    captchaField.setText("");
                    Login login = new Login();
                    state = 0;
                    try {
                        login.start(Login.stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else if (name.equals("Esc")){
                    System.exit(33);
                }
            }
        });
        forgotPassword.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                recover();
            }
        });
        dontHaveAccount.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Signup signup = new Signup();
                try {
                    signup.start(Login.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pane.getChildren().add(hBox);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
    }

    private void recover() {
        state = 1;
        vBox.getChildren().remove(1); vBox.getChildren().remove(forgotPassword);
        vBox.getChildren().remove(captcha); vBox.getChildren().remove(captchaField);
        vBox1.getChildren().remove(password); vBox1.getChildren().remove(captcher);
        Button button = new Button("get recovery question");
        button.setMaxWidth(500); button.setBackground(Signup.gray);
        vBox.getChildren().add(1 ,button);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (username.getText().length() == 0){
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setHeaderText("empty Fields");
                    error.setContentText(SignupMenuResponds.emptyField.getResponse());
                    error.show();
                }
                else if (!User.doesUsernameExist(username.getText())){
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setHeaderText("username doesn't exist.");
                    error.setContentText("username you entered doesn't exist.");
                    error.show();
                }
                else {
                    user = User.getUserByUsername(username.getText());
                    recoveryQ.setText(User.getQuestion(username.getText()));
                    vBox.getChildren().remove(button);
                    vBox.getChildren().remove(dontHaveAccount);
                    vBox1.getChildren().remove(Username);
                    vBox.getChildren().remove(username);
                    vBox1.getChildren().remove(Password);
                    Label recoverQ = Signup.LabelMaker("recovery question : " , 20 , Color.BEIGE);
                    Label recoverA = Signup.LabelMaker("recovery answer : " , 20 , Color.BEIGE);
                    newPassword.setBackground(Signup.gray); newPasswordConfirm.setBackground(Signup.gray);
                    recoveryA.setVisible(true); recoveryQ.setVisible(true);
                    Button submit = new Button("submit");
                    vBox1.getChildren().addAll(recoverQ , recoverA , newPass , newPassCon);
                    vBox.getChildren().addAll(recoveryQ ,recoveryA , newPassword , newPasswordConfirm , submit);
                    submit.setBackground(Signup.gray); submit.setMaxWidth(500);
                    submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            recoverDoer();
                        }
                    });
                }
            }
        });
    }

    private void recoverDoer() {
        if (user.isSecurityAnswerCorrect(recoveryA.getText())){
            if (newPassword.getText().equals("random")){
                Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
                String generatePass = SignupController.generatePass();
                errorAlert.setHeaderText("your random password is : " + generatePass);
                errorAlert.setContentText("if you agree enter click ok to continue with this password else click cancel.");
                Optional<ButtonType> result = errorAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK){
                    newPassword.setText(generatePass);
                    return;
                }
            }
            if (!User.checkPassword(newPassword , newPasswordConfirm).equals("fail")){
                user.setPassword(newPassword.getText());
                newPassword.setText(""); newPasswordConfirm.setText("");
                Login login = new Login();
                try {
                    login.start(Login.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                return;
            }
        }
        else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("invalid answer.");
            error.setContentText("the entered answer doesn't match with your original answer.");
            error.show();
            return;
        }
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
        captchasContent = number;
        cir0.setFill(new ImagePattern(images.get(0))); cir1.setFill(new ImagePattern(images.get(1)));
        cir2.setFill(new ImagePattern(images.get(2))); cir3.setFill(new ImagePattern(images.get(3)));
        if (captcha.getChildren().size() > 0) {
            captcha.getChildren().remove(0);
            captcha.getChildren().add(0, cir0);
            captcha.getChildren().remove(1);
            captcha.getChildren().add(1, cir1);
            captcha.getChildren().remove(2);
            captcha.getChildren().add(2, cir2);
            captcha.getChildren().remove(3);
            captcha.getChildren().add(3, cir3);
        }
        else {
            captcha.getChildren().addAll(cir0 , cir1 , cir2 , cir3);
        }
        captcha.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                capchaMake();
            }
        });
    }
    public static void main(String[] args) {
        User.readFile();
        launch(args);
        User.saveFile();
    }
    public static boolean isValidCapthca(String text) {
        if (text.equals(captchasContent)){
            return true;
        }
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("invalid captcha");
        error.setContentText("please reEnter the captcha correctly.");
        error.show();
        return false;
    }
}