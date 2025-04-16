import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 구간합구하기5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        
        // 2차원 배열로 저장
        int[][] map = new int[N+1][N+1];
        for(int i = 1; i<=N; i++){
            String[] inputMap = br.readLine().split(" ");
            for(int j = 1; j<=N; j++){
                map[i][j] = Integer.parseInt(inputMap[j-1]);
            }
        }
        
        // 2차원 누적합 계산
        int[][] dp = new int[N+1][N+1];
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + map[i][j];
            }
        }
        
        // 구간합 계산
        for(int i = 0; i<M; i++){
            String[] input3 = br.readLine().split(" ");
            int x1 = Integer.parseInt(input3[0]);
            int y1 = Integer.parseInt(input3[1]);
            int x2 = Integer.parseInt(input3[2]);
            int y2 = Integer.parseInt(input3[3]);
            
            int result = dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1];
            System.out.println(result);
        }
    }
}