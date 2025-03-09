import java.io.*;
import java.util.*;

class 심각한계단중독입니다 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(isPossible(nums) ? "1" : "-1");
    }
    
    private static boolean isPossible(int[] nums) {
        int n = nums.length;
        
        // n=1인 경우는 항상 가능
        if (n == 1) {
            return true;
        }
        
        // 홀수 길이는 불가능 (첫번째와 마지막 숫자가 1 차이가 나야 하므로)
        if (n % 2 != 0) {
            return false;
        }
        
        // 빈도수 계산
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        
        // 최소값과 최대값 찾기
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        
        for (int key : counter.keySet()) {
            minVal = Math.min(minVal, key);
            maxVal = Math.max(maxVal, key);
        }
        
        // 최소값과 최대값 각각 1개씩 사용
        counter.put(minVal, counter.get(minVal) - 1);
        counter.put(maxVal, counter.get(maxVal) - 1);
        
        int cnt = n - 2; // 남은 숫자 개수
        
        // min+1부터 max-1까지 각각 2개 이상 있어야 함
        for (int i = minVal + 1; i < maxVal; i++) {
            if (!counter.containsKey(i) || counter.get(i) < 2) {
                return false;
            }
            counter.put(i, counter.get(i) - 2);
            cnt -= 2;
        }
        
        // 남은 숫자가 페어로 만들어질 수 있는지 확인
        for (int i = minVal; i < maxVal; i++) {
            if (!counter.containsKey(i) || counter.get(i) == 0) {
                continue;
            }
            
            if (!counter.containsKey(i + 1) || counter.get(i) > counter.get(i + 1)) {
                return false;
            }
            
            counter.put(i + 1, counter.get(i + 1) - counter.get(i));
            cnt -= counter.get(i) * 2;
            counter.put(i, 0);
        }
        
        // 모든 숫자가 사용되었는지 확인
        return cnt == 0;
    }
}