import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 두단계최단경로 {
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        graph = new ArrayList[n+1];

        for(int i = 0; i <= n; i++){
            graph[i] = new ArrayList<>();    
        }

        for(int i = 0; i < m; i++){
            String[] tmp = br.readLine().split(" ");
            int u = Integer.parseInt(tmp[0]);
            int v = Integer.parseInt(tmp[1]);
            int w = Integer.parseInt(tmp[2]);

            graph[u].add(new Node(v, w));
        }

        String[] query = br.readLine().split(" ");
        int X = Integer.parseInt(query[0]);
        int Y = Integer.parseInt(query[1]);
        int Z = Integer.parseInt(query[2]);

        // Case 1: X → Y → Z
        int[] distFromX = dijkstra(n, X);
        int[] distFromY = dijkstra(n, Y);
        
        int pathThroughY = -1;
        if (distFromX[Y] != Integer.MAX_VALUE && distFromY[Z] != Integer.MAX_VALUE) {
            pathThroughY = distFromX[Y] + distFromY[Z];
        }
        
        // Case 2: X → Z without passing through Y
        int pathWithoutY = findPathWithoutY(n, X, Y, Z);
        
        System.out.println(pathThroughY + " " + pathWithoutY);
    }

    static int[] dijkstra(int n, int start){
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int nowVertex = current.next;
            
            if(visited[nowVertex]) continue;
            visited[nowVertex] = true;

            for(Node nextNode : graph[nowVertex]){
                if(dist[nextNode.next] > dist[nowVertex] + nextNode.weight){
                    dist[nextNode.next] = dist[nowVertex] + nextNode.weight;
                    pq.offer(new Node(nextNode.next, dist[nextNode.next]));
                }
            }
        }
        
        return dist;
    }
    
    static int findPathWithoutY(int n, int start, int avoid, int end) {
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int nowVertex = current.next;
            
            if(nowVertex == avoid) continue;
            if(visited[nowVertex]) continue;
            visited[nowVertex] = true;

            for(Node nextNode : graph[nowVertex]){
                if(nextNode.next == avoid) continue;
                
                if(dist[nextNode.next] > dist[nowVertex] + nextNode.weight){
                    dist[nextNode.next] = dist[nowVertex] + nextNode.weight;
                    pq.offer(new Node(nextNode.next, dist[nextNode.next]));
                }
            }
        }
        
        return dist[end] == Integer.MAX_VALUE ? -1 : dist[end];
    }

    static class Node implements Comparable<Node> {
        int next;
        int weight;
        Node(int next, int weight){
            this.next = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node next){
            return Integer.compare(this.weight, next.weight);
        }
    }
}