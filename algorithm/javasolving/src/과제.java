import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 과제 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[][] assignments = new int[N][2];
        
        int maxDay = 0;
        
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int deadline = Integer.parseInt(input[0]);
            int score = Integer.parseInt(input[1]);
            
            assignments[i][0] = deadline;
            assignments[i][1] = score;
            
            maxDay = Math.max(maxDay, deadline);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        Arrays.sort(assignments, (a, b) -> {
            if (a[0] == b[0]) { // 마감일이 같으면 점수가 높은 순
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        
        int totalScore = 0;
        int idx = N - 1;
        
        for (int day = maxDay; day >= 1; day--) {
            while (idx >= 0 && assignments[idx][0] >= day) {
                pq.offer(assignments[idx][1]);
                idx--;
            }
            
            if (!pq.isEmpty()) {
                totalScore += pq.poll();
            }
        }
        
        System.out.println(totalScore);
    }
}