package com.example.gameoflife;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import com.SGS.dependency.*;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;





import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //create a scene with the table
        Pane pane = new Pane();
        int HEIGHT = 500;
        int WIDTH = 500;
        int w = 10;
        int h = 10;
        Rectangle rectangle = new Rectangle(10, 10, 10, 10);
        Universe universe = new Universe(new Player[0], 8);
        Cell[][] cells = CellCreator.createCells(w, h, universe);
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                Rectangle r = cell.getRectangle(WIDTH, HEIGHT, w, h);
                r.setOnMouseClicked(e -> {
                    cell.click();
                    r.setFill(cell.isActive() ? javafx.scene.paint.Color.BLACK : javafx.scene.paint.Color.WHITE);
                });

                pane.getChildren().add(r);
            }
        }


        Scene scene = new Scene(pane, 500, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}