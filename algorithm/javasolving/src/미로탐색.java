import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

//2178
public class 미로탐색 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[][] matrix = new int[n][m];

        for(int i = 0; i<n; i++){
            String[] tmp = reader.readLine().split("");
            for(int j = 0; j<m; j++){
                matrix[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        int answer = bfs(matrix, n, m);

        System.out.println(answer);
    }

    public static int bfs(int[][] arr, int n, int m){
        final int[] dx = {0, 0, -1, 1};
        final int[] dy = {1, -1, 0, 0};
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                visited[i][j] = false;
            }
        }

        q.offer(new Point(0,0, 1));
        visited[0][0] = true;


        while(!q.isEmpty()){
            Point p = q.poll();

            int tmpx = p.x;
            int tmpy= p.y;
            if(tmpx == m-1 && tmpy==n-1){
                return p.distance;
            }

            for(int i = 0; i<4; i++){
                int nx = tmpx + dx[i];
                int ny = tmpy + dy[i];

                if(0<=nx && nx < m && 0<= ny&& ny < n){
                    if(!visited[ny][nx]&&arr[ny][nx]!=0) {
                        visited[ny][nx] = true;
                        q.add(new Point(nx, ny, p.distance + 1));
                    }
                }
            }
        }

        return -1;
    }

    public static class Point{
        public int x;
        public int y;
        public int distance;

        Point(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.distance = d;
        }
    }
}
