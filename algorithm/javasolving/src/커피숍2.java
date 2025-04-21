import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 커피숍2 {
    static long[] tree;
    static long[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i<=N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        tree = new long[N*4];
        init(1,1,N);
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< Q; i++){
            st = new StringTokenizer(br.readLine());
            int x=  Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            int left = Math.min(x, y);
            int right = Math.max(x, y);

            long sum = query(1, 1, N ,left ,right);
            sb.append(sum).append("\n");

            long diff = b - arr[a];
            arr[a] = b;
            update(1, 1, N, a, diff);

        }

        System.out.println(sb);
    }

    static void init(int node, int start, int end){
        if(start == end){
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end)/2;
        init(node*2, start, mid);
        init(node*2 + 1 ,mid + 1, end);
        tree[node] = tree[node*2] + tree[node*2 + 1];
    }

    static long query(int node, int start, int end, int left, int right){
        if(left>end||right<start){
            return 0;
        }

        if(left<=start && end <= right){
            return tree[node];
        }

        int mid = (start + end) / 2;
        long leftSum = query(node*2, start, mid, left, right);
        long rightSum = query(node*2+1, mid+1, end, left, right);
        return leftSum + rightSum;
    }

    static void update(int node, int start, int end, int index, long diff){
        if(index < start || index > end){
            return;
        }

        tree[node] += diff;

        if(start != end){
            int mid = (start+end)/2;
            update(node*2, start, mid, index, diff);
            update(node*2+1, mid + 1, end, index, diff);
        }
    }
}
