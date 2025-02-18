import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 순열장난 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        List<Integer> result = restorePermutation(input);

        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            sb.append(num).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    public static List<Integer> restorePermutation(String input) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> used = new HashSet<>();

        dfs(input, 0, result, used);

        return result;
    }

    private static boolean dfs(String input, int index, List<Integer> result, Set<Integer> used) {
        if (index == input.length()) {
            return true;
        }

        for (int len = 1; len <= Math.min(5, input.length() - index); len++) {
            if (input.charAt(index) == '0') {
                continue;
            }

            String numStr = input.substring(index, index + len);
            int num = Integer.parseInt(numStr);

            if (num >= 1 && !used.contains(num)) {
                result.add(num);
                used.add(num);

                if (dfs(input, index + len, result, used)) {
                    return true;
                }

                result.remove(result.size() - 1);
                used.remove(num);
            }
        }

        return false;
    }
}