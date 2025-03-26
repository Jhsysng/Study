import java.io.*;
import java.util.*;

public class 탑보기 {
    static class Building {
        int idx, height;
        
        public Building(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 건물 정보 저장
        Building[] buildings = new Building[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = new Building(i + 1, Integer.parseInt(st.nextToken()));
        }
        
        int[] count = new int[N + 1];
        int[] closest = new int[N + 1];
        Arrays.fill(closest, Integer.MAX_VALUE);
        
        Stack<Building> stack = new Stack<>();
        
        for (int i = 0; i < N; i++) {
            processBuilding(stack, count, closest, buildings[i]);
        }
        
        stack.clear();
        
        for (int i = N - 1; i >= 0; i--) {
            processBuilding(stack, count, closest, buildings[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(count[i]);
            if (count[i] > 0) {
                sb.append(" ").append(closest[i]);
            }
            sb.append("\n");
        }
        
        System.out.print(sb);
    }
    
    private static void processBuilding(Stack<Building> stack, int[] count, int[] closest, Building current) {
        while (!stack.isEmpty() && stack.peek().height <= current.height) {
            stack.pop();
        }
        
        count[current.idx] += stack.size();
        
        if (!stack.isEmpty()) {
            int distance = Math.abs(stack.peek().idx - current.idx);
            int prevDistance = Math.abs(closest[current.idx] - current.idx);
            
            if (distance < prevDistance) {
                closest[current.idx] = stack.peek().idx;
            } else if (distance == prevDistance) {
                closest[current.idx] = Math.min(closest[current.idx], stack.peek().idx);
            }
        }
        
        stack.push(current);
    }
}