import java.io.*;
import java.util.*;

public class 학생회뽑기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        
        int[] student = new int[n];
        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            student[i] = Integer.parseInt(input2[i]);
        }
        
        // N이 작은 경우 (예: 25 이하) 비트마스킹 접근 방식 사용
        if (n <= 25) {
            int answer = 0;
            
            for (int i = 0; i < (1 << n); i++) {
                if (Integer.bitCount(i) == k) {
                    int tmp = -1; // 모든 비트 1로 설정
                    
                    for (int j = 0; j < n; j++) {
                        if ((i & (1 << j)) != 0) {
                            tmp &= student[j];
                        }
                    }
                    
                    answer = Math.max(answer, tmp);
                }
            }
            
            System.out.println(answer);
        } else {
            // N이 큰 경우, 그리디한 접근 방식이 필요
            // 이 문제에서는 k명을 선택해야 하므로, 가장 비트가 많이 켜진 학생들을 선택하는 전략
            
            // 비트 위치별 가장 많은 1을 가진 k개 학생 선택 전략
            // (이 부분은 문제 특성에 따라 다르게 최적화해야 할 수 있음)
            Arrays.sort(student);
            
            // k개의 가장 큰 값 선택
            int answer = -1;
            for (int i = n - k; i < n; i++) {
                answer &= student[i];
            }
            
            System.out.println(answer);
        }
    }
}