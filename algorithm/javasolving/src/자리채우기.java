import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 자리채우기 {
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int[] m = new int[2];
        for(int i = 0 ; i< 2; i++){
            m[i] = Integer.parseInt(s[i]);
        }

        int k = Integer.parseInt(br.readLine());

        if(m[0]*m[1] < k){
            System.out.println(0);
            return;
        }

        int[][] matrix = new int[m[1]][m[0]];

    }
}
