import java.io.*;
import java.util.*;
public class 합분해 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        final int MOD = 1000000000;
        long[][] dp = new long[N+1][K+1];

        // 초기값 설정
        for (int n = 0; n <= N; n++) {
            dp[n][1] = 1;
        }
        
        for (int k = 1; k <= K; k++) {
            dp[0][k] = 1;
        }
        
        for (int k = 2; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                dp[n][k] = (dp[n][k-1] + dp[n-1][k]) % MOD;
            }
        }
        
        System.out.println(dp[N][K]);

    }
    
}
