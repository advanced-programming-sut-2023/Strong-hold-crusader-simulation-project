package org.example.model;

public class DigDetails {
    private int turnsLeft;
    private int x;
    private int y;
    private Move move;
    public DigDetails(Move move , int x, int y) {
        this.move = move;
        this.turnsLeft = 3;
        this.x = x;
        this.y = y;
    }
    public Boolean update(){
        if (move == null || move.getTurnsLeft() == 0){
            turnsLeft -= 1;
        }
        if (turnsLeft == 0){
            return true;
        }
        return false;
    }
}
