package org.example.model.buildings;

public class StoneGatehouse extends Building {
    private final int maximumResidents;
    private int currentResidents;

    public StoneGatehouse(int maximumResidents) {
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
