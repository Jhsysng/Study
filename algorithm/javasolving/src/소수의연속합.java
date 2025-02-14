import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 소수의연속합 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> primes = new ArrayList<>();
        boolean[] isComposite = new boolean[n + 1];

        for (int i = 2; i * i <= n; i++) {
            if (!isComposite[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isComposite[j] = true;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (!isComposite[i]) {
                primes.add(i);
            }
        }

        int count = 0;
        int start = 0;
        int end = 0;
        int sum = 0;
        int size = primes.size();

        while (start < size) {
            while (end < size && sum < n) {
                sum += primes.get(end++);
            }

            if (sum == n) count++;

            sum -= primes.get(start++);
        }

        System.out.println(count);
    }
}