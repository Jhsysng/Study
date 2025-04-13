import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 타임머신 {

    static class Edge{
        int start, end, time;
        public Edge(int start, int end, int time){
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();

        for(int i = 0; i< M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.add(new Edge(A, B, C));
        }

        long[] dist = new long[N+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        boolean hasNegativeCycle = false;


        for (int i = 0; i < N - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.start] != Long.MAX_VALUE && dist[edge.end] > dist[edge.start] + edge.time) {
                    dist[edge.end] = dist[edge.start] + edge.time;
                }
            }
        }
        
        // 음수 사이클 검출 (한 번 더 반복)
        for (Edge edge : edges) {
            if (dist[edge.start] != Long.MAX_VALUE && dist[edge.end] > dist[edge.start] + edge.time) {
                hasNegativeCycle = true;
                break;
            }
        }
        
        // 결과 출력
        StringBuilder sb = new StringBuilder();
        
        if (hasNegativeCycle) {
            sb.append("-1");
        } else {
            for (int i = 2; i <= N; i++) {
                if (dist[i] == Long.MAX_VALUE) {
                    sb.append("-1\n");
                } else {
                    sb.append(dist[i]).append("\n");
                }
            }
        }
        
        System.out.print(sb);
    }
}
