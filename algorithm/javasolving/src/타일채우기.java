import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 타일채우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());


        if (n % 2 == 1) {
            System.out.println(0);
            return;
        }

        // dp 배열 초기화
        long[] dp = new long[n + 1];
        dp[0] = 1;  // 빈 벽을 채우는 경우의 수는 1
        dp[2] = 3;  // 3x2 벽을 채우는 경우의 수는 3

        for (int i = 4; i <= n; i += 2) {
            dp[i] = dp[i-2] * 4 - dp[i-4];
        }

        System.out.println(dp[n]);
    }
}