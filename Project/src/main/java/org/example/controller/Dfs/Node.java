package org.example.controller.Dfs;

public class Node {
    public int i ;
    public int j ;
    String dirction;
    public int weight;

    public Node(int i, int j, String direction , int weight) {
        this.i = i;
        this.j = j;
        this.dirction = direction;
        this.weight = weight;
    }
    public static int WeightGenerator(int i , int j , int ii , int jj){
        return (int)(Math.sqrt(((i - ii)*(i - ii)) + ((j - jj)*(j - jj))));
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDirction() {
        return dirction;
    }

    public void setDirction(String dirction) {
        this.dirction = dirction;
    }

    public static int Compare(Node node1 , Node node2){
        if(node2.getWeight() > node1.getWeight()){
            return 1;
        }
        else if (node2.getWeight() == node1.getWeight()){
            return 0;
        }
       else {
           return -1;
        }
    }
}
