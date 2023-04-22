package model;

public enum Texture {
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
}
