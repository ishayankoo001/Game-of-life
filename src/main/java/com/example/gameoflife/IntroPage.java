package com.example.gameoflife;

import com.SGS.dependency.Player;
import com.SGS.dependency.Universe;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class IntroPage {

    private Scene scene;
    private MainApp mainApp;

    public IntroPage(MainApp mainApp) {
        this.mainApp = mainApp;
        initialize();
    }

    private void initialize() {
        VBox vbox = new VBox();
        vbox.setSpacing(20);

        Pane gridPane = new Pane();
        int HEIGHT = 500;
        int WIDTH = 500;
        int w = 10;
        int h = 10;
        Universe universe = new Universe(new Player[0], 8);
        Cell[][] cells = CellCreator.createCells(w, h, universe);
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

                gridPane.getChildren().add(r);
            }
        }

        Button startButton = new Button("Start");
        startButton.setOnAction(event -> mainApp.showRectangleScene(cells));

        vbox.getChildren().addAll(gridPane, startButton);
        scene = new Scene(vbox, WIDTH, HEIGHT + 40); // Extra height for the button
    }

    public Scene getScene() {
        return scene;
    }
}