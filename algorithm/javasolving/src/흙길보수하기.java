import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 흙길보수하기 {
    static class Puddle implements Comparable<Puddle> {
        int start;
        int end;
        
        Puddle(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Puddle o) {
            return Integer.compare(this.start, o.start);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        
        List<Puddle> puddles = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            puddles.add(new Puddle(start, end));
        }
        
        // 웅덩이를 시작점 기준으로 정렬
        Collections.sort(puddles);
        
        int boardCount = 0;  // 필요한 널빤지 개수
        int coveredPosition = 0;  // 현재까지 널빤지로 덮은 위치
        
        for (Puddle puddle : puddles) {
            // 이미 덮은 부분은 건너뛰기
            if (coveredPosition >= puddle.end) continue;
            
            // 시작점 조정 (현재까지 덮은 위치보다 물웅덩이 시작점이 더 크면 시작점부터 시작)
            int startPosition = Math.max(coveredPosition, puddle.start);
            
            // 필요한 널빤지 개수 계산 (올림 연산)
            int requiredBoards = (puddle.end - startPosition + L - 1) / L;
            boardCount += requiredBoards;
            
            // 덮은 위치 업데이트
            coveredPosition = startPosition + requiredBoards * L;
        }
        
        System.out.println(boardCount);
    }
}