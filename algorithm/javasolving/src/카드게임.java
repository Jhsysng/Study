import java.io.BufferedReader;
import java.io.InputStreamReader;

//11062
public class 카드게임 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int testCase = 0;
        while(testCase<T){
            int n = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            int[] cards = new int[n];
            for(int i = 0; i<n; i++){
                cards[i] = Integer.parseInt(input[i]);
            }
            int[] dp = new int[n];

        }
    }
}
