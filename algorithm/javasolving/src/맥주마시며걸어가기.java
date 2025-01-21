import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 맥주마시며걸어가기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int test = 0;
        while(test<t){
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n+2][2];
            for(int i=0; i<n+2; i++){
                String[] input = br.readLine().split(" ");
                arr[i][0] = Integer.parseInt(input[0]);
                arr[i][1] = Integer.parseInt(input[1]);
            }
            boolean[] visited = new boolean[n+2];
            if(bfs(arr, n+1)){
                System.out.println("happy");
            }else{
                System.out.println("sad");
            }
            test++;
        }
    }

    private static boolean bfs(int[][] arr, int arrive){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[arr.length];

        queue.offer(0);  // 시작점 추가
        visited[0] = true;

        while(!queue.isEmpty()){
            int current = queue.poll();
            int x = arr[current][0];
            int y = arr[current][1];

            if(current == arrive) return true;  // 도착점에 도달

            for(int i=0; i<arr.length; i++){
                if(!visited[i]){
                    int distance = Math.abs(x-arr[i][0])+Math.abs(y-arr[i][1]);
                    if(distance <= 1000){
                        queue.offer(i);
                        visited[i] = true;
                    }
                }
            }
        }
        return false;  // 도달할 수 없음
    }
}