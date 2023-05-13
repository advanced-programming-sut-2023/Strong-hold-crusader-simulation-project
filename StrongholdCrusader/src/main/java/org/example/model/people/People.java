package org.example.model.people;

import java.io.Serializable;

public class People implements Serializable {
    People(String ownerUsername , int health){
        this.ownerUsername = ownerUsername;
        this.health = health;
    }
    protected String ownerUsername;
    protected int posX;
    protected int posY;
    protected int posAimX;
    protected int posAimY;
    private int health;
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
