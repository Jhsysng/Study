import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 문자열게임2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int t = 0;
        Map<Character, Integer> max = new HashMap<>();
        Map<Character, Integer> min = new HashMap<>();

        while(t<T){
            t++;
            String str = br.readLine().trim();
            int gap = Integer.parseInt(br.readLine());
            
            for(int i = 0; i<str.length(); i++){
                Character tmp = str.charAt(i);
                int minP = min.getOrDefault(tmp, str.length());
                if(minP>i){
                    min.put(tmp, Integer.valueOf(i));
                }

                int maxP = max.getOrDefault(tmp, 0);
                if(maxP<i){
                    max.put(tmp, Integer.valueOf(i));
                }
            }

            for(int i =0; i<26; i++){
                Character a = 'a';
                int minX = min.getOrDefault(a, -1);
                int maxX = max.getOrDefault(a, -1);
                a++;
                if(minX==-1){
                    continue;
                }
                if((maxX-minX-1)<gap){
                    continue;
                }
                

            }
        }
    }
}
