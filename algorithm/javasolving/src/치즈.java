import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 치즈 {
    // 상하좌우 이동을 위한 방향 배열
    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};
    public static int n, m;
    public static int[][] board;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        board = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int time = 0;
        
        while (hasCheese()) {
            time++;
            boolean[][] visited = new boolean[n][m];
            
            markOuterAir(visited);
            
            meltCheese();
        }
        
        System.out.println(time);
    }
    
    public static boolean hasCheese() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void markOuterAir(boolean[][] visited) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0));
        visited[0][0] = true;
        board[0][0] = 2;
        
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                    if (board[nx][ny] == 0 || board[nx][ny] == 2) {
                        board[nx][ny] = 2;
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }
    }
    
    // 치즈 녹이기
    public static void meltCheese() {
        int[][] temp = new int[n][m];
        
        // 현재 보드 상태 복
        for (int i = 0; i < n; i++) {
            temp[i] = board[i].clone();
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    int count = 0;
                    
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        
                        if (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == 2) {
                            count++;
                        }
                    }
                    
                    if (count >= 2) {
                        temp[i][j] = 0;
                    }
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) {
                    board[i][j] = 0;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                }
            }
        }
    }
    
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}