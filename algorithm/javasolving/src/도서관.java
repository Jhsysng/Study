import java.io.*;
import java.util.*;

public class 도서관 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 책의 개수
        int M = Integer.parseInt(st.nextToken()); // 한 번에 들 수 있는 책의 수

        List<Integer> negative = new ArrayList<>(); // 음수 위치
        List<Integer> positive = new ArrayList<>(); // 양수 위치
        int maxDist = 0; // 가장 먼 거리

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int pos = Integer.parseInt(st.nextToken());
            if(pos < 0) {
                negative.add(-pos); // 음수는 절댓값으로 저장
            } else {
                positive.add(pos);
            }
        }

        // 내림차순 정렬
        negative.sort(Collections.reverseOrder());
        positive.sort(Collections.reverseOrder());

        // 가장 먼 거리 찾기
        if(!negative.isEmpty() && !positive.isEmpty()) {
            maxDist = Math.max(negative.getFirst(), positive.getFirst());
        } else if(!negative.isEmpty()) {
            maxDist = negative.getFirst();
        } else if(!positive.isEmpty()) {
            maxDist = positive.getFirst();
        }

        int total = 0;

        // 음수 방향 처리
        for(int i = 0; i < negative.size(); i += M) {
            total += negative.get(i) * 2; // 각 그룹의 가장 먼 거리만 고려
        }

        // 양수 방향 처리
        for(int i = 0; i < positive.size(); i += M) {
            total += positive.get(i) * 2; // 각 그룹의 가장 먼 거리만 고려
        }

        // 마지막에는 돌아올 필요가 없으므로 가장 먼 거리를 한 번만 계산
        total -= maxDist;

        System.out.println(total);
    }
}