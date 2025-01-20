import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 트리의지름 {
    static List<List<Node>> tree;
    static boolean[] visited;
    static int maxDistance;
    static int farthestNode;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 트리 초기화
        tree = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        // 트리 구성
        for(int i = 0; i < n-1; i++) {
            String[] input = br.readLine().split(" ");
            int parent = Integer.parseInt(input[0]);
            int child = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            // 양방향 연결
            tree.get(parent).add(new Node(child, weight));
            tree.get(child).add(new Node(parent, weight));
        }

        visited = new boolean[n + 1];
        maxDistance = 0;
        farthestNode = 1;
        dfs(1, 0);

        visited = new boolean[n + 1];
        maxDistance = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDistance);
    }

    public static void dfs(int current, int distance) {
        visited[current] = true;

        if(distance > maxDistance) {
            maxDistance = distance;
            farthestNode = current;
        }

        for(Node next : tree.get(current)) {
            if(!visited[next.vertex]) {
                dfs(next.vertex, distance + next.weight);
            }
        }
    }

    public static class Node {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}