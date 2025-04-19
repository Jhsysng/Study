import java.util.*;
import java.io.*;

public class 암호만들기 {
    static char[] alphabet;
    static char[] password;
    static int L, C;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        alphabet = new char[C];
        password = new char[L];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alphabet[i] = st.nextToken().charAt(0);
        }
        
        Arrays.sort(alphabet);
        makePassword(0, 0);
        
        br.close();
    }
    
    static void makePassword(int idx, int depth) {
        if (depth == L) {
            if (isValidPassword()) {
                System.out.println(new String(password));
            }
            return;
        }
        
        for (int i = idx; i < C; i++) {
            password[depth] = alphabet[i];
            makePassword(i + 1, depth + 1);
        }
    }
    
    static boolean isValidPassword() {
        int vowels = 0;
        int consonants = 0;
        
        for (int i = 0; i < L; i++) {
            if (isVowel(password[i])) {
                vowels++;
            } else {
                consonants++;
            }
        }
        
        return vowels >= 1 && consonants >= 2;
    }
    
    static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}