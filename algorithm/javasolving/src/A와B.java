import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A와B {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String start = br.readLine().trim();
        String end = br.readLine().trim();

        int sLen = start.length();
        int eLen = start.length();
        int answer = 0;

    }

    static String addA(String init){
        StringBuffer sb = new StringBuffer(init);
        sb.append("A");

        return sb.toString();

    }

    static String addB(String init){
        StringBuffer sb = new StringBuffer(init);
        sb.reverse();
        sb.append("B");

        return sb.toString();
    }
}
