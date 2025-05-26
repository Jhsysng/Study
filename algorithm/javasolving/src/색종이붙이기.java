import java.io.*;
import java.util.*;

public class 색종이붙이기 {
    static int[][] board = new int[10][10];
    static int[] paperCount = new int[6];
    static int minPapers = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 10; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtrack(0, 0, 0);

        System.out.println(minPapers == Integer.MAX_VALUE ? -1 : minPapers);
    }

    static void backtrack(int row, int col, int usedPapers){
        if(usedPapers >= minPapers) return;

        int[] next = findNext1(row, col);

        if(next == null){
            minPapers = Math.min(minPapers, usedPapers);
            return;
        }
    }

    static int[] findNext1(int startRow, int startCol){
        for(int i = startRow; i < 10; i++){
            for(int j=(i==startRow? startCol : 0); j< 10; j++){
                if(board[i][j] == 1){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
