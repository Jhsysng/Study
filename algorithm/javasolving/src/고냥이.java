import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

//16472번 고냥이
public class 고냥이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1 - 26
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        Map<Character, Integer> map = new HashMap<>();

        int front = 0;
        int back = 0;
        int answer = 0;

        while (back < str.length()) {
            char c = str.charAt(back);
            map.put(c, map.getOrDefault(c, 0) + 1);
            back++;

            while (map.size() > N) {
                char f = str.charAt(front);
                map.put(f, map.get(f) - 1);
                if (map.get(f) == 0) {
                    map.remove(f);
                }
                front++;
            }

            answer = Math.max(answer, back - front);
        }

        System.out.println(answer);
    }
}