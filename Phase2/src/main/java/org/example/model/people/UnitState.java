package org.example.model.people;

public enum UnitState {
    Standing,
    Defencive,
    Offensive;

    public static UnitState getStateByString(String state) {
        switch (state){
            case "standing" :{
                return Standing;
            }
            case "defencive" :{
                return Defencive;
            }
            case "offensive":{
                return Offensive;
            }
        }
        return null;
    }
}
