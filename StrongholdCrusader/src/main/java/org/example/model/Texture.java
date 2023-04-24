package org.example.model;

public enum Texture {
    BASE_GROUND("Base Ground","\033[43m"),
    PEBBLE("Pebble","\033[40m"),
    ROCK("Rock","\033[45m"),
    STONE("Stone","\033[47m"),
    IRON("Iron","\033[41m"),
    GRASS("Grass","\033[42m"),
    OASIS("Oasis","\033[46m"),
    DENSE_OASIS("Dense Oasis","\033[44m");
    private final String color;
    private final String textureName;
    Texture(String textureName ,String color) {
        this.textureName = textureName;
        this.color = color;
    }
    public String getColor() {
        return color;
    }

    public String getTextureName() {
        return textureName;
    }
}
