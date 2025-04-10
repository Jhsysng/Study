import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 무한수열 {
    static Map<Long, Long> memo = new HashMap<>();
    static long P, Q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        long N = Long.parseLong(input[0]);

        P = Long.parseLong(input[1]);
        Q = Long.parseLong(input[2]);

        System.out.println(getA(N));
    }

    static long getA(long n){
        if(n==0) return 1;

        if(memo.containsKey(n)){
            return memo.get(n);
        }

        long result = getA(n/P) + getA(n/Q);

        memo.put(n, result);

        return result;
    }
}
