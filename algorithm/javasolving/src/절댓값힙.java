import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 절댓값힙 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->Math.abs(a) == Math.abs(b) ? a - b : Math.abs(a) - Math.abs(b));
        List<Integer> answer = new ArrayList<>();
        for(int i = 0; i<N; i++){
            int x = Integer.parseInt(br.readLine());
            if(x==0){
                if(pq.isEmpty()){
                    if(!answer.isEmpty()&&answer.get(answer.size()-1)==0){
                        continue;
                    }
                    answer.add(0);
                }else{
                    answer.add(pq.remove());
                }
            }else{
                pq.offer(x);
            }
        }

        for(int ans:answer){
            System.out.println(ans);
        }
        
    }
}
