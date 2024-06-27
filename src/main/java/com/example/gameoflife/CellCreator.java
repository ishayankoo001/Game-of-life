package com.example.gameoflife;

import com.SGS.dependency.Universe;

public class CellCreator {
    public static Cell[][] createCells(int width, int height, Universe universe) {
        Cell[][] cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(String.format("%d,%d", x, y), universe, x, y);
            }
        }
        return cells;
    }
}
