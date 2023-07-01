package org.example.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.controller.SignupController;
import org.example.model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Profile extends Application {
    private static User currentUser;
    public static Stage stage;
    public static Pane pane;
    private StackPane contentPane;
    private Circle avatar;
    private static Background yellow = new Background(new BackgroundFill(Color.YELLOW , null , null));
    public Profile(User currentUser){
        this.currentUser = currentUser;
    }
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        pane = FXMLLoader.load(Profile.class.getResource("/FXML/Login.fxml"));
        Background background = Signup.BackgroundMaker(new Image(Main.class.getResource("/Images/ProfileBackground1.jpg").toExternalForm()));
        pane.setBackground(background);
        Circle avatar = new Circle(120); avatar.setFill(new ImagePattern(new Image(currentUser.getProfilePicture().toExternalForm())));
        Effect effect = new DropShadow(+40d, 0d, +7d, Color.BLACK);
        avatar.setEffect(effect);
        avatar.setLayoutY(200); avatar.setLayoutX(720); this.avatar = avatar;
        TextField Username = new TextField("username : "); Username.setEditable(false); Username.setBackground(yellow); Username.setFont(Font.font(20));
        TextField Email = new TextField("email :"); Email.setEditable(false); Email.setBackground(yellow); Email.setFont(Font.font(20));
        TextField Nickname = new TextField("nickname :"); Nickname.setEditable(false); Nickname.setBackground(yellow); Nickname.setFont(Font.font(20));
        TextField Slogan = new TextField("slogan :"); Slogan.setEditable(false); Slogan.setBackground(yellow); Slogan.setFont(Font.font(20));
        Username.setFocusTraversable(false); Email.setFocusTraversable(false); Nickname.setFocusTraversable(false); Slogan.setFocusTraversable(false);
        Username.setMaxWidth(130); Email.setMaxWidth(130); Nickname.setMaxWidth(130); Slogan.setMaxWidth(130);
        VBox labels = new VBox(); labels.getChildren().addAll(Username ,Email , Nickname , Slogan);
        VBox data = new VBox();
        TextField username = new TextField(currentUser.getUsername()); username.setBackground(Signup.gray); username.setFont(Font.font(20));
        username.setAlignment(Pos.CENTER); username.setEffect(effect); username.setEditable(false);
        TextField email = new TextField(currentUser.getEmail()); email.setBackground(Signup.gray); email.setFont(Font.font(20));
        email.setAlignment(Pos.CENTER); email.setEffect(effect); email.setEditable(false);
        TextField slogan = new TextField("you don't have slogan"); slogan.setBackground(Signup.gray); slogan.setFont(Font.font(20));
        TextField nickname = new TextField("you don't have nickname"); nickname.setBackground(Signup.gray); nickname.setFont(Font.font(20));
        nickname.setAlignment(Pos.CENTER); nickname.setEffect(effect); nickname.setEditable(false);
        HBox setSlogan = new HBox(); Button setSloganButton = new Button("set new slogan"); setSloganButton.setBackground(Signup.gray); setSlogan.setSpacing(5);
        setSloganButton.setFont(Font.font(20)); slogan.setEffect(effect); setSloganButton.setEffect(effect);
        Username.setEffect(effect); Email.setEffect(effect); Nickname.setEffect(effect); Slogan.setEffect(effect);
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(mouseEvent.getScreenX());

                return ;
            }
        });
        setSloganButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                sloganHandel(slogan);
                return ;
            }
        });
        setSlogan.getChildren().addAll(slogan , setSloganButton);
        Button droper = new Button("drop a profile"); droper.setMaxWidth(500); droper.setBackground(Signup.gray); droper.setAlignment(Pos.CENTER);
        droper.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                contentPane = new StackPane();
                Scene secondScene = new Scene(contentPane, 200, 200);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("drop your files here...");
                newWindow.setScene(secondScene);


                // Set position of second window, related to primary window.
                newWindow.setX(stage.getX() + 200);
                newWindow.setY(stage.getY() + 100);

                newWindow.show();
                contentPane.setOnDragOver(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(final DragEvent event) {
                        mouseDragOver(event);
                        return ;
                    }
                });
                contentPane.setOnDragDropped(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(final DragEvent event) {
                        mouseDragDropped(event);
                        return ;
                    }
                });

                contentPane.setOnDragExited(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(final DragEvent event) {
                        contentPane.setStyle("-fx-border-color: #C6C6C6;");
                        return ;
                    }
                });
                return ;
            }
        });
        username.setFocusTraversable(false); email.setFocusTraversable(false); nickname.setFocusTraversable(false); slogan.setFocusTraversable(false);
        Button changePassword = new Button("change password"); changePassword.setBackground(Signup.gray);
        changePassword.setFont(Font.font(20)); changePassword.setEffect(effect); changePassword.setMaxWidth(500);
        changePassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PasswordField newPassword = new PasswordField();
                newPassword.setMaxWidth(250);
                HBox passf = new HBox(); passf.getChildren().addAll(Signup.LabelMaker("new password :        " , 20 , Color.BLACK) , newPassword);
                newPassword.setBackground(Signup.gray);
                PasswordField newPasswordConf = new PasswordField();
                HBox passc = new HBox(); passc.getChildren().addAll(Signup.LabelMaker("password confirm : " , 20 , Color.BLACK) , newPasswordConf);
                newPasswordConf.setMaxWidth(250);
                newPasswordConf.setBackground(Signup.gray);
                Button submit = new Button("submit"); submit.setMaxWidth(350); submit.setBackground(Signup.gray);
                Pane pane1 = new Pane();
                VBox vBox = new VBox();
                vBox.getChildren().addAll(passf , passc , submit); vBox.setSpacing(15);
                vBox.setLayoutX(30); vBox.setLayoutY(40);
                pane1.getChildren().add(vBox);
                Scene secondScene = new Scene(pane1, 400, 200);
                pane1.setBackground(new Background(new BackgroundFill(Color.DARKGRAY , null , null)));
                submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        passwordChanger(newPassword , newPasswordConf);
                        return ;
                    }
                });
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("change Password");
                newWindow.setScene(secondScene);


                // Set position of second window, related to primary window.
                newWindow.setX(stage.getX() + 200);
                newWindow.setY(stage.getY() + 100);

                newWindow.show();
                return ;
            }
        });
        if (currentUser.getSlogan() != null){
            slogan.setText(currentUser.getSlogan());
        }
        if (currentUser.getNickname() != null){
            nickname.setText(currentUser.getNickname());
        }
        data.getChildren().addAll(username , email , nickname , setSlogan , droper , changePassword);
        labels.setSpacing(18); data.setSpacing(18);
        HBox hBox = new HBox(); hBox.getChildren().addAll(labels , data);
        hBox.setLayoutY(380); hBox.setLayoutX(425); hBox.setSpacing(15);
        pane.getChildren().addAll(avatar , hBox);
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
                return ;
            }
        });
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void passwordChanger(PasswordField newPassword, PasswordField newPasswordConf) {
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
            else {
                return;
            }
        }
        else if (User.checkPassword(newPassword , newPasswordConf).equals("fail")){
            return;
        }
        else {
            currentUser.setPassword(newPassword.getText());
            newPassword.setText(""); newPasswordConf.setText("");
        }
    }

    private void sloganHandel(TextField slogan) {
        if (slogan.getText().equals("random")){
            String temp = SignupController.randomSlogan();
            Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
            errorAlert.setHeaderText("your random slogan is : " + temp);
            errorAlert.setContentText("if you agree enter click ok to continue with this password else click cancel.");
            Optional<ButtonType> result = errorAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                slogan.setText(temp);
                currentUser.setSlogan(temp);
            }
            else {
                return ;
            }
        }
        else {
            currentUser.setSlogan(slogan.getText());
        }
    }

    void addImage(Image i, StackPane pane){
        ImagePattern imagePattern = new ImagePattern(i);
        avatar.setFill(imagePattern);
    }

    private void mouseDragDropped(final DragEvent e) {
        final Dragboard db = e.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            // Only get the first file from the list
            final File file = db.getFiles().get(0);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println(file.getAbsolutePath());
                    try {
                        if(!contentPane.getChildren().isEmpty()){
                            contentPane.getChildren().remove(0);
                        }
                        Image img = new Image(new FileInputStream(file.getAbsolutePath()));
                        URL url = file.toURL();
                        currentUser.setProfilePicture(url);
                        addImage(img, contentPane);
                        pane.setFocusTraversable(true);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MalformedURLException ex) {
                        System.out.println("mal mal");
                    }
                }
            });
        }
        e.setDropCompleted(success);
        e.consume();
    }

    private  void mouseDragOver(final DragEvent e) {
        final Dragboard db = e.getDragboard();

        final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg");

        if (db.hasFiles()) {
            if (isAccepted) {
                contentPane.setStyle("-fx-border-color: red;"
                        + "-fx-border-width: 5;"
                        + "-fx-background-color: #C6C6C6;"
                        + "-fx-border-style: solid;");
                e.acceptTransferModes(TransferMode.COPY);
            }
        } else {
            e.consume();
        }
    }
}
