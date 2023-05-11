package org.example.controller.Dfs;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int[][] map = new int[x][x];
        for (int i = 0 ; i < x ; i++){
            int[] temp = new int[x];
            for (int j = 0 ; j < x ; j++) {
                temp[j] = scanner.nextInt();
            }
            map[i] = temp;
        }
        for (int i = 0 ; i < x ; i++){
            for (int j = 0 ; j < x ; j++){
                System.out.printf("%2d " , map[i][j]);
            }
            System.out.println();
        }
        DFS dfs = new DFS(map , x);
        int i = scanner.nextInt() ; int j = scanner.nextInt() ; int ii = scanner.nextInt() ; int jj = scanner.nextInt();
        System.out.println(dfs.dfs(i , j , ii , jj , new String("res : ")));
    }
}
