package com.example.gameoflife;

public class StartingFormation {
    public static Cell[][] RPentomino(int centerX, int centerY, Cell[][] cells) {
        cells[centerY][centerX].setIsActive(true);
        cells[centerY][centerX + 1].setIsActive(true);
        cells[centerY + 1][centerX].setIsActive(true);
        cells[centerY + 1][centerX - 1].setIsActive(true);
        cells[centerY + 2][centerX].setIsActive(true);
        return cells;
    }
    public static Cell[][] Beehive(int centerX, int centerY, Cell[][] cells) {
        cells[centerY][centerX].setIsActive(true);
        cells[centerY][centerX + 1].setIsActive(true);
        cells[centerY + 1][centerX - 1].setIsActive(true);
        cells[centerY + 1][centerX + 2].setIsActive(true);
        cells[centerY + 2][centerX].setIsActive(true);
        cells[centerY + 2][centerX + 1].setIsActive(true);
        return cells;
    }
    public static Cell[][] Blinker(int centerX, int centerY, Cell[][] cells) {
        cells[centerY][centerX].setIsActive(true);
        cells[centerY][centerX + 1].setIsActive(true);
        cells[centerY][centerX + 2].setIsActive(true);
        return cells;
    }
    public static Cell[][] Glider(int centerX, int centerY, Cell[][] cells) {
        cells[centerY][centerX].setIsActive(true);
        cells[centerY][centerX + 1].setIsActive(true);
        cells[centerY][centerX + 2].setIsActive(true);
        cells[centerY + 1][centerX].setIsActive(true);
        cells[centerY + 2][centerX + 1].setIsActive(true);
        return cells;
    }
    public static Cell[][] Pulsar(int centerX, int centerY, Cell[][] cells) {
        // Clear existing active cells if needed
        for (int y = centerY - 6; y <= centerY + 6; y++) {
            for (int x = centerX - 6; x <= centerX + 6; x++) {
                cells[y][x].setIsActive(false);
            }
        }

        int[] xOffsets = { -4, -3, -2, 2, 3, 4 };
        int[] yOffsets = { 0, -1, 1 };

        // Activate the horizontal arms
        for (int dx : xOffsets) {
            for (int dy : yOffsets) {
                cells[centerY + dy][centerX + dx].setIsActive(true);
                cells[centerY - dy][centerX - dx].setIsActive(true);
            }
        }

        xOffsets = new int[]{ -6, -1, 0, 1, 6 };
        yOffsets = new int[]{ -4, -3, -2, 2, 3, 4 };

        // Activate the vertical arms
        for (int dy : yOffsets) {
            for (int dx : xOffsets) {
                cells[centerY + dy][centerX + dx].setIsActive(true);
                cells[centerY - dy][centerX - dx].setIsActive(true);
            }
        }

        return cells;
    }

    public static Cell[][] Pentadecathlon(int centerX, int centerY, Cell[][] cells) {
        // Clear existing active cells if needed (optional based on your setup)
        for (int y = centerY - 1; y <= centerY + 1; y++) {
            for (int x = centerX - 6; x <= centerX + 6; x++) {
                cells[y][x].setIsActive(false);
            }
        }

        // Activate the central line of cells
        for (int i = -5; i <= 4; i++) { // 10 central cells
            if (i == -3 || i == 2) continue; // Skip to create bumps
            cells[centerY][centerX + i].setIsActive(true);
        }

        // Add bumps
        cells[centerY - 1][centerX - 3].setIsActive(true);
        cells[centerY + 1][centerX - 3].setIsActive(true);
        cells[centerY - 1][centerX + 2].setIsActive(true);
        cells[centerY + 1][centerX + 2].setIsActive(true);

        return cells;
    }
}
