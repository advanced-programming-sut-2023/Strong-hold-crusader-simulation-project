package org.example.model;

public class Map {
    private int size;
    private MapCell[][] cells;
    public Map(int size) {
        this.cells = new MapCell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new MapCell(chooseTexture(i, j));
            }
        }
        this.size = size;
    }

    private Texture chooseTexture(int i, int j) {
        if (((i == 10 || i == 11) && j < 12) || (i == 54 || i == 55) && j > 94)
            return Texture.IRON;
        if ((j > 5 && j < 10 && i < 5) || (j > 90 && j < 95 && i > 95))
            return Texture.STONE;
        if (j > 95 && i < 4)
            return Texture.OIL;
        if (i == 68 && j == 33)
            return Texture.OASIS;
        if (i < 20 && j > 45 && j < 65)
            return Texture.GRASS;
        if (i > 97 && j > 8 && j < 12)
            return Texture.ROCK;
        if (j > 80 && i > 45 && i < 55)
            return Texture.SEA;
        if (i > 77 && i < 83 && j > 34 && j < 39)
            return Texture.PLAIN;
        return Texture.BASE_GROUND;
    }

    public int getSize() {
        return size;
    }

    public MapCell[][] getCells() {
        return cells;
    }
}
