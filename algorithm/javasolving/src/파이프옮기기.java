import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class 파이프옮기기 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N+1][N+1];

        for(int i = 1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][][] dp = new long[N+1][N+1][3];

        dp[1][2][0] = 1;
        for(int r = 1; r<=N; r++){
            for(int c = 3; c <= N; c++){
                if(map[r][c] == 0){
                    dp[r][c][0]+= dp[r][c-1][0];
                    dp[r][c][0]+= dp[r][c-1][2];
                }

                if(r>1&&map[r][c]==0){
                    dp[r][c][1]+=dp[r-1][c][1];
                    dp[r][c][1]+=dp[r-1][c][2];
                }

                if(r>1 && map[r][c] == 0 && map[r-1][c] == 0 && map[r][c-1]==0){
                    dp[r][c][2]+=dp[r-1][c-1][0];
                    dp[r][c][2]+=dp[r-1][c-1][1];
                    dp[r][c][2]+=dp[r-1][c-1][2];
                }
            }
        }

        long answer = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
        System.out.println(answer);
    }
    
}
