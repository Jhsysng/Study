import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 수이어쓰기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long length = 0;
        long start = 1;
        long digitLength = 1;

        while(start <= N){
            long end = Math.min(N, start * 10 -1);
            long count = end - start +1;
            long rangeLength = count * digitLength;

            if(length + rangeLength >= k){
                long position = k - length - 1;
                long targetNum = start + position / digitLength;
                int digitPosition = (int)(position%digitLength);

                String targetNumStr = String.valueOf(targetNum);
                System.out.println(targetNumStr.charAt(digitPosition) - '0');
                return;
            }
            length += rangeLength;
            start *= 10;
            digitLength++;

        }
    }
}
