import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
14500 테트로미노
 */
public class 테트로미노 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] readline = reader.readLine().split(" ");
        int n = Integer.parseInt(readline[0]);
        int m = Integer.parseInt(readline[1]);
        int[][] matrix = new int[n][m];
        for(int i = 0; i < n; i++) {
            String[] tmp = reader.readLine().split(" ");
            for(int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        List<Tetronomino> tetronominos = new ArrayList<>();

        // 모든 테트로미노 모양 추가 (19가지)
        tetronominos.add(new Tetronomino(new int[][]{{1, 1, 1, 1}}));
        tetronominos.add(new Tetronomino(new int[][]{{1}, {1}, {1}, {1}}));
        tetronominos.add(new Tetronomino(new int[][]{{1, 1}, {1, 1}})); // 정사각형
        tetronominos.add(new Tetronomino(new int[][]{{1, 0}, {1, 0}, {1, 1}}));
        tetronominos.add(new Tetronomino(new int[][]{{0, 1}, {0, 1}, {1, 1}}));
        tetronominos.add(new Tetronomino(new int[][]{{1, 1, 1}, {1, 0, 0}}));
        tetronominos.add(new Tetronomino(new int[][]{{1, 1, 1}, {0, 0, 1}}));
        tetronominos.add(new Tetronomino(new int[][]{{1, 0, 0}, {1, 1, 1}}));
        tetronominos.add(new Tetronomino(new int[][]{{0, 0, 1}, {1, 1, 1}}));
        tetronominos.add(new Tetronomino(new int[][]{{1, 1, 0}, {0, 1, 1}}));
        tetronominos.add(new Tetronomino(new int[][]{{0, 1, 1}, {1, 1, 0}}));
        tetronominos.add(new Tetronomino(new int[][]{{1, 0}, {1, 1}, {0, 1}}));
        tetronominos.add(new Tetronomino(new int[][]{{0, 1}, {1, 1}, {1, 0}}));
        tetronominos.add(new Tetronomino(new int[][]{{1, 1, 1}, {0, 1, 0}}));
        tetronominos.add(new Tetronomino(new int[][]{{0, 1}, {1, 1}, {0, 1}}));
        tetronominos.add(new Tetronomino(new int[][]{{1, 0}, {1, 1}, {1, 0}}));
        tetronominos.add(new Tetronomino(new int[][]{{0, 1, 0}, {1, 1, 1}}));
        tetronominos.add(new Tetronomino(new int[][]{{1, 1, 0}, {0, 1, 1}}));
        tetronominos.add(new Tetronomino(new int[][]{{0, 1, 1}, {1, 1, 0}}));

        int answer = 0;
        for (Tetronomino tmp : tetronominos) {
            int width = tmp.getWidth();
            int height = tmp.getHeight();

            for (int i = 0; i <= n - height; i++) {
                for (int j = 0; j <= m - width; j++) {
                    answer = Math.max(tmp.calcType(matrix, i, j), answer);
                }
            }
        }

        System.out.println(answer);
    }

    public static class Tetronomino {
        private final int[][] shape;
        private final int width;
        private final int height;

        Tetronomino(int[][] shape) {
            this.shape = shape;
            this.height = shape.length;
            this.width = shape[0].length;
        }

        public int[][] getShape() {
            return shape;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public int calcType(int[][] matrix, int a, int b) {
            int sum = 0;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (shape[i][j] == 1) {
                        sum += matrix[a + i][b + j];
                    }
                }
            }
            return sum;
        }
    }
}