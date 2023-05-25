package org.example.model.buildings;

import javafx.scene.control.Cell;
import org.example.controller.Controller;
import org.example.model.Government;
import org.example.model.MapCell;
import org.example.model.User;
import org.example.model.buildings.buildingTypes.BuildingType;
import org.example.model.buildings.buildingTypes.TowerType;
import org.example.model.people.Unit;

public class Tower extends Building {

    private final TowerType exactType;
    private final int range;
    private final int damage;

    public Tower(BuildingType type, Government government, TowerType exactType) {
        super(type, government);
        this.exactType = exactType;
        range = exactType.getRange();
        damage = exactType.getDamage();
    }

    @Override
    public void work() {
        for (int i = x - range; i < x + range; i++) {
            for (int j = y - range; j < y + range; j++) {
                MapCell cell = Controller.getCurrentGame().getMap().getCells()[i - 1][j - 1];
                for (Unit unit: cell.getUnits()) {
                    if (!unit.getOwnerUsername().equals(this.getGovernment().getOwner().getUsername()))
                        unit.setHealth(unit.getHealth() - damage);
                    if (unit.getHealth() <= 0)
                        cell.getUnits().remove(unit);
                }
            }
        }
    }
}
