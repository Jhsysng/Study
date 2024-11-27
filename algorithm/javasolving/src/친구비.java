import java.util.Scanner;

public class 친구비 {
    static int[] parent;
    static int[] friendCost;

    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y ){
        x = find(x);
        y = find(y);
        if( x!= y){
            if(friendCost[x] < friendCost[y]){
                parent[y] = x;
            }else{
                parent[x] = y;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        parent = new int[n+1];
        friendCost = new int[n+1];
        for(int i = 1; i<=n; i++){
            parent[i] = i;
            friendCost[i] = sc.nextInt();
        }

        for(int i = 0; i<m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            union(x, y);
        }

        boolean[] visited = new boolean[n+1];
        int totalCost = 0;
        for(int i = 1; i<=n; i++){
            int root = find(i);
            if(!visited[root]){
                visited[root] = true;
                totalCost += friendCost[root];
            }
        }

        if(totalCost <= k){
            System.out.println(totalCost);
        }else{
            System.out.println("Oh no");
        }

        sc.close();
    }
}
