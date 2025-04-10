import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 우수마을 {
    static List<Integer>[] tree;
    static int[] residents;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        residents = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            residents[i] = Integer.parseInt(st.nextToken());
        }
        
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        
        dfs(1);
        
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }
    
    static void dfs(int current) {
        visited[current] = true;
        
        dp[current][1] = residents[current];
        
        for (int next : tree[current]) {
            if (!visited[next]) {
                dfs(next);
                
                dp[current][0] += Math.max(dp[next][0], dp[next][1]);
                
                dp[current][1] += dp[next][0];
            }
        }
    }
}