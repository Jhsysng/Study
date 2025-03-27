import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행렬곱셈순서 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] dimesions = new int[n][2];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            dimesions[i][0] = Integer.parseInt(st.nextToken());
            dimesions[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][n];

        for(int l = 1; l < n; l ++){
            for(int i = 0; i < n - l; i++){
                int j = i+ l;
                dp[i][j] = Integer.MAX_VALUE;

                for(int k = i; k<j; k++){
                    int operations = dp[i][k] + dp[k+1][j] + dimesions[i][0]*dimesions[k][1]*dimesions[j][1];
                    dp[i][j] = Math.min(dp[i][j], operations);
                }
            }
        }

        System.out.println(dp[0][n-1]);
    }
}
