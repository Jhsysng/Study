import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 계단오르기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        // 계단이 하나만 있는 경우의 예외 처리
        if (n == 1) {
            System.out.println(Integer.parseInt(br.readLine()));
            return;
        }
        
        int[] stairs = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }
        
        int[][] dp = new int[n + 1][3];

        dp[1][1] = stairs[1];
        dp[1][2] = 0;
        if (n >= 2) {
            dp[2][1] = stairs[1] + stairs[2];
            dp[2][2] = stairs[2];
        }
        for (int i = 3; i <= n; i++) {
            
            dp[i][1] = dp[i-1][2] + stairs[i];
            dp[i][2] = Math.max(dp[i-2][1], dp[i-2][2]) + stairs[i];
        }
        
        System.out.println(Math.max(dp[n][1], dp[n][2]));
    }
}
