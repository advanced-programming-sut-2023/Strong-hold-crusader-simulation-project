package org.example.model;

public class Map {
    private int size;
    private MapCell[][] cells;
    public Map(int size) {
        this.cells = new MapCell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new MapCell();
            }
        }
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public MapCell[][] getCells() {
        return cells;
    }
}
