package com.example.gameoflife;

import com.SGS.dependency.Player;
import com.SGS.dependency.Universe;
import javafx.scene.shape.Rectangle;

public class Cell extends Player {



    public int x;


    public int y;


    public Cell(String name, Universe universe, int x, int y) {
        super(name, universe);
        this.setIsActive(false);
        this.x = x;
        this.y = y;
    }

    public Rectangle getRectangle(int width, int height, int w, int h) {
        int y1 = height / h;
        int x1 = width / w;

        Rectangle r = new Rectangle(x * x1, y * y1, x1*0.9, y1*0.9);
        if(isActive()){
            r.setFill(javafx.scene.paint.Color.BLACK);
        } else {
            r.setFill(javafx.scene.paint.Color.WHITE);
            //add border
            r.setStroke(javafx.scene.paint.Color.BLACK);
        }
        return r;

    }
    public void click() {
        setIsActive(!isActive());
    }

    @Override
    public boolean checkDeath() {
        return super.checkDeath();
    }
}
