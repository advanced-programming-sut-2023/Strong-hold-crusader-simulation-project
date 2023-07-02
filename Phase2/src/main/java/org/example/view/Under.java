package org.example.view;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Optional;

public class Under {
    public static Node selectedThing;
    public static GridPane gridPaneSag;
    static ScrollPane scrollPane;
    static Pane pane;
    static int add;
    public static Pane asleKar(GridPane gridPane,ScrollPane scrollPane0)
    {
        scrollPane = scrollPane0;
        pane=new Pane();
//        draggable(gridPane);
        gridPaneSag=gridPane;
        pane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY){
                System.out.println("تو گل بندری اره اره والا");

                selectedThing=null;
            }
        });

        Image image1 = new Image(Under.class.getResource("/Images/icons/i1.png").toExternalForm());
        ImageView imageView1 = new ImageView(image1);
        imageView1.setX(336);
        imageView1.setY(110);
        imageView1.setFitHeight(38);
        imageView1.setFitWidth(38);
        pane.getChildren().add(imageView1);


        Image image2 = new Image(Under.class.getResource("/Images/icons/i2.png").toExternalForm());
        ImageView imageView2 = new ImageView(image2);
        imageView2.setX(370);
        imageView2.setY(117);
        imageView2.setFitHeight(31);
        imageView2.setFitWidth(31);
        pane.getChildren().add(imageView2);

        Image image3 = new Image(Under.class.getResource("/Images/icons/i3.png").toExternalForm());
        ImageView imageView3 = new ImageView(image3);
        imageView3.setX(400);
        imageView3.setY(114);
        imageView3.setFitHeight(35);
        imageView3.setFitWidth(35);
        pane.getChildren().add(imageView3);

        Image image4 = new Image(Under.class.getResource("/Images/icons/i4.png").toExternalForm());
        ImageView imageView4 = new ImageView(image4);
        imageView4.setX(430);
        imageView4.setY(114);
        imageView4.setFitHeight(33);
        imageView4.setFitWidth(33);
        pane.getChildren().add(imageView4);

        Image image5 = new Image(Under.class.getResource("/Images/icons/i5.png").toExternalForm());
        ImageView imageView5 = new ImageView(image5);
        imageView5.setX(461);
        imageView5.setY(115.2);
        imageView5.setFitHeight(32);
        imageView5.setFitWidth(32);
        pane.getChildren().add(imageView5);

        Image image6 = new Image(Under.class.getResource("/Images/icons/i6.png").toExternalForm());
        ImageView imageView6 = new ImageView(image6);
        imageView6.setX(491);
        imageView6.setY(116);
        imageView6.setFitHeight(30);
        imageView6.setFitWidth(30);
        pane.getChildren().add(imageView6);


        Image tower = new Image(Under.class.getResource("/Images/buildings/buildings2/castle buildings/sag.png").toExternalForm());
        ImageView towerView = new ImageView(tower);
        towerView.setFitWidth(25);
        towerView.setFitHeight(25);
        towerView.setX(740);
        towerView.setY(116);
        pane.getChildren().add(towerView);

        Image gate = new Image(Under.class.getResource("/Images/buildings/buildings2/castle buildings/sag2.png").toExternalForm());
        ImageView gateView = new ImageView(gate);
        gateView.setFitWidth(25);
        gateView.setFitHeight(25);
        gateView.setX(780);
        gateView.setY(116);
        pane.getChildren().add(gateView);


        Circle delete=new Circle(10,Color.RED);
        delete.setCenterX(870);
        delete.setCenterY(70);
        pane.getChildren().add(delete);

        Circle undo=new Circle(10,Color.RED);
        undo.setCenterX(870);
        undo.setCenterY(95);
        pane.getChildren().add(undo);


        Pane backUp=new Pane(castleBuildings()
                ,townBuildings(),foodBuildings(),weaponBuildings(),
                industryBuildings(),farmBuildings(),gate(),tower());
        fuckingInvisible(backUp);


        backUp.getChildren().get(0).setVisible(true);
        pane.getChildren().add(backUp);

        imageView1.setOnMouseClicked(e -> {
            fuckingInvisible(backUp);
            towerView.setVisible(true);
            gateView.setVisible(true);

            backUp.getChildren().get(0).setVisible(true);
        });

        imageView4.setOnMouseClicked(e -> {
            fuckingInvisible(backUp);
            towerView.setVisible(false);
            gateView.setVisible(false);
            backUp.getChildren().get(1).setVisible(true);
        });

        imageView6.setOnMouseClicked(e -> {
            fuckingInvisible(backUp);
            towerView.setVisible(false);
            gateView.setVisible(false);
            backUp.getChildren().get(2).setVisible(true);
        });

        imageView5.setOnMouseClicked(e -> {
            fuckingInvisible(backUp);
            towerView.setVisible(false);
            gateView.setVisible(false);
            backUp.getChildren().get(3).setVisible(true);
        });

        imageView2.setOnMouseClicked(e -> {
            fuckingInvisible(backUp);
            towerView.setVisible(false);
            gateView.setVisible(false);
            backUp.getChildren().get(4).setVisible(true);
        });

        imageView3.setOnMouseClicked(e -> {
            fuckingInvisible(backUp);
            towerView.setVisible(false);
            gateView.setVisible(false);
            backUp.getChildren().get(5).setVisible(true);
        });

        gateView.setOnMouseClicked(e -> {
            fuckingInvisible(backUp);
            backUp.getChildren().get(6).setVisible(true);

        });

        towerView.setOnMouseClicked(e -> {
            fuckingInvisible(backUp);
            backUp.getChildren().get(7).setVisible(true);

        });

        delete.setOnMouseClicked(e ->{
            if (selectedThing!=null){
                gridPaneSag.getChildren().remove(selectedThing);
            }
        });

        undo.setOnMouseClicked(e ->{
            gridPaneSag.getChildren().remove(gridPaneSag.getChildren().size()-1);
        });
        return pane;
    }


    static double scrollPositionV=0;
    static double scrollPositionH=0;
    static ImageView dragging;

    public static void setSelectedThing(ImageView imageView){
        imageView.setOnMouseClicked(e -> {
            if (e.getClickCount()==2){
                selectedThing= imageView;
                System.out.println(selectedThing);
            }
        });
    }


    public static void dragDrop(ImageView... imageView){

        for (ImageView imageViews:imageView){

            imageViews.setOnMousePressed(e -> {
                dragging= new ImageView(imageViews.getImage());
                dragging.setFitHeight(imageViews.getFitHeight());
                dragging.setFitWidth(imageViews.getFitWidth());
                dragging.setX(imageViews.getX());
                dragging.setY(imageViews.getY());
                pane.getChildren().add(dragging);
                startX = e.getSceneX() - imageViews.getTranslateX();
                startY = e.getSceneY() - imageViews.getTranslateY();
            });

            imageViews.setOnMouseDragged(e -> {
                dragging.setTranslateX(e.getSceneX() - startX);
                dragging.setTranslateY(e.getSceneY() - startY);

            });

            imageViews.setOnMouseReleased(e -> {
                pane.getChildren().remove(dragging);
                DoubleProperty vvalue = scrollPane.vvalueProperty();
                vvalue.addListener((observable, oldValue, newValue) -> {
                    scrollPositionV = newValue.doubleValue();
                });

                DoubleProperty hvalue = scrollPane.hvalueProperty();
                hvalue.addListener((observable, oldValue, newValue) -> {
                    scrollPositionH = newValue.doubleValue();
                });

                int col=  ((int) e.getSceneY()/100 +(int) (scrollPositionV*GameMenu.currentMap.getSize()));
                int row=  ((int) e.getSceneX()/100 +(int) (scrollPositionH*GameMenu.currentMap.getSize()));

                ImageView imageVieww = new ImageView(imageViews.getImage());
                imageVieww.setFitWidth(imageViews.getFitWidth());
                imageVieww.setFitHeight(imageViews.getFitHeight());
                gridPaneSag.add(imageVieww,row,col,1,1);
                setSelectedThing(imageVieww);
            });
        }
    }
    static void fuckingInvisible(Pane backUp){

        for (int i=0 ; i< backUp.getChildren().size() ; i++){
            backUp.getChildren().get(i).setVisible(false);
        }
    }
    static double startX;
    static double startY;

    public static Pane castleBuildings()  {

        Pane castlePane=new Pane();

        Pane result=new Pane();

        Image image = new Image(Under.class.getResource("/Images/buildings/buildings2/castle buildings/castleBuil.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        imageView.setX(350);
        imageView.setY(25);

        castlePane.getChildren().add(imageView);



        Image image1 = new Image(Under.class.getResource("/Images/buildings/buildings2/castle buildings/castle1.png").toExternalForm());
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(65);
        imageView1.setFitHeight(65);
        imageView1.setX(430);
        imageView1.setY(30);
        castlePane.getChildren().add(imageView1);



        Image image2 = new Image(Under.class.getResource("/Images/buildings/buildings2/castle buildings/castle2.png").toExternalForm());
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(65);
        imageView2.setFitHeight(65);
        imageView2.setX(510);
        imageView2.setY(30);
        castlePane.getChildren().add(imageView2);



        Image image4 = new Image(Under.class.getResource("/Images/buildings/buildings2/castle buildings/castle4.png").toExternalForm());
        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitWidth(65);
        imageView4.setFitHeight(65);
        imageView4.setX(590);
        imageView4.setY(30);
        castlePane.getChildren().add(imageView4);



        Image image6 = new Image(Under.class.getResource("/Images/buildings/buildings2/castle buildings/castle6.png").toExternalForm());
        ImageView imageView6 = new ImageView(image6);
        imageView6.setFitWidth(85);
        imageView6.setFitHeight(85);
        imageView6.setX(670);
        imageView6.setY(30);
        castlePane.getChildren().add(imageView6);


        dragDrop(imageView,imageView6,imageView4,imageView2,imageView1);
        result.getChildren().addAll(castlePane);
        return result;
    }
    public static Pane tower(){

        Pane towerPane=new Pane();

        Image gh = new Image(Under.class.getResource("/Images/buildings/buildings2/castle buildings/t1.png").toExternalForm());
        ImageView ghView = new ImageView(gh);
        ghView.setFitWidth(35);
        ghView.setFitHeight(85);
        ghView.setX(400);
        ghView.setY(20);


        Image gh1 = new Image(Under.class.getResource("/Images/buildings/buildings2/castle buildings/t2.png").toExternalForm());
        ImageView gh1View = new ImageView(gh1);
        gh1View.setFitWidth(75);
        gh1View.setFitHeight(75);
        gh1View.setX(475);
        gh1View.setY(35);

        Image gh2 = new Image(Under.class.getResource("/Images/buildings/buildings2/castle buildings/t3.png").toExternalForm());
        ImageView gh2View = new ImageView(gh2);
        gh2View.setFitWidth(75);
        gh2View.setFitHeight(75);
        gh2View.setX(560);
        gh2View.setY(35);
        towerPane.getChildren().addAll(ghView,gh1View,gh2View);

        dragDrop(ghView,gh1View,gh2View);
        return towerPane;
    };
    public static Pane gate(){
        Pane gatePane=new Pane();
        Image gh = new Image(Under.class.getResource("/Images/buildings/buildings2/castle buildings/gh.png").toExternalForm());
        ImageView ghView = new ImageView(gh);
        ghView.setFitWidth(35);
        ghView.setFitHeight(85);
        ghView.setX(400);
        ghView.setY(20);


        Image gh1 = new Image(Under.class.getResource("/Images/buildings/buildings2/castle buildings/khar.png").toExternalForm());
        ImageView gh1View = new ImageView(gh1);
        gh1View.setFitWidth(75);
        gh1View.setFitHeight(75);
        gh1View.setX(475);
        gh1View.setY(40);

        Image gh2 = new Image(Under.class.getResource("/Images/buildings/buildings2/castle buildings/gh2.png").toExternalForm());
        ImageView gh2View = new ImageView(gh2);
        gh2View.setFitWidth(75);
        gh2View.setFitHeight(75);
        gh2View.setX(560);
        gh2View.setY(45);

        Image ghh = new Image(Under.class.getResource("/Images/buildings/buildings2/castle buildings/ghh.png").toExternalForm());
        ImageView ghhView = new ImageView(ghh);
        ghhView.setFitWidth(75);
        ghhView.setFitHeight(75);
        ghhView.setX(640);
        ghhView.setY(45);

        gatePane.getChildren().addAll(ghhView,gh2View,gh1View,ghView);
        dragDrop(ghhView,gh2View,gh1View,ghView);
        return gatePane;
    }
    public static Pane townBuildings(){
        Pane townPane=new Pane();

        Image image = new Image(Under.class.getResource("/Images/buildings/buildings2/Town Buildings/t2.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        imageView.setX(350);
        imageView.setY(25);
        townPane.getChildren().add(imageView);


        Image image1 = new Image(Under.class.getResource("/Images/buildings/buildings2/Town Buildings/t3.png").toExternalForm());
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(65);
        imageView1.setFitHeight(65);
        imageView1.setX(430);
        imageView1.setY(30);
        townPane.getChildren().add(imageView1);



        Image image2 = new Image(Under.class.getResource("/Images/buildings/buildings2/Town Buildings/t4.png").toExternalForm());
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(65);
        imageView2.setFitHeight(65);
        imageView2.setX(510);
        imageView2.setY(30);
        townPane.getChildren().add(imageView2);



        Image image4 = new Image(Under.class.getResource("/Images/buildings/buildings2/Town Buildings/t5.png").toExternalForm());
        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitWidth(65);
        imageView4.setFitHeight(65);
        imageView4.setX(590);
        imageView4.setY(30);
        townPane.getChildren().add(imageView4);



        Image image6 = new Image(Under.class.getResource("/Images/buildings/buildings2/Town Buildings/t6.png").toExternalForm());
        ImageView imageView6 = new ImageView(image6);
        imageView6.setFitWidth(70);
        imageView6.setFitHeight(70);
        imageView6.setX(670);
        imageView6.setY(30);
        townPane.getChildren().add(imageView6);

        Image image0 = new Image(Under.class.getResource("/Images/buildings/buildings2/Town Buildings/t1.gif").toExternalForm());
        ImageView imageView0 = new ImageView(image0);
        imageView0.setFitWidth(70);
        imageView0.setFitHeight(70);
        imageView0.setX(750);
        imageView0.setY(25);
        townPane.getChildren().add(imageView0);

        dragDrop(imageView,imageView0,imageView4,imageView2,imageView6,imageView1);
        return townPane;
    }
    public static Pane foodBuildings(){
        Pane foodPane=new Pane();

        Image image = new Image(Under.class.getResource("/Images/buildings/buildings2/Food Buildings/fi1.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        imageView.setX(350);
        imageView.setY(25);
        foodPane.getChildren().add(imageView);


        Image image1 = new Image(Under.class.getResource("/Images/buildings/buildings2/Food Buildings/fi2.png").toExternalForm());
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(65);
        imageView1.setFitHeight(65);
        imageView1.setX(430);
        imageView1.setY(30);
        foodPane.getChildren().add(imageView1);


        Image image2 = new Image(Under.class.getResource("/Images/buildings/buildings2/Food Buildings/fi3.gif").toExternalForm());
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(70);
        imageView2.setFitHeight(70);
        imageView2.setX(520);
        imageView2.setY(25);
        foodPane.getChildren().add(imageView2);


        Image image3 = new Image(Under.class.getResource("/Images/buildings/buildings2/Food Buildings/fi4.gif").toExternalForm());
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitWidth(65);
        imageView3.setFitHeight(65);
        imageView3.setX(600);
        imageView3.setY(30);
        foodPane.getChildren().add(imageView3);

        dragDrop(imageView,imageView1,imageView2,imageView3);
        return foodPane;
    }
    public static Pane weaponBuildings(){
        Pane weaponPane=new Pane();


        Image image = new Image(Under.class.getResource("/Images/buildings/buildings2/Weapons Buildings/w1.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        imageView.setX(350);
        imageView.setY(25);
        weaponPane.getChildren().add(imageView);


        Image image1 = new Image(Under.class.getResource("/Images/buildings/buildings2/Weapons Buildings/w2.png").toExternalForm());
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(65);
        imageView1.setFitHeight(65);
        imageView1.setX(430);
        imageView1.setY(30);
        weaponPane.getChildren().add(imageView1);

        dragDrop(imageView,imageView1);
        return weaponPane;
    }
    public static Pane industryBuildings(){
        Pane industryPane=new Pane();

        Image image = new Image(Under.class.getResource("/Images/buildings/buildings2/Industry buildings in Crusader/in1.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        imageView.setX(350);
        imageView.setY(30);
        industryPane.getChildren().add(imageView);


        Image image1 = new Image(Under.class.getResource("/Images/buildings/buildings2/Industry buildings in Crusader/in2.gif").toExternalForm());
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(60);
        imageView1.setFitHeight(60);
        imageView1.setX(425);
        imageView1.setY(30);
        industryPane.getChildren().add(imageView1);



        Image image2 = new Image(Under.class.getResource("/Images/buildings/buildings2/Industry buildings in Crusader/in3.gif").toExternalForm());
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(60);
        imageView2.setFitHeight(60);
        imageView2.setX(500);
        imageView2.setY(30);
        industryPane.getChildren().add(imageView2);


        Image image3 = new Image(Under.class.getResource("/Images/buildings/buildings2/Industry buildings in Crusader/in4.gif").toExternalForm());
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitWidth(60);
        imageView3.setFitHeight(60);
        imageView3.setX(575);
        imageView3.setY(30);
        industryPane.getChildren().add(imageView3);



        Image image4 = new Image(Under.class.getResource("/Images/buildings/buildings2/Industry buildings in Crusader/in5.gif").toExternalForm());
        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitWidth(60);
        imageView4.setFitHeight(60);
        imageView4.setX(650);
        imageView4.setY(30);
        industryPane.getChildren().add(imageView4);



        Image image6 = new Image(Under.class.getResource("/Images/buildings/buildings2/Industry buildings in Crusader/in6.png").toExternalForm());
        ImageView imageView6 = new ImageView(image6);
        imageView6.setFitWidth(60);
        imageView6.setFitHeight(60);
        imageView6.setX(725);
        imageView6.setY(30);
        industryPane.getChildren().add(imageView6);

        Image image7 = new Image(Under.class.getResource("/Images/buildings/buildings2/Industry buildings in Crusader/in7.png").toExternalForm());
        ImageView imageView7 = new ImageView(image7);
        imageView7.setFitWidth(60);
        imageView7.setFitHeight(60);
        imageView7.setX(800);
        imageView7.setY(30);
        industryPane.getChildren().add(imageView7);

        dragDrop(imageView,imageView1,imageView2,imageView3,imageView4,imageView6,imageView7);
        return industryPane;

    }
    public static Pane farmBuildings(){
        Pane farmPane=new Pane();
        Image image1 = new Image(Under.class.getResource("/Images/buildings/buildings2/farm buildings/dairy farmer.png").toExternalForm());
        ImageView imageView1 = new ImageView(image1);
        imageView1.setX(350);
        imageView1.setY(40);
        imageView1.setFitHeight(60);
        imageView1.setFitWidth(60);
        farmPane.getChildren().add(imageView1);


        Image image2 = new Image(Under.class.getResource("/Images/buildings/buildings2/farm buildings/hops farmer.png").toExternalForm());
        ImageView imageView2 = new ImageView(image2);
        imageView2.setX(420);
        imageView2.setY(40);
        imageView2.setFitHeight(60);
        imageView2.setFitWidth(60);
        farmPane.getChildren().add(imageView2);

        Image image3 = new Image(Under.class.getResource("/Images/buildings/buildings2/farm buildings/wheat farmer.png").toExternalForm());
        ImageView imageView3 = new ImageView(image3);
        imageView3.setX(490);
        imageView3.setY(40);
        imageView3.setFitHeight(60);
        imageView3.setFitWidth(60);
        farmPane.getChildren().add(imageView3);

        dragDrop(imageView1,imageView2,imageView3);
        return farmPane;
    }
}
