import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Queue;

public class DNA {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        String[] input1 = reader.readLine().split(" ");
        int p = Integer.parseInt(input1[0]);  // 전체 문자열 길이
        int s = Integer.parseInt(input1[1]);  // 부분 문자열 길이

        String password = reader.readLine();

        String[] agctString = reader.readLine().split(" ");
        int[] agct = new int[4];
        for(int i = 0; i<4; i++){
            agct[i] = Integer.parseInt(agctString[i]);
        }

        Window sWindow = new Window();

        // 첫 윈도우 설정
        for(int i = 0; i < s; i++){
            sWindow.plusChar(password.charAt(i));
        }
        if(sWindow.chekcAGCT(agct)){
            answer++;
        }

        // 슬라이딩 윈도우 이동
        for(int i = 0; i < p-s; i++) {   // 여기를 수정: p-s까지만
            sWindow.minusChar(password.charAt(i));
            sWindow.plusChar(password.charAt(i+s));
            if(sWindow.chekcAGCT(agct)){
                answer++;
            }
        }

        System.out.println(answer);
    }
    // Window 클래스는 동일

    public static class Window{
        // Window 클래스 내용은 동일
        int a;
        int g;
        int c;
        int t;

        Window(){
            this.a = 0;
            this.g = 0;
            this.c = 0;
            this.t = 0;
        }

        public void plusChar(char input){
            if(input=='A'){
                a++;
            }else if (input =='G'){
                g++;
            }else if(input =='C'){
                c++;
            }else{
                t++;
            }
        }

        public void minusChar(char input){
            if(input=='A'){
                a--;
            }else if (input =='G'){
                g--;
            }else if(input =='C'){
                c--;
            }else{
                t--;
            }
        }

        public boolean chekcAGCT(int[] agct){
            boolean res = true;
            res = a>=agct[0] && g>=agct[1] && c>=agct[2] && t >= agct[3];

            return res;
        }
    }
}