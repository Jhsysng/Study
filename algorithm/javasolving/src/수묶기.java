import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 수묶기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> positives = new ArrayList<>();
        ArrayList<Integer> negatives = new ArrayList<>();

        int zero_count = 0;
        int one_count = 0;

        for(int i = 0; i<N;i++){
            int num =Integer.parseInt(br.readLine());
            if(num>1){
                positives.add(num);
            }else if (num < 0){
                negatives.add(num);
            }else if(num==0){
                zero_count++;
            } else{
                one_count++;
            }
        }


        Collections.sort(positives, Collections.reverseOrder());
        Collections.sort(negatives);

        int sum = 0;

        for(int i = 0; i<positives.size(); i+=2){
            if(i+1<positives.size()){
                sum+= positives.get(i)*positives.get(i+1);
            }else{
                sum+=positives.get(i);
            }
        }

        for(int i = 0; i<negatives.size(); i+=2){
            if(i+1<negatives.size()){
                sum+=negatives.get(i)*negatives.get(i+1);
            }else{
                if(zero_count>0){
                    zero_count--;
                }else{
                    sum+= negatives.get(i);
                }
            }
        }

        sum += one_count;
        
        System.out.println(sum);
    }
    
}
