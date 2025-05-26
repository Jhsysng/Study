import java.io.*;
import java.util.*;

public class 여행 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<int[]>[] flights = new ArrayList[N+1];

        for(int i = 1; i <= N; i++){
            flights[i] = new ArrayList<>();
        }

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a < b){
                flights[a].add(new int[]{b, c});
            }
        }

        int[][] dp = new int[N+1][N+1];

        for(int i = 0; i <= N; i++){
            Arrays.fill(dp[i], -1);
        }

        dp[1][1]=0;

        for(int count = 1; count < M; count++){
            for(int city = 1; city < N; city++){
                if(dp[city][count] == -1) continue;

                for(int[] flight : flights[city]){
                    int nextCity = flight[0];
                    int score = flight[1];
                    int newScore = dp[city][count] + score;
                    if(dp[nextCity][count+1] < newScore){
                        dp[nextCity][count+1] = newScore;
                    }
                }
            }
        }

        int answer = -1;
        for(int count = 1; count <= M; count++){
            answer = Math.max(answer, dp[N][count]);
        }
        System.out.println(answer);
    }

}
