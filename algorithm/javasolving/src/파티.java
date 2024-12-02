import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 파티 {

    static class Node implements Comparable<Node> {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int X = Integer.parseInt(input[2]);

        List<List<Node>> graph = new ArrayList<>();
        List<List<Node>> reverseGraph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String[] edgeInput = br.readLine().split(" ");
            int u = Integer.parseInt(edgeInput[0]);
            int v = Integer.parseInt(edgeInput[1]);
            int w = Integer.parseInt(edgeInput[2]);
            graph.get(u).add(new Node(v, w));
            reverseGraph.get(v).add(new Node(u, w));
        }

        // 1. 각 마을에서 X로 가는 최단 거리 계산
        int[] toX = dijkstra(graph, N, X);

        // 2. X에서 각 마을로 가는 최단 거리 계산
        int[] fromX = dijkstra(reverseGraph, N, X);

        // 3. 각 마을의 왕복 시간 계산
        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, toX[i] + fromX[i]);
        }

        System.out.println(maxTime);
    }

    private static int[] dijkstra(List<List<Node>> graph, int N, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            if (current.weight > dist[u]) continue;

            for (Node neighbor : graph.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        return dist;
    }
}