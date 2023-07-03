package org.example.controller;

import org.example.model.Resources;
import org.example.model.buildings.WorkerProduceBuilding;
import org.example.model.people.MilitaryType;
import org.example.model.people.Unit;

import java.util.regex.Matcher;

public class BuildingMenuController extends Controller{
    public static String repair() {
        if (!selectedBuilding.getType().isRepairable())
            return "You can only repair Castle Buildings!";
        if (selectedBuilding.getHP() == selectedBuilding.getType().getHP())
            return "This building doesn't need repairing";
        if (selectedBuilding.getGovernment().getResourceCount().get(Resources.STONE) < 2)
            return "You don't have enough stone for repairing";
        selectedBuilding.setHP(selectedBuilding.getType().getHP());
        return "Building successfully repaired";
    }

    public static String createUnit(Matcher matcher) {
        if (!(selectedBuilding instanceof WorkerProduceBuilding))
            return "You cannot create units in this building!";
        int count = Integer.parseInt(matcher.group("count"));
        MilitaryType type = MilitaryType.getUnitByName(matcher.group("type"));
        if (type == null)
            return "enter a valid type";
        for (int i = 0; i < count; i++) {
            currentGame.getCurrentTurn().addPeople(
                    new Unit(currentGame.getCurrentTurn().getOwner().getUsername(), type));

        }
        return "Units Created successfully";
    }
}
