import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 예산 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] requests = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxRequest = 0;
        for(int i = 0; i < n; i++){
            requests[i] = Integer.parseInt(st.nextToken());
            maxRequest = Math.max(maxRequest, requests[i]);
        }

        int totalBudget = Integer.parseInt(br.readLine());

        int sum = 0;
        for(int request: requests){
            sum += request;
        }

        int left = 0;
        int right = maxRequest;
        int answer = 0;

        while(left<=right){
            int mid = (left + right)/2;
            long budgetSum =  calculateBudget(requests, mid);

            if(budgetSum <= totalBudget){
                answer = mid;
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
    }

    static long calculateBudget(int[] requests, int limit){
        long sum = 0;
        for(int request : requests){
            sum += Math.min(request, limit);
        }

        return sum;
    }
}
