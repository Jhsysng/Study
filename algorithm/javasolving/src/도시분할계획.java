import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 도시분할계획{
        public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Edge> edges = new ArrayList<>();

        String[] input = br.readLine().split(" ");

        int edge = Integer.parseInt(input[0]);
        int v = Integer.parseInt(input[1]);

        int edgeCount = 0;
        int totalWeight = 0;

        for(int i = 0; i<v; i++){
            String[] tmp = br.readLine().split(" ");
            int start = Integer.parseInt(tmp[0]);
            int end = Integer.parseInt(tmp[1]);
            int weight = Integer.parseInt(tmp[2]);
            edges.add(new Edge(start, end, weight));
        }

        List<Integer> maxWeights = new ArrayList<>();

        Collections.sort(edges);
            UnionFind uf = new UnionFind(edge);
        for(Edge tmpEdge: edges){
            if(uf.union(tmpEdge.start, tmpEdge.end)){
                totalWeight += tmpEdge.weight;
                maxWeights.add(tmpEdge.weight);
                edgeCount++;
                if(edgeCount == v-1){
                    break;
                }
            }
        }
        int maxWeight = Collections.max(maxWeights);

        System.out.println(totalWeight-maxWeight);


    }

    public static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other){
            return this.weight - other.weight;
        }
    }

    public static class UnionFind{
        int[] parent, rank;

        public UnionFind(int size){
            parent = new int[size+1];
            rank = new int[size+1];

            for (int i = 0; i <= size; i++) { 
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x){
            if(parent[x] != x){
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public boolean union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);

            if(rootX==rootY){
                return false;
            }

            if(rank[rootX] > rank[rootY]){
                parent[rootY] = rootX;
            }else if (rank[rootX]<rank[rootY]) {
                parent[rootX] = rootY;
            }else{
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            return true;
        }
    }
}