import java.io.*;
import java.util.*;

public class 뱀 {
    static int n, k, l;
    static int[][] board;
    static Map<Integer, Character> directionChanges;
    static int[] dx = {0, 1, 0, -1}; // 오른쪽, 아래, 왼쪽, 위 순서
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        board = new int[n + 1][n + 1];

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 1; // 사과 위치 표시
        }

        l = Integer.parseInt(br.readLine());
        directionChanges = new HashMap<>();

        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            directionChanges.put(x, c);
        }

        System.out.println(playGame());
    }

    static int playGame() {
        Deque<int[]> snake = new LinkedList<>();
        snake.add(new int[]{1, 1});
        boolean[][] visited = new boolean[n + 1][n + 1];
        visited[1][1] = true;

        int time = 0, direction = 0, x = 1, y = 1;

        while (true) {
            time++;
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if (nx <= 0 || ny <= 0 || nx > n || ny > n || visited[nx][ny]) break;

            if (board[nx][ny] == 1) {
                board[nx][ny] = 0;
            } else {
                int[] tail = snake.pollFirst();
                visited[tail[0]][tail[1]] = false;
            }

            snake.add(new int[]{nx, ny});
            visited[nx][ny] = true;

            if (directionChanges.containsKey(time)) {
                char turn = directionChanges.get(time);
                direction = (turn == 'L') ? (direction + 3) % 4 : (direction + 1) % 4;
            }

            x = nx;
            y = ny;
        }

        return time;
    }
}