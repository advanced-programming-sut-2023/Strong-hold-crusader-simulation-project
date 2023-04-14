package model;

public class Map {
    private MapCell[][] cells;

    public Map(int height, int width) {
        this.cells = new MapCell[height][width];
    }
}
