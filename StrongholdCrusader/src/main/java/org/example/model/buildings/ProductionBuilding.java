package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.Resources;
import org.example.model.buildings.buildingTypes.BuildingType;
import org.example.model.buildings.buildingTypes.ProductionBuildingType;

public class ProductionBuilding extends Building {
    private final ProductionBuildingType exactType;
    public ProductionBuilding(BuildingType type, Government government, ProductionBuildingType exactType) {
        super(type, government);
        this.exactType = exactType;
    }

    @Override
    public void work() {
        Resources neededResource = exactType.getNeededResource();
        if (neededResource != null) {
            if (government.getResourceCount().get(neededResource) <= 0)
                return;
            else government.getResourceCount().replace(neededResource,
                    government.getResourceCount().get(neededResource) - 1);
        }
        Resources producedResource = exactType.getProducedResource();
        government.getResourceCount().replace(producedResource,
                government.getResourceCount().get(producedResource) + 1);
    }
}
