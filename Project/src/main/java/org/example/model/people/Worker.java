package org.example.model.people;

import org.example.model.buildings.Building;

public class Worker extends People{
    public Worker(String username, int health){
        super(username, health);

    }
    private Building building;
}
