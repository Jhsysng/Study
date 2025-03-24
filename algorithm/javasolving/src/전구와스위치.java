import java.io.*;

class 전구와스위치{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        // Read current state of bulbs
        String currentState = br.readLine();
        int[] current = new int[n];
        for (int i = 0; i < n; i++) {
            current[i] = currentState.charAt(i) - '0';
        }
        
        // Read target state of bulbs
        String targetState = br.readLine();
        int[] target = new int[n];
        for (int i = 0; i < n; i++) {
            target[i] = targetState.charAt(i) - '0';
        }
        
        // First attempt: Start by not pressing the first switch
        int[] attempt1 = current.clone();
        int count1 = solveWithFirstSwitchState(attempt1, target, false, n);
        
        // Second attempt: Start by pressing the first switch
        int[] attempt2 = current.clone();
        int count2 = solveWithFirstSwitchState(attempt2, target, true, n);
        
        // Determine the minimum number of switch presses
        if (count1 == -1 && count2 == -1) {
            System.out.println(-1); // Both attempts failed
        } else if (count1 == -1) {
            System.out.println(count2);
        } else if (count2 == -1) {
            System.out.println(count1);
        } else {
            System.out.println(Math.min(count1, count2));
        }
    }
    
    private static int solveWithFirstSwitchState(int[] current, int[] target, boolean pressFirst, int n) {
        int count = 0;
        
        // If we decide to press the first switch
        if (pressFirst) {
            toggleBulbs(current, 0, n);
            count++;
        }
        
        // For each bulb from the 2nd to the last, check if the previous bulb matches the target
        for (int i = 1; i < n; i++) {
            // If the previous bulb doesn't match target, press the current switch
            if (current[i-1] != target[i-1]) {
                toggleBulbs(current, i, n);
                count++;
            }
        }
        
        // Check if all bulbs match the target
        for (int i = 0; i < n; i++) {
            if (current[i] != target[i]) {
                return -1; // Cannot reach target state
            }
        }
        
        return count;
    }
    
    private static void toggleBulbs(int[] state, int switchIndex, int n) {
        // Toggle previous bulb if exists
        if (switchIndex > 0) {
            state[switchIndex - 1] = 1 - state[switchIndex - 1];
        }
        
        // Toggle current bulb
        state[switchIndex] = 1 - state[switchIndex];
        
        // Toggle next bulb if exists
        if (switchIndex < n - 1) {
            state[switchIndex + 1] = 1 - state[switchIndex + 1];
        }
    }
}