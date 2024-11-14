import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 학년 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        int n = Integer.parseInt(reader.readLine());
        String[] split = reader.readLine().split(" ");
        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(split[i]);
        }

        long[][] dp = new long[n-1][21];

        dp[0][arr[0]] = 1;

        // DP 진행
        for(int i = 1; i < n-1; i++){
            for(int j = 0; j <= 20; j++){
                if(dp[i-1][j] != 0){
                    if(j + arr[i] <= 20){
                        dp[i][j + arr[i]] += dp[i-1][j];
                    }
                    if(j - arr[i] >= 0){
                        dp[i][j - arr[i]] += dp[i-1][j];
                    }
                }
            }
        }

        System.out.println(dp[n-2][arr[n-1]]);
    }
}