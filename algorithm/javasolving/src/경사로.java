import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 경사로 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int L = Integer.parseInt(input[1]);

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int[] row = new int[N];
            int[] col = new int[N];
            for (int j = 0; j < N; j++) {
                row[j] = map[i][j];
                col[j] = map[j][i];
            }
            if (check(row, L)) {
                answer++;
            }
            if (check(col, L)) {
                answer++;
            }
        }

        System.out.println(answer);

    }

    public static boolean check(int[] arr, int L) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                continue;
            }
            if (Math.abs(arr[i] - arr[i + 1]) > 1) {
                return false;
            }
            if (arr[i] > arr[i + 1]) {
                for (int j = i + 1; j <= i + L; j++) {
                    if (j >= n || arr[i + 1] != arr[j] || visited[j]) {
                        return false;
                    }
                    visited[j] = true;
                }
            } else {
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || arr[i] != arr[j] || visited[j]) {
                        return false;
                    }
                    visited[j] = true;
                }
            }
        }
        return true;
    }
}
