import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MST {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int V = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);

        List<Edge> edges = new ArrayList<>();

        for(int i = 0; i < E; i++){
            String[] edgeInfo = br.readLine().split(" ");
            int u = Integer.parseInt(edgeInfo[0]);
            int v = Integer.parseInt(edgeInfo[1]);
            int w = Integer.parseInt(edgeInfo[2]);
            edges.add(new Edge(u, v, w));
        }

        Collections.sort(edges);

        UnionFind uf = new UnionFind(V);

        long mstWeight = 0;
        for(Edge edge:edges){
            if(uf.find(edge.u) != uf.find(edge.v)){
                uf.union(edge.u, edge.v);
                mstWeight += edge.w;
            }
        }

        System.out.println(mstWeight);
    }

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        public Edge(int u, int v, int weight){
            this.u = u;
            this.v = v;
            this.w = weight;
        }

        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.w, o.w);
        }
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size){
            parent = new int[size];
            rank = new int[size];
            for(int i = 0; i<size; i++){
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int node){
            if(parent[node] != node){
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }

        public void union(int u, int v){
            int rootU = find(u);
            int rootV = find(v);

            if(rootU != rootV){
                if(rank[rootU] > rank[rootV])
                    parent[rootV] = rootU;
                else if(rank[rootU] < rank[rootV])
                    parent[rootU] = rootV;
                else{
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }
    }
}
