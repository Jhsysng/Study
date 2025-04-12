import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 회전하는큐 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[] targets = new int[M];
        input = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            targets[i] = Integer.parseInt(input[i]);
        }

        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            deque.add(i);
        }

        int count = 0;

        for (int i = 0; i < M; i++) {
            int target = targets[i];
            int targetIdx = 0;

            for (int j = 0; j < deque.size(); j++) {
                if (deque.get(j) == target) {
                    targetIdx = j;
                    break;
                }
            }

            if (targetIdx <= deque.size() / 2) {
                for (int j = 0; j < targetIdx; j++) {
                    deque.addLast(deque.removeFirst()); 
                    count++;
                }
            } else {
                for (int j = 0; j < deque.size() - targetIdx; j++) {
                    deque.addFirst(deque.removeLast());
                    count++;
                }
            }

            // 1번 연산: 첫 번째 원소를 뽑아냄
            deque.removeFirst();
        }

        System.out.println(count);
    }
}