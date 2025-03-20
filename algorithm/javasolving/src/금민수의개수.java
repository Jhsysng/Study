import java.io.*;
import java.util.*;

public class 금민수의개수 {
   public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       
       long A = Long.parseLong(st.nextToken());
       long B = Long.parseLong(st.nextToken());
       
       List<Long> guemMinSu = new ArrayList<>();
       generateGuemMinSu(guemMinSu, 0, 10, B);
       
       int count = 0;
       for (long num : guemMinSu) {
           if (num >= A && num <= B) {
               count++;
           }
       }
       
       System.out.println(count);
   }
   
   private static void generateGuemMinSu(List<Long> list, long num, long digit, long limit) {
       if (num > limit) {
           return;
       }
       
       if (num > 0) {
           list.add(num);
       }
       
       if (num * 10 + 4 <= limit) {
           generateGuemMinSu(list, num * 10 + 4, digit * 10, limit);
       }
       
       if (num * 10 + 7 <= limit) {
           generateGuemMinSu(list, num * 10 + 7, digit * 10, limit);
       }
   }
}