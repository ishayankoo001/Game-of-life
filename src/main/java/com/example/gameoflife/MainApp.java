package com.example.gameoflife;
import javafx.animation.AnimationTimer;
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

    int w = 30;
    int h = 30;
    private long lastUpdate = 0; // Time of last update
    private static final long UPDATE_INTERVAL = 1_000_000_000;
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        showIntroPage();
        IntroPage introPage = new IntroPage(this, w, h);
        introPage.initialize(w,h);
    }

    public void mainLoop(Stage stage, Cell[][] cells) {
        primaryStage = stage;
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= UPDATE_INTERVAL) {
                    lastUpdate = now;
                    showRectangleScene(cells, w, h);
                }
            }
        };
        gameLoop.start();
    }


    public void showRectangleScene(Cell[][] cells, int w , int h) {
        GolGameRound gameRound = new GolGameRound(cells);
        Stage stage = new Stage();
        Pane pane = new Pane();
        int HEIGHT = 500;
        int WIDTH = 500;
        Universe universe = new Universe(new Player[0], 8);

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
        //wait for two seconds


        for (int i = 0; i < w; i++) {
            for (Cell[] row : cells) {
                for (Cell cell : row) {
                    cell.sendToAll();
                }
            }
        }

        for (int i = 0; i < w; i++) {
            for (Cell[] row : cells) {
                for (Cell cell : row) {
                    cell.setMessagesToRespond(cell.getNewMessagesInbox());
                }
            }
        }



        for (int i = 0; i < w; i++) {

            for (Cell[] row : cells) {
                for (Cell cell : row) {
                    GolResponseFunction responseFunction = new GolResponseFunction();
                    cell.setResponseFunction(responseFunction);
                    Message response = responseFunction.getResponse(cell.getMessagesToRespond());

                    Message deadMessage = new Message(new int[universe.getK()]);
                    Message aliveMessage = new Message(new int[universe.getK()]);
                    for (int m = 0; m < universe.getK(); m++) {
                        aliveMessage.getNumbers()[m] = 1;
                        deadMessage.getNumbers()[m] = 0;
                    }
                    if (response.equals(deadMessage)){
                        cell.setIsActive(false);
                    } else if (response.equals(aliveMessage)) {
                        cell.setIsActive(true);
                    }

                }
            }


        }

    }
    public void showIntroPage() {
        IntroPage introScene = new IntroPage(this, w, h);
        primaryStage.setScene(introScene.getScene());
        primaryStage.setTitle("Welcome to the Game of Life!");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}