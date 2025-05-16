import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class 트리 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer>[] children = new ArrayList[n];
        for(int i = 0; i< n ; i++){
            children[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] parent = new int[n];
        int root = -1;

        for(int i = 0; i< n ;i++){
            parent[i] = Integer.parseInt(st.nextToken());
            if(parent[i] == -1){
                root = i;
            }else {
                children[parent[i]].add(i);
            }
        }

        int removeNode = Integer.parseInt(br.readLine());

        boolean[] isRemoved = new boolean[n];

        markRemoved(removeNode, children, isRemoved);

        int leafCount = 0;

        if(isRemoved[root]){
            System.out.println(0);
            return;
        }
        for(int i = 0; i < n; i++){
            if(!isRemoved[i]){
                int validChildCount = 0;
                for(int child : children[i]){
                    if(!isRemoved[child]){
                        validChildCount++;
                    }
                }
                if(validChildCount == 0){
                    leafCount++;
                }
            }
        }

        System.out.println(leafCount);
    }

    private static void markRemoved(int node, List<Integer>[] children, boolean[] isRemoved){
        isRemoved[node] = true;
        for(int child : children[node]){
            markRemoved(child, children, isRemoved);
        }
    }
}
