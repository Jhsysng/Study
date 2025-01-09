import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 자리채우기 {
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int C = Integer.parseInt(s[0]);
        int R = Integer.parseInt(s[1]);
        int K = Integer.parseInt(br.readLine());

        if (K > C * R) {
            System.out.println(0);
            return;
        }

        int x = 1, y = 1;
        int dir = 0;
        int count = 1;
        boolean[][] visited = new boolean[R + 1][C + 1];

        while (count < K) {
            visited[y][x] = true;
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 1 || nx > C || ny < 1 || ny > R || visited[ny][nx]) {
                dir = (dir + 1) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            x = nx;
            y = ny;
            count++;
        }

        System.out.println(x + " " + y);
    }
}