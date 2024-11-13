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

        // dp[i][j] : i번째 위치까지 계산했을 때, 결과값이 j인 경우의 수
        long[][] dp = new long[n-1][21];

        // 초기값 설정
        dp[0][arr[0]] = 1;

        // DP 진행
        for(int i = 1; i < n-1; i++){
            for(int j = 0; j <= 20; j++){
                if(dp[i-1][j] != 0){
                    // 더하기 연산
                    if(j + arr[i] <= 20){
                        dp[i][j + arr[i]] += dp[i-1][j];
                    }
                    // 빼기 연산
                    if(j - arr[i] >= 0){
                        dp[i][j - arr[i]] += dp[i-1][j];
                    }
                }
            }
        }

        // 마지막 숫자와 같은 결과를 가진 경우의 수 출력
        System.out.println(dp[n-2][arr[n-1]]);
    }
}