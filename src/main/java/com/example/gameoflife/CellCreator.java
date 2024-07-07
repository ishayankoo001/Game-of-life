package com.example.gameoflife;

import com.SGS.dependency.AcquaintanceArray;
import com.SGS.dependency.AcquaintanceElement;
import com.SGS.dependency.IResponseFunction;
import com.SGS.dependency.Universe;

public class CellCreator {
    GolResponseFunction responseFunction = new GolResponseFunction();
    public static Cell[][] createCells(int width, int height, Universe universe) {
        Cell[][] cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell cell  = new Cell(String.format("%d,%d", x, y), universe, x, y);
                IResponseFunction responseFunction = new GolResponseFunction();
                cell.setResponseFunction(responseFunction);
                cells[x][y] = cell;

            }
        }
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                Cell cell = cells[x][y];
                if (x>0 && y>0 && x<width-1 && y<height-1) {
                    AcquaintanceElement left = new AcquaintanceElement(cells[x-1][y], 4);
                    AcquaintanceElement topLeft = new AcquaintanceElement(cells[x-1][y-1], 5);
                    AcquaintanceElement top = new AcquaintanceElement(cells[x][y-1], 6);
                    AcquaintanceElement topRight = new AcquaintanceElement(cells[x+1][y-1], 7);
                    AcquaintanceElement right = new AcquaintanceElement(cells[x+1][y], 0);
                    AcquaintanceElement bottomRight = new AcquaintanceElement(cells[x+1][y+1], 1);
                    AcquaintanceElement bottom = new AcquaintanceElement(cells[x][y+1], 2);
                    AcquaintanceElement bottomLeft = new AcquaintanceElement(cells[x-1][y+1], 3);
                    AcquaintanceElement[] acquaintances = {left, topLeft, top, topRight, right, bottomRight, bottom, bottomLeft};
                    AcquaintanceArray acquaintanceArray = new AcquaintanceArray();
                    acquaintanceArray.setAcquaintanceElements(acquaintances);
                    cell.setAcquaintances(acquaintanceArray);
                }
                else if (x==0 && y==0) {
                    AcquaintanceElement right = new AcquaintanceElement(cells[x+1][y], 0);
                    AcquaintanceElement bottomRight = new AcquaintanceElement(cells[x+1][y+1], 1);
                    AcquaintanceElement bottom = new AcquaintanceElement(cells[x][y+1], 2);
                    AcquaintanceElement[] acquaintances = {null, null, null, null, right, bottomRight, bottom, null};
                    AcquaintanceArray acquaintanceArray = new AcquaintanceArray();
                    acquaintanceArray.setAcquaintanceElements(acquaintances);
                    cell.setAcquaintances(acquaintanceArray);
                }
                else if (x==width-1 && y==0) {
                    AcquaintanceElement left = new AcquaintanceElement(cells[x-1][y], 4);
                    AcquaintanceElement bottomLeft = new AcquaintanceElement(cells[x-1][y+1], 3);
                    AcquaintanceElement bottom = new AcquaintanceElement(cells[x][y+1], 2);
                    AcquaintanceElement[] acquaintances = {left, null, null, null, null, null, bottom, bottomLeft};
                    AcquaintanceArray acquaintanceArray = new AcquaintanceArray();
                    acquaintanceArray.setAcquaintanceElements(acquaintances);
                    cell.setAcquaintances(acquaintanceArray);
                }
                else if (x==width-1 && y==height-1) {
                    AcquaintanceElement left = new AcquaintanceElement(cells[x-1][y], 4);
                    AcquaintanceElement topLeft = new AcquaintanceElement(cells[x-1][y-1], 5);
                    AcquaintanceElement top = new AcquaintanceElement(cells[x][y-1], 6);
                    AcquaintanceElement[] acquaintances = {left, topLeft, top, null, null, null, null, null};
                    AcquaintanceArray acquaintanceArray = new AcquaintanceArray();
                    acquaintanceArray.setAcquaintanceElements(acquaintances);
                    cell.setAcquaintances(acquaintanceArray);
                }
                else if (x==0 && y==height-1) {
                    AcquaintanceElement top = new AcquaintanceElement(cells[x][y-1], 6);
                    AcquaintanceElement topRight = new AcquaintanceElement(cells[x+1][y-1], 7);
                    AcquaintanceElement right = new AcquaintanceElement(cells[x+1][y], 0);
                    AcquaintanceElement[] acquaintances = {null, null, top, topRight, right, null, null, null};
                    AcquaintanceArray acquaintanceArray = new AcquaintanceArray();
                    acquaintanceArray.setAcquaintanceElements(acquaintances);
                    cell.setAcquaintances(acquaintanceArray);
                }
                else if (x==0) {
                    AcquaintanceElement top = new AcquaintanceElement(cells[x][y-1], 6);
                    AcquaintanceElement topRight = new AcquaintanceElement(cells[x+1][y-1], 7);
                    AcquaintanceElement right = new AcquaintanceElement(cells[x+1][y], 0);
                    AcquaintanceElement bottomRight = new AcquaintanceElement(cells[x+1][y+1], 1);
                    AcquaintanceElement bottom = new AcquaintanceElement(cells[x][y+1], 2);
                    AcquaintanceElement[] acquaintances = {null, null, top, topRight, right, bottomRight, bottom, null};
                    AcquaintanceArray acquaintanceArray = new AcquaintanceArray();
                    acquaintanceArray.setAcquaintanceElements(acquaintances);
                    cell.setAcquaintances(acquaintanceArray);
            }
                else if (y==0) {
                    AcquaintanceElement left = new AcquaintanceElement(cells[x-1][y], 4);
                    AcquaintanceElement bottomLeft = new AcquaintanceElement(cells[x-1][y+1], 3);
                    AcquaintanceElement bottom = new AcquaintanceElement(cells[x][y+1], 2);
                    AcquaintanceElement bottomRight = new AcquaintanceElement(cells[x+1][y+1], 1);
                    AcquaintanceElement right = new AcquaintanceElement(cells[x+1][y], 0);
                    AcquaintanceElement[] acquaintances = {left, null, null, null, right, bottomRight, bottom, bottomLeft};
                    AcquaintanceArray acquaintanceArray = new AcquaintanceArray();
                    acquaintanceArray.setAcquaintanceElements(acquaintances);
                    cell.setAcquaintances(acquaintanceArray);
                }
                else if (x==width-1) {
                    AcquaintanceElement left = new AcquaintanceElement(cells[x-1][y], 4);
                    AcquaintanceElement bottomLeft = new AcquaintanceElement(cells[x-1][y+1], 3);
                    AcquaintanceElement bottom = new AcquaintanceElement(cells[x][y+1], 2);
                    AcquaintanceElement[] acquaintances = {left, null, null, null, null, null, bottom, bottomLeft};
                    AcquaintanceArray acquaintanceArray = new AcquaintanceArray();
                    acquaintanceArray.setAcquaintanceElements(acquaintances);
                    cell.setAcquaintances(acquaintanceArray);
                }
                else if (y==height-1) {
                    AcquaintanceElement left = new AcquaintanceElement(cells[x-1][y], 4);
                    AcquaintanceElement topLeft = new AcquaintanceElement(cells[x-1][y-1], 5);
                    AcquaintanceElement top = new AcquaintanceElement(cells[x][y-1], 6);
                    AcquaintanceElement topRight = new AcquaintanceElement(cells[x+1][y-1], 7);
                    AcquaintanceElement right = new AcquaintanceElement(cells[x+1][y], 0);
                    AcquaintanceElement[] acquaintances = {left, topLeft, top, topRight, right, null, null, null};
                    AcquaintanceArray acquaintanceArray = new AcquaintanceArray();
                    acquaintanceArray.setAcquaintanceElements(acquaintances);
                    cell.setAcquaintances(acquaintanceArray);
        }
            }
        }
        return cells;
    }
}
