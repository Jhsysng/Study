import java.io.*;
import java.util.*;

public class 괄호의값 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        
        Stack<Character> stack = new Stack<>();
        Stack<Integer> valueStack = new Stack<>();
        
        boolean isValid = true;
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            
            if (c == '(' || c == '[') {
                // 여는 괄호는 스택에 넣기
                stack.push(c);
                valueStack.push(-1); // 임시 값으로 -1 저장
            } else if (c == ')') {
                // 닫는 소괄호인 경우
                
                // 스택이 비어있거나 짝이 맞지 않는 경우
                if (stack.isEmpty() || stack.peek() != '(') {
                    isValid = false;
                    break;
                }
                
                stack.pop(); // 여는 괄호 제거
                
                int value = 0;
                // 괄호 안의 값 계산
                while (!valueStack.isEmpty() && valueStack.peek() != -1) {
                    value += valueStack.pop();
                }
                
                // 여는 괄호에 해당하는 -1 제거
                if (!valueStack.isEmpty()) {
                    valueStack.pop();
                }
                
                // 값이 없는 경우 (바로 닫는 경우) 값은 2
                if (value == 0) {
                    valueStack.push(2);
                } else {
                    valueStack.push(2 * value); // 괄호 안의 값에 2를 곱함
                }
            } else if (c == ']') {
                // 닫는 대괄호인 경우
                
                // 스택이 비어있거나 짝이 맞지 않는 경우
                if (stack.isEmpty() || stack.peek() != '[') {
                    isValid = false;
                    break;
                }
                
                stack.pop(); // 여는 괄호 제거
                
                int value = 0;
                // 괄호 안의 값 계산
                while (!valueStack.isEmpty() && valueStack.peek() != -1) {
                    value += valueStack.pop();
                }
                
                // 여는 괄호에 해당하는 -1 제거
                if (!valueStack.isEmpty()) {
                    valueStack.pop();
                }
                
                // 값이 없는 경우 (바로 닫는 경우) 값은 3
                if (value == 0) {
                    valueStack.push(3);
                } else {
                    valueStack.push(3 * value);
                }
            } else {
                // 괄호 이외의 문자가 있는 경우
                isValid = false;
                break;
            }
        }
        
        // 최종 결과 계산
        int result = 0;
        while (!valueStack.isEmpty()) {
            result += valueStack.pop();
        }
        
        // 괄호 스택이 비어있지 않으면 불완전한 괄호열
        if (!stack.isEmpty() || !isValid) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }
    }
}