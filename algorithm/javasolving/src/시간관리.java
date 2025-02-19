import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 시간관리 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] tasks = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            tasks[i][0] = Integer.parseInt(st.nextToken());
            tasks[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(tasks, (a, b) -> b[1] - a[1]);

        int currentTime = tasks[0][1];

        for (int i = 0; i < N; i++) {
            currentTime = Math.min(currentTime, tasks[i][1]);
            currentTime -= tasks[i][0];

            if (currentTime < 0) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(currentTime);
    }
}