import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 팰린드롬 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(input[i]);
        }
        int t = Integer.parseInt(br.readLine());

        int[][] dp = new int[n][n];
        for(int i = 0; i<n; i++){
            dp[i][i] = 1;
        }

        for(int i = 0; i<n-1; i++){
            if(arr[i] == arr[i+1]){
                dp[i][i+1] = 1;
            }
        }

        for(int i = 2; i<n; i++){
            for(int j = 0; j<n-i; j++){
                if(arr[j] == arr[j+i] && dp[j+1][j+i-1] == 1){
                    dp[j][j+i] = 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<t; i++){
            String[] split = br.readLine().split(" ");
            int s = Integer.parseInt(split[0]);
            int e = Integer.parseInt(split[1]);
            sb.append(dp[s-1][e-1]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
