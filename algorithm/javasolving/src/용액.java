import java.io.*;
import java.util.*;

class 용액 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] solutions = new int[N];
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0;
        int right = N - 1;
        
        int minDiff = Integer.MAX_VALUE;
        int resultLeft = 0;
        int resultRight = 0;
        
        while (left < right) {
            int sum = solutions[left] + solutions[right];
            int diff = Math.abs(sum);
            
            if (diff < minDiff) {
                minDiff = diff;
                resultLeft = solutions[left];
                resultRight = solutions[right];
            }
            
            if (sum < 0) {
                left++;
            }
            else {
                right--;
            }
        }
        
        System.out.println(resultLeft + " " + resultRight);
    }
}