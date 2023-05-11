package org.example.controller;

public enum GameMenuOutputs {
    isDigging("unit is digging the cell right now."),
    movingToDig("selected unit is moving to dig the cell."),
    notTunneler("sorry selected unit can't dig , select a tunneler unit for that."),
    invalidRange("sorry! the selected unit's can't shoot that far ; it maybe damage the other units of us."),
    noWeaponUnitWantToShoot("the selected unit doesn't have weapon to shoot"),
    isAttacking("selected unit is attacking to chosen enemy."),
    movingToEnemy("selected unit is moving to attack the enemy"),
    farEnemy("your chosen enemy is far from you , you must get closer to the enemy so you can attack him."),
    success("success:))))"),
    invalidState("there is no state named "),
    unitStoppedPatrol("unit successfully stopped patrol"),
    invalidPatrolCoordinate("you should first go to start position for patrol!!"),
    invalidPatrol("ouch !! you can just patrol on a line and the line must be crossable ."),
    blockedCell("you can't go to this cell , it's blocked by something"),
    invalidCommand("invalid command!"),
    invalidCoordinate("invalid coordinates!"),
    youHaveNoUnit("you have no unit in the selected cell dude!"),
    longDistance("oh no : the unit you selected can't go that far!"),
    unitMoving("unit is moving now ..."),
    SelectedNull("pleas select a unit first :(");
    String output;
    GameMenuOutputs(String output){
        this.output = output;
    }
    public String getOutput(){
        return output;
    }
}
