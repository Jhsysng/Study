import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        long[] cables = new long[K];
        
        for (int i = 0; i < K; i++) {
            cables[i] = Long.parseLong(br.readLine());
        }
        
        long left = 1;
        long right = Long.MAX_VALUE - 1;
        
        long result = 0;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            
            long count = 0;
            for (int i = 0; i < K; i++) {
                count += cables[i] / mid;
            }
            
            if (count >= N) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        System.out.println(result);
    }
}