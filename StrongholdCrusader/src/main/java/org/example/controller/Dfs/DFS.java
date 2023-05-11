import java.io.StringReader;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class BFS {
    private int[][] map ;
    private int x ;
    private boolean[][] validation;
    public BFS(int[][] map, int x) {
        this.map = map;
        this.x = x;
        boolean[][] valid = new boolean[x][x];
        this.validation = valid;
    }

    public String bfs(int i , int j , int ii , int jj , String res){
        validation[i][j] = true;
        if (i == ii && j == jj){
            return res;
        }
        String newRes;
        if (i + 1 < x ){
            if (isSafe(i+1 , j)){
                res += "D";
                validation[i+1][j] = true;
                newRes = bfs(i+1 , j , ii , jj , res);
                if (!newRes.equals("fail")){
                    return newRes;
                }
                else {
                    newRes = new String();
                    res = res.substring(0 , res.length()-1);
                    validation[i+1][j] = false;
                }
            }
        }
        if (j - 1 >= 0 ){
            if (isSafe(i , j-1)){
                res += "L";
                validation[i][j-1] = true;
                newRes = bfs(i , j-1 , ii , jj , res);
                if (!newRes.equals("fail")){
                    return newRes;
                }
                else {
                    newRes = new String();
                    res = res.substring(0 , res.length()-1);
                    validation[i][j-1] = false;
                }
            }
        }
        if (i - 1 >= 0 ){
            if (isSafe(i-1 , j)){
                res += "U";
                validation[i-1][j] = true;
                newRes = bfs(i-1 , j , ii , jj , res);
                if (!newRes.equals("fail")){
                    return newRes;
                }
                else {
                    newRes = new String();
                    res = res.substring(0 , res.length()-1);
                    validation[i-1][j] = false;
                }
            }
        }
        if (j + 1 < x){
            if (isSafe(i , j+1)){
                res += "R";
                validation[i][j+1] = true;
                newRes = bfs(i , j+1 , ii , jj , res);
                if (!newRes.equals("fail")){
                    return newRes;
                }
                else {
                    newRes = new String();
                    res = res.substring(0 , res.length()-1);
                    validation[i][j+1] = false;
                }
            }
        }
        return "fail";
    }
    public Boolean isSafe(int i , int j){
        if (map[i][j] == -1 || validation[i][j] == true){
            return false;
        }
        return true;
    }
    public Boolean hasWay(int i , int j , int ii , int jj ){
        if (Math.abs(i - ii ) + Math.abs(j = jj) <= 1){
            return true;
        }
        return false;
    }
}

