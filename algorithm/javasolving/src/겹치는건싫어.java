import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 겹치는건싫어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] freq = new int[100001];
        int l = 0;
        int maxLength = 0;
        for (int r = 0; r < N; r++) {
            int current = arr[r];
            freq[current]++;
            while (freq[current] > K) {
                freq[arr[l]]--;
                l++;
            }
            maxLength = Math.max(maxLength, r - l + 1);
        }
        System.out.println(maxLength);
    }
}
