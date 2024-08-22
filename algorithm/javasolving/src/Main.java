import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 입력 줄에서 숫자 개수를 읽음 (사용하지 않음)
        int n = Integer.parseInt(reader.readLine());

        // 두 번째 입력 줄에서 숫자들을 공백으로 구분하여 읽음
        String[] tokens = reader.readLine().split(" ");

        // 숫자들을 정렬
        Arrays.sort(tokens, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                // 두 숫자를 이어붙였을 때 더 큰 수가 되는 순서로 정렬
                return (b + a).compareTo(a + b); // 내림차순 정렬
            }
        });

        // 정렬된 숫자들을 하나의 문자열로 합침
        StringBuilder answer = new StringBuilder();
        for (String token : tokens) {
            answer.append(token);
        }

        // 가장 큰 숫자를 문자열로 출력, 앞자리가 '0'인 경우 '0'만 출력
        String result = answer.toString();
        if (result.startsWith("0")) {
            System.out.println("0");
        } else {
            System.out.println(result);
        }
    }
}