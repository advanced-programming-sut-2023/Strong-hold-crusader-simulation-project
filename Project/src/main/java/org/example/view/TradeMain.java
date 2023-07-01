package org.example.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.Controller;
import org.example.model.Trade;
import org.example.model.*;

import java.util.HashMap;
import java.util.Optional;

public class TradeMain extends Application {
    private static User currentUser;
    private static Pane pane;
    private static Stage stage;
    private static ScrollPane scrollPane;
    private static Background brown = new Background(new BackgroundFill(Color.MISTYROSE , null , null));
    private static VBox mainVBox , whole;
    public TradeMain(User currentUser){
        this.currentUser = currentUser;
    }
    @Override
    public void start(Stage stage) throws Exception {
        pane = FXMLLoader.load(TradeMain.class.getResource("/FXML/Trade.fxml"));
        this.stage = stage;
        pane.setBackground(Signup.BackgroundMaker(new Image(TradeMain.class.getResource("/Images/TradeBackground.jpg").toExternalForm())));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        VBox vBox = new VBox();
        Button newOffer = new Button("create new trade offer"); newOffer.setBackground(brown); newOffer.setPrefWidth(300);
        Button previousTrades = new Button("previous trades"); previousTrades.setBackground(brown); previousTrades.setPrefWidth(300);
        newOffer.setFont(Font.font(20));previousTrades.setFont(Font.font(20));
        vBox.getChildren().addAll(newOffer , previousTrades); vBox.setSpacing(15); vBox.setLayoutX(550); vBox.setLayoutY(300);
        this.mainVBox = vBox;
        previousTrades.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                vBox.setVisible(false);
                makePreviousTrades();
            }
        });
        newOffer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                vBox.setVisible(false);
                makeNewOffer();
            }
        });
        pane.getChildren().addAll(vBox);
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String name = keyEvent.getCode().getName();
                switch (name){
                    case "Esc":{
                        if (vBox.isVisible()){
                        Main main = new Main(currentUser);
                        try {
                            main.start(stage);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }}
                    }
                }
            }
        });
        stage.setMaximized(true);
        stage.show();
    }

    private void makeNewOffer() {
        this.scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        scrollPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT , null , null)));
        VBox vBox = new VBox(); vBox.setSpacing(15);
        scrollPane.setPrefHeight(350); scrollPane.setPrefWidth(572);
        scrollPane.setLayoutX( 450); scrollPane.setLayoutY(40);
        for (int i = 0 ; i < User.getAllUsers().size() ; i++){
            HBox hBox = new HBox(); hBox.setSpacing(10);
            VBox profile = new VBox(); profile.setAlignment(Pos.CENTER);
            VBox username = new VBox(); username.setAlignment(Pos.CENTER);
            Circle circle = new Circle(30);
            circle.setFill(new ImagePattern(new Image(User.getAllUsers().get(i).getProfilePicture().toExternalForm())));
            int finalI = i;
            profile.getChildren().add(circle);
            StackPane stackPane = new StackPane();
            Text text = new Text("username : " + User.getAllUsers().get(i).getUsername() +
                    "   high score : " + User.getAllUsers().get(i).getHighScore() +  "   rank : " + Scoreboard.getRank(i) );            text.setFont(Font.font(20)); text.setFill(Color.BROWN);
            ImageView iv = new ImageView(Scoreboard.class.getResource("/Images/LabelBackground1.jpg").toExternalForm());
            iv.setFitHeight(30); iv.setFitWidth(500);
            stackPane.getChildren().add(iv); stackPane.getChildren().add(text);
            if (User.getAllUsers().get(i).getUsername().equals(currentUser.getUsername())){
                text.setFill(Color.GOLD);
            }
            hBox.getChildren().addAll(profile , stackPane);
            int finalI1 = i;
            hBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    errorAlert.setHeaderText("creating new trade.");
                    errorAlert.setContentText("are you sure want to make a trade with " + User.getAllUsers().get(finalI).getUsername());
                    Optional<ButtonType> result = errorAlert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK){
                        whole.setVisible(false);
                        openTrade(User.getAllUsers().get(finalI1));
                    }
                }
            });
            vBox.getChildren().add(hBox);
        }
        scrollPane.setContent(vBox);
        Button back = new Button("back"); back.setBackground(Signup.gray); back.setMaxWidth(700);
        TextField usersToTradeWith = new TextField("users to make a trade with"); usersToTradeWith.setFont(Font.font(25)); usersToTradeWith.setEditable(false);
        usersToTradeWith.setAlignment(Pos.CENTER); usersToTradeWith.setFocusTraversable(false); usersToTradeWith.setBackground(Signup.gray);
        VBox whole = new VBox(); whole.getChildren().addAll(usersToTradeWith , scrollPane , back); whole.setSpacing(5);
        whole.setLayoutX(460); whole.setLayoutY(230);
        whole.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String name = keyEvent.getCode().getName();
                if (name.equals("Esc")){
                    TradeMain tradeMain = new TradeMain(currentUser);
                    try {
                        tradeMain.start(Main.stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TradeMain tradeMain = new TradeMain(currentUser);
                try {
                    tradeMain.start(Main.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        this.whole = whole;
        pane.getChildren().add(whole);
    }

    private void openTrade(User user) {

        if (Government.getGovernmentByUser(user) == null) {
            errorMaker("user error", "You're not playing with this user right now!", false);
            whole.setVisible(true);
            return;
        }
        VBox Whole = new VBox(); Whole.setAlignment(Pos.CENTER); Whole.setSpacing(5);
        HashMap<String , TextField > getPrice = new HashMap<>();
        HashMap<String , TextField > getNumber = new HashMap<>();
        this.scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        scrollPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT , null , null)));
        scrollPane.setPrefHeight(350); scrollPane.setPrefWidth(572);
        scrollPane.setLayoutX( 450); scrollPane.setLayoutY(40);

        for (Resources resource : Resources.values()){
            Image image = new Image(Signup.class.getResource("/Images/resources/"+resource.getName()+".jpg").toExternalForm());
            ImageView picture = new ImageView(image);
            HBox hBox = new HBox();
            picture.setFitHeight(100); picture.setFitWidth(100);
            TextField resourceName = new TextField(resource.getName() + "   sell price : " + resource.getSellPrice() + ", buy Price : " + resource.getBuyPrice());
            resourceName.setEditable(false); resourceName.setFocusTraversable(false); resourceName.setPrefWidth(225);
            TextField number = new TextField("0"); number.setEditable(false); number.setFocusTraversable(false); number.setPrefWidth(50);
            number.setBackground(Signup.gray); resourceName.setBackground(Signup.gray);
            ImageView positive = new ImageView(new Image(TradeMain.class.getResource("/Images/resources/positive.jpg").toExternalForm()));
            ImageView negative = new ImageView(new Image(TradeMain.class.getResource("/Images/resources/negative.jpg").toExternalForm()));
            positive.setFitHeight(20); positive.setFitWidth(20);negative.setFitHeight(20); negative.setFitWidth(20);
            TextField totalPrice = new TextField("0"); totalPrice.setPrefWidth(100); totalPrice.setEditable(false);
            totalPrice.setFocusTraversable(false); totalPrice.setBackground(Signup.gray);
            hBox.setAlignment(Pos.CENTER);
            VBox ResourceName = new VBox(); VBox Number = new VBox(); VBox TotalPrice = new VBox(); VBox add = new VBox(); VBox remove = new VBox();
            ResourceName.setSpacing(5); Number.setSpacing(5); TotalPrice.setSpacing(5);
            ResourceName.getChildren().addAll(Signup.LabelMaker("resource name" , 14 , Color.GOLD) , resourceName );
            Number.getChildren().addAll( Signup.LabelMaker("number" , 14 , Color.GOLD) , number );
            TotalPrice.getChildren().addAll( Signup.LabelMaker("total price" , 14 , Color.GOLD) , totalPrice);
            add.getChildren().addAll(Signup.LabelMaker("add" , 14 , Color.GOLD) , positive);
            remove.getChildren().addAll(Signup.LabelMaker("remove" , 14 , Color.GOLD) , negative);
            ResourceName.setAlignment(Pos.CENTER); Number.setAlignment(Pos.CENTER); TotalPrice.setAlignment(Pos.CENTER);
            add.setAlignment(Pos.CENTER); remove.setAlignment(Pos.CENTER);
            getPrice.put(resource.getName(), totalPrice);
            positive.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int price;
                    number.setText(""+(Integer.parseInt(number.getText())+1));
                    if (Integer.parseInt(number.getText()) > 0){
                        price = resource.getBuyPrice();
                    }
                    else if (Integer.parseInt(number.getText()) < 0){
                        price = resource.getSellPrice();
                    }
                    else {
                        price = 0;
                    }
                    totalPrice.setText("" + (Integer.parseInt(number.getText())*price));
                }
            });
            negative.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int price;
                    number.setText(""+(Integer.parseInt(number.getText())-1));
                    if (Integer.parseInt(number.getText()) > 0){
                        price = resource.getBuyPrice();
                    }
                    else if (Integer.parseInt(number.getText()) < 0){
                        price = resource.getSellPrice();
                    }
                    else {
                        price = 0;
                    }
                    totalPrice.setText("" + (Integer.parseInt(number.getText())*price));
                }
            });
            hBox.setSpacing(5);
            hBox.getChildren().addAll(picture , ResourceName , Number , remove , add , TotalPrice);
            Whole.getChildren().addAll(hBox);
        }
        scrollPane.setContent(Whole);

        Button back = new Button("back"); back.setBackground(Signup.gray); back.setPrefWidth(280);
        Button send = new Button("send request"); send.setBackground(Signup.gray); send.setPrefWidth(280);
        HBox buttons = new HBox(); buttons.setSpacing(10); buttons.getChildren().addAll(back , send);
        VBox whole = new VBox(); whole.getChildren().addAll( scrollPane , buttons ); whole.setSpacing(5);
        whole.setLayoutX(460); whole.setLayoutY(230);
        whole.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String name = keyEvent.getCode().getName();
                if (name.equals("Esc")){
                    TradeMain tradeMain = new TradeMain(currentUser);
                    try {
                        tradeMain.start(Main.stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TradeMain tradeMain = new TradeMain(currentUser);
                try {
                    tradeMain.start(Main.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        send.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int x = 0;
                for (Resources resources : Resources.values()){
                    if (!getPrice.get(resources.getName()).getText().equals("0")){
                        x++;
                    }
                }
                if (x > 1) {
                    errorMaker("invalid amount" , "you can't trade multiple items at the same time" , false);
                    return;
                }
                if (x == 0){
                    TradeMain tradeMain = new TradeMain(currentUser);
                    try {
                        tradeMain.start(stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    for (Resources resources : Resources.values()){
                        if (!getPrice.get(resources.getName()).getText().equals("0")){
                            if (Integer.parseInt(getPrice.get(resources.getName()).getText()) >
                                    Government.getGovernmentByUser(currentUser).getBalance()){
                                errorMaker("invalid balance" , "you don't have enough balance for this trade" , false);
                            }
                            else if (Government.getGovernmentByUser(currentUser).getResourceCount().get(resources) +
                            Integer.parseInt(getNumber.get(resources).getText()) < 0  ||
                            Government.getGovernmentByUser(user).getResourceCount().get(resources) -
                                    Integer.parseInt(getNumber.get(resources).getText()) < 0){
                                errorMaker("invalid amount" , "there isn't enough resources to do this trade" , false);
                            }
                            else {
                                Government government = Government.getGovernmentByUser(user);
                                Game currentGame = Controller.getCurrentGame();
                                government.getTradeNotification().add(new Trade(government, currentGame.getCurrentTurn(),
                                        Integer.parseInt(getPrice.get(resources).getText()), resources
                                        , Integer.parseInt(getNumber.get(resources.getName()).getText() ) , "message by graphic"));
                                TradeMain tradeMain = new TradeMain(currentUser);
                                try {
                                    tradeMain.start(stage);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }
            }
        });
        pane.getChildren().add(whole);
    }

    private void makePreviousTrades() {
        VBox vBox = new VBox();
        vBox.setSpacing(8);
        this.scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        scrollPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT , null , null)));
        scrollPane.setPrefWidth(500); scrollPane.setPrefHeight(200);
        int i = 1 ;
        if (Trade.getAllTrades().size() == 0){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("no trade founds.");
            error.setContentText("sorry , there is no trade history to show!!!");
            error.show();
            mainVBox.setVisible(true);
            return;
        }
    for (Trade trade : Trade.getAllTrades()) {
        if (!trade.isAccepted() || !trade.getRecipient().equals(Controller.getCurrentGame().getCurrentTurn()))
            continue;
        VBox vBox1 = new VBox(); vBox1.setSpacing(5); vBox1.setAlignment(Pos.CENTER);
        ImageView iv = new ImageView(new Image(TradeMain.class.getResource("/Images/SimpleBackground.jpg").toExternalForm()));
        iv.setFitWidth(400); iv.setFitHeight(180);
        Text name = new Text("Trade number " + (i)); name.setFill(Color.YELLOW); name.setFont(Font.font(20));
        Text id = new Text("trade id: " ); id.setFill(Color.YELLOW);
        Text id1 = new Text(""+trade.getId()); id1.setFill(Color.BEIGE); HBox Id = new HBox(); Id.getChildren().addAll(id , id1);
        Text sender = new Text("sender: " ); sender.setFill(Color.YELLOW);
        Text sender1 = new Text( trade.getRequester().getOwner().getUsername()); sender1.setFill(Color.BEIGE); HBox Sender = new HBox(); Sender.getChildren().addAll(sender , sender1);
        Text resource = new Text("resource: " ); resource.setFill(Color.YELLOW);
        Text resource1 = new Text(trade.getResource().getName()); resource1.setFill(Color.BEIGE); HBox Resource = new HBox(); Resource.getChildren().addAll(resource , resource1);
        Text amount = new Text("amount: " ); amount.setFill(Color.YELLOW);
        Text amount1 = new Text("" + trade.getAmount()); amount1.setFill(Color.BEIGE); HBox Amount = new HBox(); Amount.getChildren().addAll(amount , amount1);
        Text request = new Text("request message: " ); request.setFill(Color.YELLOW);
        Text request1 = new Text(trade.getRequestMessage()); request1.setFill(Color.BEIGE);HBox Request = new HBox(); Request.getChildren().addAll(request , request1);
        Text acceptMessage = new Text( "accept message: "); acceptMessage.setFill(Color.YELLOW);
        Text acceptMessage1 = new Text(trade.getAcceptMessage()); acceptMessage1.setFill(Color.BEIGE); HBox AcceptMessage = new HBox(); AcceptMessage.getChildren().addAll(acceptMessage , acceptMessage1);
        Id.setAlignment(Pos.CENTER); Sender.setAlignment(Pos.CENTER); Resource.setAlignment(Pos.CENTER); Amount.setAlignment(Pos.CENTER); Request.setAlignment(Pos.CENTER); AcceptMessage.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(name , Id , Sender , Resource , Amount , Request , AcceptMessage );
        StackPane stackPane = new StackPane(); stackPane.getChildren().add(iv); stackPane.getChildren().add(vBox1); stackPane.setAlignment(Pos.CENTER);
        vBox.getChildren().add(stackPane);
        i++;
    }
        scrollPane.setContent(vBox);
        VBox vBox1 = new VBox(); vBox1.setSpacing(15); vBox1.setPrefWidth(500);
        Button back = new Button("back"); back.setBackground(brown); back.setPrefWidth(400); back.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(scrollPane , back); vBox1.setLayoutX(500); vBox1.setLayoutY(300);
        pane.getChildren().add(vBox1);
        scrollPane.requestFocus();
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TradeMain tradeMain = new TradeMain(currentUser);
                try {
                    tradeMain.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
    public static boolean errorMaker(String header , String body , boolean isConfirmation){
        if (!isConfirmation){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText(header);
            error.setContentText(body);
            error.show();
            return true;
        }
        else {
            Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
            errorAlert.setHeaderText(header);
            errorAlert.setContentText(body);
            Optional<ButtonType> result = errorAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                return true;
            }
            return false;
        }
    }
}


