import java.util.ArrayList;

public class DFS {
    private int[][] map ;
    private int x ;
    private boolean[][] validation;
    public DFS(int[][] map, int x) {
        this.map = map;
        this.x = x;
        boolean[][] valid = new boolean[x][x];
        this.validation = valid;
    }

    public String dfs(int i , int j , int ii , int jj , String res){
        ArrayList<Node> nodes = new ArrayList<>();
        validation[i][j] = true;
        if (i == ii && j == jj){
            return res;
        }
        if (i + 1 < x ){
            if (isBlocked(i+1 , j)){
                Node temp = new Node(i+1 , j ,"D", Node.WeightGenerator(i+1 , j , ii , jj));
                nodes.add(temp);
            }
        }
        if (j - 1 >= 0 ){
            if (isBlocked(i , j-1)){
                Node temp2 = new Node(i , j-1 ,"L" , Node.WeightGenerator(i , j-1 , ii , jj));
                nodes.add(temp2);
            }
        }
        if (i - 1 >= 0 ){
            if (isBlocked(i-1 , j)){
                Node temp3 = new Node(i-1 , j ,"U", Node.WeightGenerator(i-1 , j , ii ,jj));
                nodes.add(temp3);
            }
        }
        if (j + 1 < x){
            if (isBlocked(i , j+1)){
                Node temp4 = new Node(i , j+1 , "R" ,Node.WeightGenerator(i , j+1 , ii , jj));
                nodes.add(temp4);
            }
        }
    Node tempi;
    Node tempi2;
    for(int i1 = 0 ; i1 < nodes.size()  ; i1++){
        for(int j1 = 0 ; j1 < nodes.size() - i1 - 1 ; j1++){
            if(Node.Compare(nodes.get(j1) , nodes.get(j1+1)) < 0){
                tempi = nodes.get(j1);
                tempi2 = nodes.get(j1+1);
                nodes.remove(j1);
                nodes.add(j1 , tempi2);
                nodes.remove(j1+1);
                nodes.add(j1+1 , tempi);
            }
        }
    }
    String newRes;
    for (int k = 0 ; k < nodes.size() ; k++){
        res += nodes.get(k).getDirction();
        validation[nodes.get(k).getI()][nodes.get(k).getJ()] = true;
        newRes = dfs(nodes.get(k).getI() , nodes.get(k).getJ() , ii , jj , res);
        if (!newRes.equals("fail")){
            return newRes;
        }
        else {
            res = res.substring(0 , res.length()-1);
            validation[nodes.get(k).getI()][nodes.get(k).getJ()] = false;
        }
    }
    return "fail";
    }
    public Boolean isBlocked(int i , int j){
        if (map[i][j] == -1 || validation[i][j] == true){
            return false;
        }
        return true;
    }
}

