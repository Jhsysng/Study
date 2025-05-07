import java.io.*;
import java.util.*;

public class 가르침 {
    static int N, K;
    static int[] words;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(K<5){
            System.out.println(0);
            return;
        }else if (K==26){
            System.out.println(N);
            return;
        }

        words = new int[N];

        for(int i = 0; i<N;i++){
            String word = br.readLine();
            int bit = 0;
            for(char c: word.toCharArray()) {
                bit |= (1<<(c-'a'));
            }
            words[i] = bit;

        }  

        int learned = 0;
        learned |= (1 << ('a' - 'a'));
        learned |= (1 << ('n' - 'a'));
        learned |= (1 << ('t' - 'a'));
        learned |= (1 << ('i' - 'a'));
        learned |= (1 << ('c' - 'a'));
        
        // 백트래킹으로 나머지 K-5개의 알파벳을 선택
        backtrack(0, 5, learned);
        
        System.out.println(max);

    }
    static void backtrack(int index, int count, int learned){
        if(count == K || index == 26){
            if(count == K ){
                int readable = 0;
                for(int word: words){
                    if((word&~learned)== 0){
                        readable++;
                    }
                }
                max = Math.max(max, readable);
            }
            return;
        }

        if((learned & (1<<index))!= 0){
            backtrack(index+1, count, learned);
            return;
        }

        if(count < K){
            backtrack(index + 1, count + 1, learned | (1<<index));
        }

        backtrack(index+1, count, learned);
    }
}
