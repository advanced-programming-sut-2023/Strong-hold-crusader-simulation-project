package model;

public enum Texture {
<<<<<<< HEAD
    BASE_GROUND,
    PEBBLE,
    ROCK,
    STONE,
    IRON,
    GRASS,
    OASIS,
    HIGH_DENSITY_OASIS;
=======
    BASE_GROUND(""),
    PEBBLE(""),
    ROCK(""),
    STONE(""),
    IRON(""),
    GRASS(""),
    OASIS(""),
    HIGH_DENSITY_OASIS("");
    private final String color;
    Texture(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }
>>>>>>> parent of 790ce96 (map menu complete)
}
