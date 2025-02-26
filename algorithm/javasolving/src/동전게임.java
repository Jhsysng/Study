import java.io.*;
import java.util.*;
class 동전게임 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++){
            char[][] board =new char[3][3];
            for(int i = 0; i<3; i++){
                StringTokenizer st= new StringTokenizer(br.readLine());
                for(int j = 0; j<3; j++){
                    board[i][j] = st.nextToken().charAt(0);
                }
            }
            System.out.println(solve(board));
        }
    }

    static int solve(char[][] board){
        int minOps = Integer.MAX_VALUE;

        for(int mask = 0 ; mask < (1<<8); mask++){
            char[][] temp = new char[3][3];
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    temp[i][j] = board[i][j];
                }
            }

            int ops = 0;

            for(int i = 0; i<3; i++){
                if((mask & (1 << i ))!=0){
                    ops++;
                    for(int j =0; j<3; j++){
                        temp[i][j] = temp[i][j] == 'H' ? 'T': 'H';
                    }
                }
            }

            for(int j = 0; j<3; j++){
                if((mask & (1<<j+3))!=0){
                    ops++;
                    for(int i = 0; i< 3; i++){
                        temp[i][j] = temp[i][j] == 'H' ? 'T' : 'H';
                    }
                }
            }

            if((mask & (1 << 6))!=0){
                ops++;
                for(int i = 0; i< 3; i++){
                    temp[i][i] = temp [i][i] == 'H' ? 'T' :'H';
                }
            }

            if((mask&(1<<7))!=0){
                ops++;
                for(int i = 0; i<3; i++){
                    temp[i][2-i] = temp[i][2-i] == 'H' ? 'T' : 'H';
                }
            }

            boolean allSame = true;
            char first = temp[0][0];

            for(int i =0; i<3 && allSame; i++){
                for(int j =0; j<3 && allSame; j++){
                    if(temp[i][j] != first){
                        allSame = false;
                    }
                }
            }

            if (allSame && ops < minOps){
                minOps = ops;
            }

            return minOps == Integer.MAX_VALUE ? -1 : minOps;
        }
        return minOps;
    }
}