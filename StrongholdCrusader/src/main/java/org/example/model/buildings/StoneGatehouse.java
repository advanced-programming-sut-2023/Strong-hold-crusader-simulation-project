package org.example.model.buildings;

import org.example.model.Government;

public class StoneGatehouse extends Building {
    private final int maximumResidents;
    private int currentResidents;
    protected StoneGatehouse(Government government, int HP, int maximumResidents) {
        super(government, HP);
        this.maximumResidents = maximumResidents;
    }
    public int getMaximumResidents() {
        return maximumResidents;
    }
    public int getCurrentResidents() {
        return currentResidents;
    }

    public void setCurrentResidents(int currentResidents) {
        this.currentResidents = currentResidents;
    }
}
