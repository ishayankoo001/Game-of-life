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
        cells[0][0].setIsActive(true);
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                pane.getChildren().add(cell.getRectangle(WIDTH, HEIGHT, w, h));
            }
        }

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        stage.setTitle("Game of life!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}