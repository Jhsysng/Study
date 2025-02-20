import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class 적록색약 {
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[][] map = new char[n][n];

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        System.out.println(bfs(map, n) + " " + bfsRG(map, n));
    }

    // 일반인이 보는 영역 수 계산
    public static int bfs(char[][] map, int n) {
        boolean[][] visited = new boolean[n][n];
        int cnt = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    // 새로운 구역 발견
                    cnt++;
                    Deque<Point> dq = new LinkedList<>();
                    dq.add(new Point(i, j, map[i][j]));
                    visited[i][j] = true;

                    while(!dq.isEmpty()) {
                        Point tmp = dq.poll();

                        for(int k = 0; k < 4; k++) {
                            int nx = tmp.x + dx[k];
                            int ny = tmp.y + dy[k];

                            if(0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny]) {
                                if(tmp.color == map[nx][ny]) {
                                    visited[nx][ny] = true;
                                    dq.add(new Point(nx, ny, map[nx][ny]));
                                }
                            }
                        }
                    }
                }
            }
        }
        return cnt;
    }

    // 적록색약이 보는 영역 수 계산
    public static int bfsRG(char[][] map, int n) {
        boolean[][] visited = new boolean[n][n];
        int cnt = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    // 새로운 구역 발견
                    cnt++;
                    Deque<Point> dq = new LinkedList<>();
                    dq.add(new Point(i, j, map[i][j]));
                    visited[i][j] = true;

                    while(!dq.isEmpty()) {
                        Point tmp = dq.poll();
                        char tmpColor = tmp.color;

                        for(int k = 0; k < 4; k++) {
                            int nx = tmp.x + dx[k];
                            int ny = tmp.y + dy[k];

                            if(0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny]) {
                                // 적록색약 로직: R과 G를 같은 색으로 취급
                                if(isSameColorForRG(tmpColor, map[nx][ny])) {
                                    visited[nx][ny] = true;
                                    dq.add(new Point(nx, ny, map[nx][ny]));
                                }
                            }
                        }
                    }
                }
            }
        }
        return cnt;
    }

    // 적록색약을 위한 색상 비교 메서드
    private static boolean isSameColorForRG(char c1, char c2) {
        // 둘 다 R 또는 G인 경우 (적록색약에게는 같은 색)
        if((c1 == 'R' || c1 == 'G') && (c2 == 'R' || c2 == 'G')) {
            return true;
        }
        // 둘 다 B인 경우
        return c1 == c2;
    }

    public static class Point {
        int x;
        int y;
        char color;

        public Point(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
}