import java.io.BufferedReader;
import java.io.InputStreamReader;
//11659 구간 합 구하기 4
public class 구간합구하기4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int[] arr = new int[n];

        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input2[i]);
        }

        int[] sum = new int[n];
        sum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i-1] + arr[i];
        }

        for(int i = 0; i<m; i++){
            String[] input3 = br.readLine().split(" ");
            int start = Integer.parseInt(input3[0]);
            int end = Integer.parseInt(input3[1]);

            if(start == 1){
                System.out.println(sum[end-1]);
            } else {
                System.out.println(sum[end-1] - sum[start-2]);
            }
        }
    }
}
