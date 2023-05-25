package org.example.model.buildings;

import org.example.model.Government;
import org.example.model.Resources;
import org.example.model.buildings.buildingTypes.BuildingType;
import org.example.model.buildings.buildingTypes.ProductionBuildingType;
import org.example.model.buildings.buildingTypes.StorageType;

public class ProductionBuilding extends Building {
    private final ProductionBuildingType exactType;
    public ProductionBuilding(BuildingType type, Government government, ProductionBuildingType exactType) {
        super(type, government);
        this.exactType = exactType;
    }

    @Override
    public void work() {
        Resources neededResource = exactType.getNeededResource();
        Resources producedResource = exactType.getProducedResource();
        if (producedResource == null) {
            if (neededResource != null) {
                if (government.getResourceCount().get(neededResource) <= 0)
                    return;
                else government.getResourceCount().replace(neededResource,
                        government.getResourceCount().get(neededResource) - 1);
            }
            return;
        }
        StorageType storeType = producedResource.getStorageType();
        boolean available = false;
        StorageBuilding storage = null;
        for (Building building : government.getBuildings()) {
            if (building instanceof StorageBuilding && ((StorageBuilding) building).getExactType().equals(storeType)
            && ((StorageBuilding) building).getLeftCapacity() > 0) {
                storage = (StorageBuilding) building;
                available = true;
                break;
            }
        }
        if (!available)
            return;
        if (neededResource != null) {
            if (government.getResourceCount().get(neededResource) <= 0)
                return;
            else government.getResourceCount().replace(neededResource,
                    government.getResourceCount().get(neededResource) - 1);
        }
        government.getResourceCount().replace(producedResource,
                government.getResourceCount().get(producedResource) + 1);
        storage.setLeftCapacity(storage.getLeftCapacity() - 1);
    }
}
