package org.example.model;

import java.util.Objects;

public enum Texture {
    BASE_GROUND("Base Ground","\033[43m"),
    PEBBLE("Pebble","\033[47m"),
    ROCK("Rock","\033[45m"),
    STONE("Stone","\033[47m"),
    IRON("Iron","\033[41m"),
    GRASS("Grass","\033[42m"),
    OASIS("Oasis","\033[46m"),
    DENSE_OASIS("Dense Oasis","\033[44m"),
    OIL("Oil", "\033[40m"),
    PLAIN("Plain","\033[42m"),
    BIG_POND("Big Pond","\033[44m"),
    SMALL_POND("Small Pond","\033[46m"),
    RIVER("River","\033[46m"),
    SHALLOW_WATER("Shallow Water","\033[44m"),
    BEACH("Beach","\033[43m"),
    SEA("Sea","\033[44m");
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
    public static Texture findTexture(String string){
        for (Texture texture: Texture.values()){
            if (Objects.equals(texture.getTextureName(), string)){
                return texture;
            }
        }
        return null;
    }
}
