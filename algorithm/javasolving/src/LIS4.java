import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class LIS4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        int[] prev = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int maxLength = 1;
        int maxIndex = 0;

        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(arr[j] < arr[i] && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }

            if(dp[i] > maxLength){
                maxLength = dp[i];
                maxIndex = i;
            }
        }

        System.out.println(maxLength);

        List<Integer> lis = new ArrayList<>();
        while(maxIndex != -1){
            lis.add(arr[maxIndex]); 
            maxIndex = prev[maxIndex];
        }

        Collections.reverse(lis);
        for(int num : lis){
            System.out.print(num + " ");
        }
    }
}