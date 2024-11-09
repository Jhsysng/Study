import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 색종이만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

         int n = Integer.parseInt(br.readLine());
         int[][] matrix = new int[n][n];
         for(int i = 0 ; i<n; i++){
             String[] tmp = br.readLine().split(" ");
             for(int j = 0; j < tmp.length; j++){
                 matrix[i][j] = Integer.parseInt(tmp[j]);
             }
         }
         int ans = 0;
         boolean result = false;
         while(result){
             if (checkColor(matrix)) {
                 ans++;
             }else {

             }
         }


    }

    private static boolean checkColor(int[][] arr){
        int size = arr[0].length;
        int and = arr[0][0];
        int or = arr[0][0];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                int tmp = arr[i][j];
                and = and&tmp;
                or = or|tmp;
            }
            if (and != or){
                return false;
            }
        }
        return true;
    }
}
