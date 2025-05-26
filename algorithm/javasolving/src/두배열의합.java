import java.util.*;
import java.io.*;
public class 두배열의합 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
    
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
    
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m ; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }
        Map<Integer, Integer> sumCountA = new HashMap<>();
        for(int i = 0; i<n;i++){
            int sum = 0;
            for(int j = i; j < n; j++){
                sum += A[j];
                sumCountA.put(sum, sumCountA.getOrDefault(sum, 0) + 1);
            }
        }

        Map<Integer, Integer> sumCountB = new HashMap<>();
        for(int i = 0; i < m; i++){
            int sum = 0;
            for(int j = i; j < m; j++){
                sum += B[j];
                sumCountB.put(sum, sumCountB.getOrDefault(sum, 0)+1);
            }
        }

        long answer = 0;
        
        for(Map.Entry<Integer, Integer> entry: sumCountA.entrySet()){
            int sumA = entry.getKey();
            int countA= entry.getValue();

            int needB = T - sumA;
            if(sumCountB.containsKey(needB)){
                int countB = sumCountB.get(needB);
                answer += (long) countA*countB;
            }
        }

        System.out.println(answer);
    }



}
