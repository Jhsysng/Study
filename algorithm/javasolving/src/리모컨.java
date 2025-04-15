import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class 리모컨 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        boolean[] broken = new boolean[10];
        
        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int button = Integer.parseInt(st.nextToken());
                broken[button] = true;
            }
        }
        
        int minPresses = Math.abs(N - 100);
        for (int i = 0; i <= 1000000; i++) {
            int channel = i;
            int length = isPossible(channel, broken);
            
            if (length > 0) {
                int presses = length + Math.abs(channel - N);
                minPresses = Math.min(minPresses, presses);
            }
        }
        
        System.out.println(minPresses);
    }
    
    private static int isPossible(int channel, boolean[] broken) {
        if (channel == 0) {
            return broken[0] ? 0 : 1;
        }
        
        int length = 0;
        
        while (channel > 0) {
            int digit = channel % 10;

            if (broken[digit]) {
                return 0;
            }
            
            length++;
            channel /= 10;
        }
        
        return length;
    }
}