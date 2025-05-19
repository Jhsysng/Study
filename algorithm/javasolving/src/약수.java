import java.io.*;
import java.util.*;
public class 약수 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[] a = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            a[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(a);

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 0; i<n; i++){
            for(int j = 0; j < i; j++){
                if(a[i]%a[j]==0){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLength = 0;
        for(int length:dp){
            maxLength = Math.max(maxLength, length);
        }

        System.out.println(n - maxLength);
    }
}
