import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 사다리조작 {
    static int N, M, H;
    static boolean[][] ladder;
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 입력 줄에서 N, M, H를 읽음
        String[] tokens = reader.readLine().split(" ");
        N = Integer.parseInt(tokens[0]); // 세로선 개수
        M = Integer.parseInt(tokens[1]); // 가로선 개수
        H = Integer.parseInt(tokens[2]); // 세로선마다 가로선을 놓을 수 있는 위치의 개수

        // 사다리 정보를 저장할 2차원 배열
        ladder = new boolean[H + 1][N + 1];

        // 가로선 정보 입력
        for (int i = 0; i < M; i++) {
            tokens = reader.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]); // 가로선의 높이
            int b = Integer.parseInt(tokens[1]); // 가로선의 시작점
            ladder[a][b] = true;
        }

        // 추가할 가로선 개수를 0부터 3까지 시도
        for (int i = 0; i <= 3; i++) {
            dfs(0, 0, i);
            if (answer != -1) {
                break;
            }
        }

        System.out.println(answer);
        reader.close();
    }

    // depth: 현재까지 추가한 가로선 개수
    // start: 탐색 시작 위치
    // target: 목표로 하는 가로선 개수
    static void dfs(int depth, int start, int target) {
        if (answer != -1) return; // 이미 답을 찾은 경우
        if (depth == target) { // 목표한 개수만큼 가로선을 추가한 경우
            if (check()) { // 모든 세로선이 자기 자신으로 내려오는지 확인
                answer = target;
            }
            return;
        }

        // 가로선 추가
        for (int i = start; i < H * N; i++) {
            int row = i / N + 1;
            int col = i % N + 1;

            if (col == N) continue; // 마지막 세로선에는 가로선을 놓을 수 없음

            // 가로선을 놓을 수 있는 조건 확인
            if (!ladder[row][col] && !ladder[row][col+1] &&
                    (col == 1 || !ladder[row][col-1])) {
                ladder[row][col] = true;
                dfs(depth + 1, i + 1, target);
                ladder[row][col] = false;
            }
        }
    }

    // 모든 세로선이 자기 자신으로 내려오는지 확인하는 메소드
    static boolean check() {
        for (int start = 1; start <= N; start++) {
            int current = start;

            // 위에서 아래로 내려가기
            for (int row = 1; row <= H; row++) {
                if (current < N && ladder[row][current]) {
                    current++;
                } else if (current > 1 && ladder[row][current-1]) {
                    current--;
                }
            }

            if (current != start) return false;
        }
        return true;
    }
}