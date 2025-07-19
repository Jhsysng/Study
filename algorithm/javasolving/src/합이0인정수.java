import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 합이0인정수 {
        public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        long[] sumAB = new long[n * n];
        int idx = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                sumAB[idx++] = (long)A[i] + B[j];
            }
        }

        Arrays.sort(sumAB);

        long count = 0;

        for(int k = 0; k < n; k++){
            for(int l = 0; l < n; l ++){
                long target = -((long) C[k] + D[l]);

                int left = lowerBound(sumAB, target);
                if(left < sumAB.length && sumAB[left] == target){
                    int right = upperBound(sumAB, target);
                    count += (right - left);
                }
            }
        }

        System.out.println(count);


    }

    private static int lowerBound(long[] arr, long target){
        int left = 0, right = arr.length;

        while(left < right){
            int mid = left + (right - left) / 2;

            if(arr[mid] >= target){
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static int upperBound(long[] arr, long target) {
        int left = 0, right = arr.length;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
