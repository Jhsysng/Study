import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class 부동산다툼 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        
        HashSet<Integer> occupied = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < Q; i++) {
            int desiredLand = Integer.parseInt(br.readLine());
            
            ArrayList<Integer> path = getPath(desiredLand);
            
            boolean canOccupy = true;
            int blockedBy = 0;
            
            for (int land : path) {
                if (occupied.contains(land)) {
                    canOccupy = false;
                    blockedBy = land;
                    break;
                }
            }
            
            if (canOccupy) {
                occupied.add(desiredLand);
                sb.append("0\n");
            } else {
                sb.append(blockedBy).append("\n");
            }
        }
        
        System.out.print(sb);
    }
    
    private static ArrayList<Integer> getPath(int land) {
        ArrayList<Integer> path = new ArrayList<>();
        
        while (land >= 1) {
            path.add(land);
            land /= 2;
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = path.size() - 1; i >= 0; i--) {
            result.add(path.get(i));
        }
        
        return result;
    }
}