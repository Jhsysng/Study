import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 스타트링크 {
    static int N;
    static int[][] map;
    static boolean[] selected;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        selected = new boolean[N];

        for(int i=0; i<N; i++) {
            String[] line = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        combination(0, 0);
        System.out.println(min);
    }

    static void combination(int idx, int count) {
        if(count == N/2) {
            calculateDifference();
            return;
        }

        for(int i=idx; i<N; i++) {
            if(!selected[i]) {
                selected[i] = true;
                combination(i+1, count+1);
                selected[i] = false;
            }
        }
    }

    static void calculateDifference() {
        int startTeam = 0;
        int linkTeam = 0;

        for(int i=0; i<N-1; i++) {
            for(int j=i+1; j<N; j++) {
                if(selected[i] && selected[j]) {
                    startTeam += map[i][j] + map[j][i];
                }
                else if(!selected[i] && !selected[j]) {
                    linkTeam += map[i][j] + map[j][i];
                }
            }
        }

        min = Math.min(min, Math.abs(startTeam - linkTeam));
    }
}