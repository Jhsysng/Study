import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 제로 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stacks = new Stack<>();

        for(int i = 0; i<N;i++){
            int tmp = Integer.parseInt(br.readLine());
            if(tmp == 0){
                if(stacks.size()>=0)
                    stacks.pop();
            }
            stacks.push(tmp);
        }
        int answer = 0;

        for(int i = 0; i<stacks.size(); i++){
            answer += stacks.pop();
        }

        System.out.println(answer);
    }
}
