import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 문자열폭발 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bomb = br.readLine();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<str.length(); i++){
            char c = str.charAt(i);
            sb.append(c);

            if(sb.length()>= bomb.length()){
                boolean isBomb = true;
                for(int j = 0; j<bomb.length(); j++){
                    if(sb.charAt(sb.length() - bomb.length()+j) != bomb.charAt(j)){
                        isBomb=false;
                        break;
                    }
                }
                if(isBomb){
                    sb.delete(sb.length() - bomb.length(), sb.length());
                }
            }



        }

        if(sb.length()==0){
            System.out.println("FRULA");
        }else {
            System.out.println(sb.toString());
        }
    } 
}
