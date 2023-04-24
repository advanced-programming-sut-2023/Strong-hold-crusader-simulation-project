package org.example.model;

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
}
