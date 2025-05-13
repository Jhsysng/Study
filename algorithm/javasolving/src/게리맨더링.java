import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 게리맨더링 {
    static int N;
    static int[] population;
    static ArrayList<ArrayList<Integer>> graph;
    static int minDiff = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        population = new int[N+1];
        
        StringTokenizer st= new StringTokenizer(br.readLine());

        for(int i = 1; i<=N;i++){
            population[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();

        for(int i = 0; i<=N;i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            for(int j = 0; j < count; j++){
                int adj = Integer.parseInt(st.nextToken());
                graph.get(i).add(adj);
            }
        }

        divide(1, new boolean[N+1]);

        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);

    }

    static void divide(int index, boolean[] selected){
        if(index > N){
            List<Integer> group1 = new ArrayList<>();
            List<Integer> group2 = new ArrayList<>();

            for(int i =  1 ; i<=N; i++){
                if(selected[i]) group1.add(i);
                else group2.add(i);
            }

            if(group1.size() == 0|| group2.size() == 0 ) return;

            if(isConnected(group1) && isConnected(group2)){
                int sum1 = 0, sum2 = 0;

                for(int i : group1) sum1 += population[i];
                for(int i : group2) sum2 += population[i];

                minDiff = Math.min(minDiff, Math.abs(sum1 - sum2));

            }
            return;
        }
    }

    static boolean isConnected(List<Integer> group){
        if(group.size() == 0) return false;

        boolean[] visited = new boolean[N+1];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(group.get(0));
        visited[group.get(0)] = true;

        int count = 1;
        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int next : graph.get(current)){
                if(!visited[next] && group.contains(next)){
                    queue.offer(next);
                    visited[next] =  true;
                    count++;
                }
            }
        }
        return count == group.size();
    }
}
