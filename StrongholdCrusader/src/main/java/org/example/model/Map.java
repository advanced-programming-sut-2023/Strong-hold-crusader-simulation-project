package org.example.model;

import org.example.model.people.Unit;

import java.util.ArrayList;

public class Map {
    private int size;
    private MapCell[][] cells;
    public Map(int size) {
        this.cells = new MapCell[size][size];
        this.size = size;
    }
    public int getSize() {
        return size;
    }

    public MapCell[][] getCells() {
        return cells;
    }
    public ArrayList<Unit> getUnits(int x , int y){
        return cells[x][y].getUnits();
    }
}
