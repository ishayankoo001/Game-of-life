package com.example.gameoflife;

import com.SGS.dependency.Player;
import com.SGS.dependency.Universe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class IntroPage {

    private Scene scene;
    private MainApp mainApp;

    public IntroPage(MainApp mainApp, int w, int h) {
        this.mainApp = mainApp;
        initialize(w, h);
    }

    public Cell[][] initialize(int w, int h) {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        Pane gridPane = new Pane();
        int HEIGHT = 500;
        int WIDTH = 500;
        Universe universe = new Universe(new Player[0], 8);
        Cell[][] cells = CellCreator.createCells(w, h, universe);

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
                int finalRow = row;
                int finalCol = col;
                r.setOnMouseClicked(e -> {

                    cell.click();
                    if (finalRow == 0 && finalCol == 1) {
                        System.out.println(String.format("Cell is now %s", cell.isActive() ? "active" : "inactive"));
                    }
                    r.setFill(cell.isActive() ? Color.BLACK : Color.WHITE);
                });

                gridPane.getChildren().add(r);
            }
        }

        Button startButton = new Button("Start");
        startButton.setPrefSize(100, 40);

        //return cells when start button is clicked
        startButton.setOnAction(e -> {
            mainApp.mainLoop(mainApp.getPrimaryStage(),cells);
        });

        //add bottom padding to start button
        VBox.setMargin(startButton, new Insets(0, 0, 5, 0));


        startButton.getStyleClass().add("button");
        vbox.getChildren().addAll(gridPane, startButton);
        scene = new Scene(vbox, WIDTH, HEIGHT + 40); // Extra height for the button
        scene.getStylesheets().add("style.css");
        return cells;
    }

    public Scene getScene() {
        return scene;
    }
}