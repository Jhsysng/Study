import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        int[] arr = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = arr[0];
        int d = arr[1];
        int k = arr[2];
        int c = arr[3];

        Map<Integer, Integer> nowDish = new HashMap<>();
        List<Integer> dishes = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            dishes.add(Integer.valueOf(reader.readLine()));
        }

        // 초기 k개의 접시 처리
        for(int i = 0; i < k; i++) {
            nowDish.put(dishes.get(i), nowDish.getOrDefault(dishes.get(i), 0) + 1);
        }

        int answer = nowDish.size();
        if(!nowDish.containsKey(c)) answer++; // 쿠폰 초밥이 없는 경우 +1

        // 슬라이딩 윈도우로 모든 경우 확인
        for(int i = 1; i < N; i++) {
            // 이전 접시 제거
            int prev = dishes.get(i - 1);
            nowDish.put(prev, nowDish.get(prev) - 1);
            if(nowDish.get(prev) == 0) {
                nowDish.remove(prev);
            }

            // 새로운 접시 추가 (회전을 고려하여 모듈로 연산 사용)
            int next = dishes.get((i + k - 1) % N);
            nowDish.put(next, nowDish.getOrDefault(next, 0) + 1);

            // 현재 가능한 초밥 가짓수 계산
            int current = nowDish.size();
            if(!nowDish.containsKey(c)) current++; // 쿠폰 초밥이 없는 경우 +1

            answer = Math.max(answer, current);
        }

        System.out.println(answer);
    }
}