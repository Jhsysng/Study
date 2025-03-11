import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 텀프로젝트 {
    static int[] choices;
    static boolean[] visited;
    static boolean[] finished;
    static int count;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        
        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            
            choices = new int[n+1];
            visited = new boolean[n+1];
            finished = new boolean[n+1];
            count = 0;
            
            for(int i = 1; i <= n; i++) {
                choices[i] = Integer.parseInt(input[i-1]);
            }
            
            for(int i = 1; i <= n; i++) {
                if(!visited[i]) {
                    dfs(i);
                }
            }
            
            sb.append(n - count).append('\n');
        }
        
        System.out.print(sb);
    }
    
    public static void dfs(int node) {
        visited[node] = true;
        
        int next = choices[node];
        
        if(!visited[next]) {
            dfs(next);
        } else if(!finished[next]) {
            // 사이클 발견: 현재 노드부터 다음 노드까지 카운트
            count++;
            for(int i = next; i != node; i = choices[i]) {
                count++;
            }
        }
        
        // 현재 노드 처리 완료
        finished[node] = true;
    }
}