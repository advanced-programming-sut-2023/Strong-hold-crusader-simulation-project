package org.example.controller;

import org.example.model.Resources;

public class BuildingMenuController extends Controller{
    public static String repair() {
        if (!selectedBuilding.getType().isRepairable())
            return "You can only repair Castle Buildings!";
        if (selectedBuilding.getHP() == selectedBuilding.getType().getHP())
            return "This building doesn't need repairing";
        if (selectedBuilding.getGovernment().getResourceCount().get(Resources.STONE) < 2)
            return "You don't have enough stone for repairing";
        //TODO don't repair in case of near units
        selectedBuilding.setHP(selectedBuilding.getType().getHP());
        return "Building successfully repaired";
    }
}
