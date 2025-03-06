import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분수열의합 {
    
    static int N, S;
    static int[] arr;
    static int count = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        findSubsequences(0, 0);
        if (S == 0) {
            count--;
        }
        
        System.out.println(count);
    }
    static void findSubsequences(int idx, int sum) {
        if (idx == N) {
            if (sum == S) {
                count++;
            }
            return;
        }
        
        findSubsequences(idx + 1, sum + arr[idx]);
        findSubsequences(idx + 1, sum);
    }
}