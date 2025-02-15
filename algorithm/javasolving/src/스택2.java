import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class 스택2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> dq = new LinkedList<>();

        for(int i = 0; i<n;i++){
            String[] input = br.readLine().split(" ");
            int[] tmp = new int[input.length];
            for(int j = 0; j<input.length; j++){
                tmp[j] = Integer.parseInt(input[j]);
            }

            if(tmp[0] == 1){
                dq.addLast(tmp[1]);
            }else if(tmp[0] == 2) {
                if (dq.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(dq.pollLast());
                }
            }else if (tmp[0] == 3){
                System.out.println(dq.size());
            }else if (tmp[0] == 4) {
                if (dq.isEmpty()) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            }else if (tmp[0] == 5) {
                if (dq.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(dq.peekLast());
                }
            }
        }
    }
}
