package com.example.gameoflife;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import com.SGS.dependency.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;





import java.io.IOException;


public class MainApp extends Application {
    private Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        showIntroPage();
    }


    public void showRectangleScene(Cell[][] cells) {
        Stage stage = new Stage();
        Pane pane = new Pane();
        int HEIGHT = 500;
        int WIDTH = 500;
        int w = 10;
        int h = 10;
        Universe universe = new Universe(new Player[0], 8);
        cells[0][0].setIsActive(true);

        double rectWidth = (double) WIDTH / w;
        double rectHeight = (double) HEIGHT / h;

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                Cell cell = cells[row][col];
                Rectangle r = cell.getRectangle(WIDTH, HEIGHT, w, h);
                r.setX(col * rectWidth);
                r.setY(row * rectHeight);
                r.setWidth(rectWidth);
                r.setHeight(rectHeight);
                r.setFill(cell.isActive() ? Color.BLACK : Color.WHITE);
                r.setStroke(Color.GRAY); // Optional: to see the grid lines
                r.setOnMouseClicked(e -> {
                    cell.click();
                    r.setFill(cell.isActive() ? Color.BLACK : Color.WHITE);
                });

                pane.getChildren().add(r);
            }
        }
        primaryStage.setScene(new Scene(pane, WIDTH, HEIGHT));
        primaryStage.show();
    }
    public void showIntroPage() {
        IntroPage introScene = new IntroPage(this);
        primaryStage.setScene(introScene.getScene());
        primaryStage.setTitle("Intro Page");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}