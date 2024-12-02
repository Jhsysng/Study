import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 바이러스 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int com = Integer.parseInt(br.readLine());
        int[][] graph = new int[com+1][com+1];
        boolean[] visited = new boolean[com+1]; // 2차원이 아닌 1차원 배열로 수정

        int edge = Integer.parseInt(br.readLine());
        for(int i = 0; i<edge; i++){
            int[] numbers = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[numbers[0]][numbers[1]] = 1;
            graph[numbers[1]][numbers[0]] = 1;
        }

        int count = 0; // 감염된 컴퓨터 수를 세기 위한 변수
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true; // 시작 노드 방문 처리

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int i = 1; i <= com; i++) {
                if(graph[current][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                    count++; // 새로운 컴퓨터가 감염될 때마다 카운트 증가
                }
            }
        }

        System.out.println(count);
    }
}