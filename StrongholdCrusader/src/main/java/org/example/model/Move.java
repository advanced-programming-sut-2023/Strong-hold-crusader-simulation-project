package org.example.model;

public class Move {
    private int turnsLeft;
    private int aimX;
    private int aimY;
    public Move(int turnsLeft , int aimX , int aimY){
        this.turnsLeft = turnsLeft;
        this.aimX = aimX;
        this.aimY = aimY;
    }

    public int getAimX() {
        return aimX;
    }

    public void setAimX(int aimX) {
        this.aimX = aimX;
    }

    public int getTurnsLeft() {
        return turnsLeft;
    }

    public void setTurnsLeft(int turnsLeft) {
        this.turnsLeft = turnsLeft;
    }

    public int getAimY() {
        return aimY;
    }

    public void setAimY(int aimY) {
        this.aimY = aimY;
    }
    public void decreaseTurnsLeft(){
        turnsLeft -= 1;
    }
}
