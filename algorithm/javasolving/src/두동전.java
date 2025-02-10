import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class 두동전 {
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        char[][] map = new char[n][m];

        Coin coin1 = null;
        Coin coin2 = null;

        for(int i = 0; i < n; i++){
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                if(coin1 == null){
                    if(tmp.charAt(j) == 'o'){
                        coin1 = new Coin(i, j);
                    }
                } else if(coin2 == null){
                    if(tmp.charAt(j) == 'o'){
                        coin2 = new Coin(i, j);
                    }
                }
                map[i][j] = tmp.charAt(j);
            }
        }

        System.out.println(bfs(map, coin1, coin2));
    }

    private static int bfs(char[][] map, Coin c1, Coin c2) {
        int n = map.length;
        int m = map[0].length;
        boolean[][][][] visited = new boolean[n][m][n][m];
        Deque<State> queue = new LinkedList<>();

        queue.add(new State(c1, c2, 0));
        visited[c1.x][c1.y][c2.x][c2.y] = true;

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            if (curr.count >= 10) {
                continue;
            }

            for (int dir = 0; dir < 4; dir++) {
                Coin newCoin1 = new Coin(curr.coin1.x, curr.coin1.y);
                Coin newCoin2 = new Coin(curr.coin2.x, curr.coin2.y);

                boolean fall1 = newCoin1.move(dir, map, dx[dir], dy[dir]);
                boolean fall2 = newCoin2.move(dir, map, dx[dir], dy[dir]);

                if (fall1 && fall2) continue;  // 둘 다 떨어지면 실패
                if (fall1 || fall2) return curr.count + 1;  // 하나만 떨어지면 성공

                if (!visited[newCoin1.x][newCoin1.y][newCoin2.x][newCoin2.y]) {
                    visited[newCoin1.x][newCoin1.y][newCoin2.x][newCoin2.y] = true;
                    queue.add(new State(newCoin1, newCoin2, curr.count + 1));
                }
            }
        }

        return -1;
    }

    static class State {
        Coin coin1, coin2;
        int count;

        public State(Coin coin1, Coin coin2, int count) {
            this.coin1 = coin1;
            this.coin2 = coin2;
            this.count = count;
        }
    }

    static class Coin {
        int x;
        int y;

        public Coin(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean move(int dir, char[][] map, int dx, int dy) {
            int nx = x + dx;
            int ny = y + dy;

            // 보드 밖으로 떨어지는 경우
            if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
                return true;
            }

            // 벽을 만난 경우
            if (map[nx][ny] == '#') {
                return false;
            }

            // 이동 가능한 경우
            this.x = nx;
            this.y = ny;
            return false;
        }
    }
}