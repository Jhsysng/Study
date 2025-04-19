import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FlyMeToTheAlphaCentauri {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int distance = y - x;
            
            int count = getMinimumOperations(distance);
            
            System.out.println(count);
        }
    }
    
    private static int getMinimumOperations(int distance) {
        if (distance == 1) return 1;
        if (distance == 2) return 2;
        
        int n = (int)Math.sqrt(distance);
        
        int remaining = distance - n*n;
        
        if (remaining == 0) {
            return 2*n - 1;
        } else if (remaining <= n) {
            return 2*n;
        } else {
            return 2*n + 1;
        }
    }
}