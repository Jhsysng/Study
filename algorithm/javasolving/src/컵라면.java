import java.io.*;
import java.util.*;

public class 컵라면 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        Problem[] problems = new Problem[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());
            problems[i] = new Problem(deadline, ramen);
        }
        
        Arrays.sort(problems);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (Problem problem : problems) {
            pq.offer(problem.ramen);
            
            if (pq.size() > problem.deadline) {
                pq.poll();
            }
        }
        
        long result = 0;
        while (!pq.isEmpty()) {
            result += pq.poll();
        }
        
        System.out.println(result);
    }
    
    static class Problem implements Comparable<Problem> {
        int deadline;
        int ramen;
        
        Problem(int deadline, int ramen) {
            this.deadline = deadline;
            this.ramen = ramen;
        }
        
        @Override
        public int compareTo(Problem other) {
            return this.deadline - other.deadline;
        }
    }
}