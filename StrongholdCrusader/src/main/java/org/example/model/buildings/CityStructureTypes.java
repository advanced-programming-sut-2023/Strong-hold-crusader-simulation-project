package org.example.model.buildings;

public enum CityStructureTypes implements BuildingUsage {
    HOVEL{
        @Override
        public void usage() {

        }
    },
    CHURCH{
        @Override
        public void usage() {

        }
    },
    CATHEDRAL{
        @Override
        public void usage() {

        }
    };

    CityStructureTypes() {
    }
}
