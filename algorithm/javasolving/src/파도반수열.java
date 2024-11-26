import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 파도반수열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
        int[] queries = new int[T];
        int maxQuery = 0;

        // 입력 저장 및 최대값 확인
        for (int i = 0; i < T; i++) {
            queries[i] = Integer.parseInt(br.readLine());
            maxQuery = Math.max(maxQuery, queries[i]);
        }

        // 최대값까지 DP 배열 생성 (maxQuery가 0일 경우 고려)
        long[] dp = new long[Math.max(101, maxQuery + 1)]; // 100까지 커버 가능

        // 초기값 설정
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;

        // DP 배열 채우기
        for (int i = 6; i <= maxQuery; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }

        // 각 테스트케이스 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int query : queries) {
            sb.append(dp[query]).append("\n");
        }
        System.out.print(sb.toString());
    }
}