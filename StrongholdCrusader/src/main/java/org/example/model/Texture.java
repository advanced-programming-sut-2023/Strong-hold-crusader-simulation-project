package org.example.model;

import java.util.Objects;

public enum Texture {
    BASE_GROUND("Base Ground","\033[43m"),
    PEBBLE("Pebble","\033[40m"),
    STONE("Stone","\033[47m"),
    IRON("Iron","\033[41m"),
    GRASS("Grass","\033[42m"),
    OASIS("Oasis","\033[46m"),
    DENSE_OASIS("Dense Oasis","\033[44m"),
    OIL("oil",""),
    PLAIN("plain",""),
    BIG_POND("big pond",""),
    SMALL_POND("small pond",""),
    RIVER("river",""),
    SHALLOW_WATER("shallow water",""),
    BEACH("beach",""),
    SEA("sea","");

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
