package com.example.gameoflife;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import com.SGS.dependency.*;
import javafx.scene.control.Tab;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;





import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //create a scene with the table
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Rectangle rectangle = new Rectangle(10, 10, 10, 10);
        Table table = new Table(10, 10);
        table.setCell(1, 1, new Cell(true));

        //show
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}