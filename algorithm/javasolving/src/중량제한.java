import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 중량제한 {
    static class Bridge{
        int to;
        int weight;

        public Bridge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static int N, M;
    static ArrayList<Bridge>[] graph;
    static int start, end;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i = 1; i<= N; i++){
            graph[i] = new ArrayList<>();
        }

        int maxWeight = 0;
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[A].add(new Bridge(B,C));
            graph[B].add(new Bridge(A,C));
            maxWeight = Math.max(maxWeight, C);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int left = 1;
        int right = maxWeight;
        int result  = 0;

        while(left <= right){
            int mid = (left + right) / 2;

            if(isPossible(mid)){
                result = mid;
                left = mid + 1;
            }else { 
                right = mid - 1;
            }
        }

        System.out.println(result);
    }

    static boolean isPossible(int limit){
        boolean[] visited = new boolean[N+1];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.offer(start);
        while(!queue.isEmpty()){
            int current = queue.poll();

            if(current == end){
                return true;
            }

            for(Bridge bridge : graph[current]){
                if(!visited[bridge.to] && bridge.weight >= limit){
                    visited[bridge.to] = true;
                    queue.offer(bridge.to);
                }
            }
        }

        return false;
    }
}
