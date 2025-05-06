import java.io.*;
import java.util.*;
public class 선분위의점 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] points = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i< n; i++){
            points[i] = Integer.parseInt(st.nextToken());
        }


        Arrays.sort(points);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < m ; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer. parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // start 이상인 첫 번째 점의 인덱스
            int leftIdx = lowerBound(points, start);
            // end 이하인 마지막 점의 다음 인덱스
            int rightIdx = upperBound(points, end);
            
            // 선분 위의 점 개수
            int count = rightIdx - leftIdx;
            sb.append(count).append('\n');

        }

        System.out.print(sb);
    }

    static int lowerBound(int[] arr, int value){
        int left = 0;
        int right =  arr.length;

        while(left<right){
            int mid = (left + right) / 2;
            if(arr[mid] <value){
                left = mid + 1;
            } else{
                right = mid;
            }
        }

        return left;
    }

    static int upperBound(int[] arr, int value){
        int left = 0;
        int right = arr.length;

        while(left<right){
            int mid = (left + right) /2 ;
            if (arr[mid] <= value){
                left = mid + 1;
            }else {
                right = mid;
            }
        }

        return left;
    }
}
