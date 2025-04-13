import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 킥다운 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String gear1 = br.readLine();
        String gear2 = br.readLine();
        
        int minWidth = Integer.MAX_VALUE;
        
        // 첫 번째 방법: gear1을 왼쪽에 고정하고 gear2를 오른쪽으로 이동
        for (int offset = -gear2.length() + 1; offset < gear1.length(); offset++) {
            if (canFit(gear1, gear2, offset)) {
                int width = Math.max(gear1.length(), offset + gear2.length()) - Math.min(0, offset);
                minWidth = Math.min(minWidth, width);
            }
        }
        
        // 두 번째 방법: gear2를 왼쪽에 고정하고 gear1을 오른쪽으로 이동
        for (int offset = -gear1.length() + 1; offset < gear2.length(); offset++) {
            if (canFit(gear2, gear1, offset)) {
                int width = Math.max(gear2.length(), offset + gear1.length()) - Math.min(0, offset);
                minWidth = Math.min(minWidth, width);
            }
        }
        
        System.out.println(minWidth);
    }
    
    // leftGear는 왼쪽에 고정, rightGear는 오른쪽으로 offset만큼 이동했을 때 맞물리는지 확인
    private static boolean canFit(String leftGear, String rightGear, int offset) {
        int overlapStart = Math.max(0, offset);
        int overlapEnd = Math.min(leftGear.length(), offset + rightGear.length());
        
        for (int i = overlapStart; i < overlapEnd; i++) {
            char leftChar = leftGear.charAt(i);
            char rightChar = rightGear.charAt(i - offset);
            
            // 두 기어가 모두 이(2)인 경우 맞물릴 수 없음
            if (leftChar == '2' && rightChar == '2') {
                return false;
            }
        }
        
        return true;
    }
}