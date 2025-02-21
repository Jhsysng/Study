import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 거의소수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        long a = Long.parseLong(input[0]);
        long b = Long.parseLong(input[1]);

        long sqrtB = (long) Math.sqrt(b);
        boolean[] isComposite = new boolean[(int)sqrtB + 1];
        int cnt = 0;

        for (long i = 2; i <= sqrtB; i++) {
            if (!isComposite[(int)i]) {
                for (long j = i * i; j <= sqrtB; j += i) {
                    isComposite[(int)j] = true;
                }

                long power = i;
                while (power <= b / i) {
                    power *= i;
                    if (power >= a) {
                        cnt++;
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}