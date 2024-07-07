package com.example.gameoflife;

import com.SGS.dependency.Player;
import com.SGS.dependency.Universe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
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
        gridPane.setPadding(new Insets(10, 10, 10, 10));  // Adds padding around the grid
        int PAGE_HEIGHT = 620;
        int PAGE_WIDTH = 520;
        int HEIGHT = 500;
        int WIDTH = 500;
        int centerX = 15;
        Universe universe = new Universe(new Player[0], 8);
        Cell[][] cells = CellCreator.createCells(w, h, universe);

        double rectWidth = (double) WIDTH / w;
        double rectHeight = (double) HEIGHT / h;
        render(w, h, cells, gridPane, rectWidth, rectHeight, WIDTH, HEIGHT);

        Button startButton = new Button("▶");
        startButton.setPrefSize(100, 40);
        startButton.getStyleClass().add("button");

        // Button container
        HBox buttonBox = new HBox(10); // Spacing between buttons
        buttonBox.setAlignment(Pos.CENTER);

        // Create smaller buttons for formations
        String[] labels = {"RPentomino", "Beehive", "Glider", "Blinker", "Pulsar", "Pentadecathlon"};
        for (String label : labels) {
            Button btn = new Button(label);
            btn.getStyleClass().add("buttonFormation");
            btn.setPrefSize(90, 30); // Smaller size
            btn.setOnAction(e -> activateFormation(label, centerX, cells));
            buttonBox.getChildren().add(btn);
        }

        VBox.setMargin(startButton, new Insets(20, 0, 5, 0)); // Margin for start button
        startButton.setOnAction(e -> {
            mainApp.mainLoop(mainApp.getPrimaryStage(), cells);
        });

        vbox.getChildren().addAll(gridPane, startButton, buttonBox);
        scene = new Scene(vbox, PAGE_WIDTH, PAGE_HEIGHT + 40); // Adjust scene size if necessary
        scene.getStylesheets().add("style.css");
        return cells;
    }

    private void activateFormation(String type, int centerX, Cell[][] cells) {
        switch (type) {
            case "RPentomino":
                mainApp.mainLoop(mainApp.getPrimaryStage(), StartingFormation.RPentomino(centerX, centerX, cells));
                break;
            case "Beehive":
                mainApp.mainLoop(mainApp.getPrimaryStage(), StartingFormation.Beehive(centerX, centerX, cells));
                break;
            case "Glider":
                mainApp.mainLoop(mainApp.getPrimaryStage(), StartingFormation.Glider(centerX, centerX, cells));
                break;
            case "Blinker":
                mainApp.mainLoop(mainApp.getPrimaryStage(), StartingFormation.Blinker(centerX, centerX, cells));
                break;
            case "Pulsar":
                mainApp.mainLoop(mainApp.getPrimaryStage(), StartingFormation.Pulsar(centerX, centerX, cells));
                break;
            case "Pentadecathlon":
                mainApp.mainLoop(mainApp.getPrimaryStage(), StartingFormation.Pentadecathlon(centerX, centerX, cells));
                break;
        }
    }

    public Scene getScene() {
        return scene;
    }

    public void render(int w, int h, Cell[][] cells, Pane gridPane, double rectWidth, double rectHeight, int WIDTH, int HEIGHT){
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                Cell cell = cells[row][col];
                Rectangle r = cell.getRectangle(WIDTH, HEIGHT, w, h);
                DropShadow shadow = new DropShadow();
                shadow.setColor(Color.GRAY);
                shadow.setRadius(30);
                shadow.setOffsetX(3);
                shadow.setOffsetY(3);
                r.setX(col * rectWidth + 10);
                r.setY(row * rectHeight + 10);
                r.setWidth(rectWidth);
                r.setHeight(rectHeight);
                r.setFill(cell.isActive() ? Color.BLACK : Color.WHITE);
                r.setStroke(Color.GRAY);
                r.setOnMouseEntered(e -> {
                    r.setFill(Color.DARKGRAY);
                    r.setStroke(Color.BLACK); // Add a stroke color on hover
                    r.setStrokeWidth(2); // Increase stroke width on hover
                    r.setEffect(shadow);
                });

                r.setOnMouseExited(e -> {
                    r.setFill(cell.isActive() ? Color.BLACK : Color.WHITE);
                    r.setStroke(Color.GRAY); // Revert to original stroke color
                    r.setEffect(null); // Remove shadow when not hovering
                    r.setStrokeWidth(1); // Revert to original stroke width
                });
                r.setOnMouseClicked(e -> {
                    cell.click();
                    r.setFill(cell.isActive() ? Color.BLACK : Color.WHITE);
                });
                gridPane.getChildren().add(r);
            }
        }
    }

}