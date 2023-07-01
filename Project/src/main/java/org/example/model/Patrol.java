package org.example.model;

import java.io.Serializable;

public class Patrol implements Serializable {
    public Patrol(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.dire = 1;
    }
    private int dire;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    public Boolean isArrived(int x , int y){
        if (dire == 1 && x == x2 && y == y2 ){
            dire *= -1;
            return true;
        }
        else if (dire == -1 && x == x1 && y == y2){
            dire *= -1;
            return true;
        }
        return false;
    }

    public Move nextMove(int speed , int x , int y) {
        int z = 0;
        if (x1 == x2){
            z = dire * (Math.abs(y2 - y1)/(y2-y1));
            Move move = new Move(10 - speed , x , y+z);
            return move;
        }
        else if (y1 == y2){
            z = dire * (Math.abs(x2-x1)/(x2-x1));
            Move move = new Move(10 - speed , x+z , y);
            return move;
        }
        return null;
    }
}
