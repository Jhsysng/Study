import java.io.*;
import java.util.*;

public class 전력난 {
    static class Edge implements Comparable<Edge>{
        int u, v, weight;

        public Edge(int u, int v, int weight){
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge other){
            return Integer.compare(this.weight, other.weight);
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if(m==0&n==0){
                break;
            }

            parent = new int[n];

            for(int i =0; i<m; i++){
                parent[i] = i;
            }

            List<Edge> edges = new ArrayList<>();
            long totalCost = 0;

            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                edges.add(new Edge(x, y, cost));
                totalCost += cost;

            }

            Collections.sort(edges);
            long mstCost = 0;
            int edgeCount = 0;

            for(Edge edge : edges){
                if(union(edge.u, edge.v)){
                    mstCost+= edge.weight;
                    edgeCount++;
                    if(edgeCount == m -1) break;
                }
            }

            sb.append(totalCost -mstCost).append("\n");
            System.out.print(sb);


        }


    }
    static int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static boolean union(int x, int y){
        x = find(x);
        y = find(y);

        if(x==y){
            return false;
        }

        parent[y] = x;
        return true;
    }

    
}
