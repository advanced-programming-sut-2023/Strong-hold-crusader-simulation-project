package org.example.model;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Arrays;

public class Reports {
    static double fearRate=0;
    static double foodRate=0;
    static double taxRate=0;
    public static Pane reports(){

        GridPane gridPane=new GridPane();
        Pane pane=new Pane();
        for (int i = 0; i < 3; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            RowConstraints rowConst = new RowConstraints();
            colConst.setPrefWidth(80);
            rowConst.setPrefHeight(50);
            colConst.setHalignment(HPos.CENTER);
            rowConst.setValignment(VPos.CENTER);
            gridPane.getColumnConstraints().add(colConst);
            gridPane.getRowConstraints().add(rowConst);
        }


        Button popularity=new Button("pop & treasury");
        gridPane.add(popularity,0,0,1,1);
        Button population=new Button("population");
        gridPane.add(population,0,1,1,1);
        Button tax=new Button("tax");
        gridPane.add(tax,0,2,1,1);
        Button food=new Button("food");
        gridPane.add(food,1,0,1,1);
        Button fear=new Button("fear");
        gridPane.add(fear,1,1,1,1);

        Button back=new Button("back");
        back.setLayoutX(350);
        back.setTranslateY(100);
        pane.getChildren().add(back);

        gridPane.setTranslateX(500);
        pane.getChildren().addAll(gridPane);
        population().setVisible(true);
        population.setOnMouseClicked(e -> {
            pane.getChildren().remove(1);
            pane.getChildren().add(population());

        });

        food.setOnMouseClicked(e -> {
            pane.getChildren().remove(1);
            pane.getChildren().add(food());

        });

        fear.setOnMouseClicked(e -> {
            pane.getChildren().remove(1);
            pane.getChildren().add(fear());

        });

        tax.setOnMouseClicked(e -> {
            pane.getChildren().remove(1);
            pane.getChildren().add(tax());

        });

        popularity.setOnMouseClicked(e -> {
            pane.getChildren().remove(1);
            pane.getChildren().add(popularity());

        });

        back.setOnMouseClicked(e ->{
            if (!pane.getChildren().contains(gridPane)){
                pane.getChildren().remove(1);
                pane.getChildren().add(gridPane);
            }
        });
        return pane;
    }
    static Pane population(){

        CategoryAxis xaxis= new CategoryAxis();
        NumberAxis yaxis = new NumberAxis(0,50,100);

        BarChart<String,Float> bar = new BarChart(xaxis,yaxis);

        XYChart.Series<String,Float> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data("now",0.));
        series.getData().add(new XYChart.Data("turn2",0.));
        series.getData().add(new XYChart.Data("turn3",0));

        bar.getData().add(series);
        bar.setTranslateX(400);
        bar.setTranslateY(0);
        bar.setPrefHeight(100);
        bar.setPrefWidth(300);

        Pane population=new Pane();
        population.getChildren().add(bar);
        return population;
    }

    static Pane popularity(){
        Pane pop=new Pane();
        ImageView imageView;
        double popularity=(fearRate+foodRate+taxRate)/3;
        Label label=new Label("balance: "+Integer.toString(Government.getBalance()));
        pop.getChildren().add(label);
        label.setLayoutX(750);



        if (popularity>=60){
            imageView=new ImageView(Reports.class.getResource("/Images/happy.png").toExternalForm());
        }
        else if (popularity>40&&popularity<60){
            imageView=new ImageView(Reports.class.getResource("/Images/poker.png").toExternalForm());
        }
        else {
            imageView=new ImageView(Reports.class.getResource("/Images/sad.png").toExternalForm());
        }
        imageView.setFitHeight(100);
        imageView.setFitWidth(80);
        imageView.setX(480);
        pop.getChildren().add(imageView);

        return pop;
    }
    static Pane fear(){
        Pane fear=new Pane();

        Slider slider = new Slider(1, 100, 1);
        slider.setBlockIncrement(1);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setShowTickLabels(false);
        slider.setSnapToTicks(true);
        fear.getChildren().add(slider);
        slider.setTranslateX(450);
        slider.setLayoutY(70);
        slider.setPrefWidth(400);

        slider.valueProperty().addListener((obs, oldval, newVal) -> {fearRate=newVal.doubleValue();
            ImageView imageView;
            if (fearRate>=60){
                if (fear.getChildren().size()>1){
                    fear.getChildren().get(fear.getChildren().size()-1);
                }
                imageView=new ImageView(Reports.class.getResource("/Images/happy.png").toExternalForm());
            }
            else if (fearRate>40&&fearRate<60){
                if (fear.getChildren().size()>1){
                    fear.getChildren().get(fear.getChildren().size()-1);
                }
                imageView=new ImageView(Reports.class.getResource("/Images/poker.png").toExternalForm());
            }
            else {
                if (fear.getChildren().size()>1){
                    fear.getChildren().get(fear.getChildren().size()-1);
                }
                imageView=new ImageView(Reports.class.getResource("/Images/sad.png").toExternalForm());
            }
            imageView.setFitHeight(60);
            imageView.setFitWidth(50);
            imageView.setX(380);
            fear.getChildren().add(imageView);
        });
        return fear;
    }

    static Pane food(){
        Pane food=new Pane();


        Slider slider = new Slider(0, 100, 1);
        slider.setBlockIncrement(1);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setShowTickLabels(false);
        slider.setSnapToTicks(true);
        food.getChildren().add(slider);
        slider.setTranslateX(450);
        slider.setLayoutY(70);
        slider.setPrefWidth(400);

        slider.valueProperty().addListener((obs, oldval, newVal) -> {foodRate=newVal.doubleValue();

            ImageView imageView;
            if (foodRate>=60){
                if (food.getChildren().size()>1){
                    food.getChildren().get(food.getChildren().size()-1);
                }
                imageView=new ImageView(Reports.class.getResource("/Images/happy.png").toExternalForm());
            }
            else if (foodRate>40&&foodRate<60){
                if (food.getChildren().size()>1){
                    food.getChildren().get(food.getChildren().size()-1);
                }
                imageView=new ImageView(Reports.class.getResource("/Images/poker.png").toExternalForm());
            }
            else {
                if (food.getChildren().size()>1){
                    food.getChildren().get(food.getChildren().size()-1);
                }
                imageView=new ImageView(Reports.class.getResource("/Images/sad.png").toExternalForm());
            }
            imageView.setFitHeight(60);
            imageView.setFitWidth(50);
            imageView.setX(380);
            food.getChildren().add(imageView);
        });

        return food;
    }
    static Pane tax(){
        Pane tax=new Pane();

        Slider slider = new Slider(1, 100, 1);
        slider.setBlockIncrement(1);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setShowTickLabels(false);
        slider.setSnapToTicks(true);
        tax.getChildren().add(slider);
        slider.setTranslateX(450);
        slider.setLayoutY(70);
        slider.setPrefWidth(400);

        slider.valueProperty().addListener((obs, oldval, newVal) -> {taxRate=newVal.doubleValue();
            ImageView imageView;
            if (taxRate>=60){
                if (tax.getChildren().size()>1){
                    tax.getChildren().get(tax.getChildren().size()-1);
                }
                imageView=new ImageView(Reports.class.getResource("/Images/happy.png").toExternalForm());
            }
            else if (taxRate>40&&taxRate<60){
                if (tax.getChildren().size()>1){
                    tax.getChildren().get(tax.getChildren().size()-1);
                }
                imageView=new ImageView(Reports.class.getResource("/Images/poker.png").toExternalForm());
            }
            else {
                if (tax.getChildren().size()>1){
                    tax.getChildren().get(tax.getChildren().size()-1);
                }
                imageView=new ImageView(Reports.class.getResource("/Images/sad.png").toExternalForm());
            }
            imageView.setFitHeight(60);
            imageView.setFitWidth(50);
            imageView.setX(380);
            tax.getChildren().add(imageView);
        });
        return tax;
    }
}
