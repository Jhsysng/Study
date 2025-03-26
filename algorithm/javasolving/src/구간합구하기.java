import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기 {
    public static long[] tree;
    public static long[] nums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        nums = new long[N+1];
        for(int i = 1; i<=N; i++){
            nums[i] = Long.parseLong(br.readLine());
        }

        tree = new long[4*N];

        init(1, N, 1);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            
            if (a == 1) { // 값 변경
                update(1, N, 1, b, c);
                nums[b] = c; // 원본 배열도 업데이트
            } else { // 구간 합 구하기
                sb.append(sum(1, N, 1, b, (int)c)).append("\n");
            }
        }
        
        System.out.print(sb);
    }

    static long init(int start, int end, int node){
        if(start == end){
            return tree[node] = nums[start];
        }

        int mid = (start  + end) /2;
        return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2+1);
    }

    static long sum(int start, int end, int node, int left, int right){
        if(left>end||right<start){
            return 0;
        }

        if(left<=start && end <= right){
            return tree[node];
        }

        int mid = (start + end) / 2;
        
        return sum(start, mid, node*2, left, right) + sum(mid + 1 , end, node*2 + 1, left, right);
    }
    
    static void update(int start, int end, int node, int index, long newValue){
        if(index < start || index > end){
            return;
        }

        if(start == end){
            tree[node] =newValue;
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node*2, index, newValue);
        update(mid+1, end, node*2 + 1, index, newValue);

        tree[node] = tree[node*2] + tree[node*2 + 1];
    }
}
