package org.example.model.buildings;

public enum FarmTypes implements BuildingUsage {
    APPLE_ORCHARD{
        @Override
        public void usage() {

        }
    },
    DAIRY_FARM{
        @Override
        public void usage() {

        }
    },
    WHEAT_FARM{
        @Override
        public void usage() {

        }
    },
    HOP_FARM{
        @Override
        public void usage() {

        }
    },
    HUNTERS_POST{
        @Override
        public void usage() {

        }
    };

    FarmTypes() {
    }
}
