package com.example.gameoflife;

import com.SGS.dependency.Player;
import com.SGS.dependency.Universe;

public class Cell extends Player {
    public int x;
    public int y;


    public Cell(String name, Universe universe, int x, int y) {
        super(name, universe);
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean checkDeath() {
        return super.checkDeath();
    }
}
