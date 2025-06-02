import java.io.*;
import java.util.*;
public class 이모티콘 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());


        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[2001][2001];

        queue.offer(new int[]{1, 0, 0});
        visited[1][0] = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int screen = current[0];
            int clipboard = current[1];
            int time = current[2];

            if(screen == S){
                System.out.println(time);
                return;
            }

            if(!visited[screen][screen]){
                visited[screen][screen] = true;
                queue.offer(new int[]{screen, screen, time+1});
            }

            if(clipboard > 0 && screen + clipboard <= 2000 && !visited[screen+clipboard][clipboard]){
                visited[screen + clipboard][clipboard] =true;
                queue.offer(new int[]{screen+clipboard, clipboard, time+1});
            }

            if(screen > 0 && !visited[screen -1][clipboard]){
                visited[screen -1][clipboard] = true;
                queue.offer(new int[]{screen - 1, clipboard, time + 1});
            }
        }

    }
    
}
