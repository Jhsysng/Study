import java.io.*;
import java.util.*;
public class 철길 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] people = new int[n][2];

        for(int i = 0; i< n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            people[i][0] = Integer.parseInt(st.nextToken());
            people[i][1] = Integer.parseInt(st.nextToken());
        }

        int d = Integer.parseInt(br.readLine().trim());
        List<Event> events = new ArrayList<>();

        for(int i = 0; i < n; i++){
            int h = people[i][0];
            int o = people[i][1];

            int min = Math.min(h, o);
            int max = Math.max(h, o);

            if(max - min <= d){
                events.add(new Event(max - d, 1));
                events.add(new Event(min, -1));
            }
        }

        Collections.sort(events);

        int count = 0;
        int maxCount = 0;

        for(Event event:events){
            count+= event.type;
            maxCount = Math.max(maxCount, count);
        }

        System.out.println(maxCount);
        
    }

    static class Event implements Comparable<Event>{
        int position;
        int type;

        Event(int position, int type){
            this.position = position;
            this.type = type;
        }

        @Override
        public int compareTo(Event other){
            if(this.position!=other.position){
                return Integer.compare(this.position, other.position);
            }

            return -Integer.compare(this.type, other.type);
        }
    }
}
