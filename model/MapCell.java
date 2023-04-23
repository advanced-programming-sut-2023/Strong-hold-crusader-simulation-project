package model;

<<<<<<< HEAD
public class MapCell {
    private Texture texture = Texture.BASE_GROUND;

=======
import model.buildings.Building;
import model.people.Unit;

import java.util.ArrayList;

public class MapCell {
    private Texture texture = Texture.BASE_GROUND;

    private final ArrayList<Unit> units = new ArrayList<>();

    private final ArrayList<Building> buildings = new ArrayList<>();

    private char cellState;

    private final ArrayList<Recourses> recourses = new ArrayList<>();

>>>>>>> parent of 790ce96 (map menu complete)
    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
<<<<<<< HEAD
=======

    public void setCellState(char cellState) {
        this.cellState = cellState;
    }

    public char getCellState() {
        return cellState;
    }
>>>>>>> parent of 790ce96 (map menu complete)
}
