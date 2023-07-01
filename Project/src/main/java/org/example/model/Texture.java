package org.example.model;

import java.util.Objects;

public enum Texture {
    BASE_GROUND("Base Ground","/Images/textures/baseGround.jpg"),
    ROCK("Rock","/Images/textures/rock.jpg"),
    STONE("Stone","/Images/textures/stone.jpg"),
    IRON("Iron","/Images/textures/iron.jpg"),
    GRASS("Grass","/Images/textures/grass.jpg"),
    OASIS("Oasis","/Images/textures/oasis.jpg"),
    OIL("Oil", "/Images/textures/oil.jpg"),
    PLAIN("Plain","/Images/textures/plain.jpg"),
    SEA("Sea","/Images/textures/sea.jpg");
    private final String imagePath;
    private final String textureName;
    Texture(String textureName ,String imagePath) {
        this.textureName = textureName;
        this.imagePath = imagePath;
    }
    public String getImagePath() {
        return imagePath;
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
