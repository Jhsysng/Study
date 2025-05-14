import java.io.*;
import java.util.*;

public class 선분위선분 {
    static class Event implements Comparable<Event> {
        int pos;    // 이벤트 위치
        int type;   // 이벤트 타입: 1(시작점), -1(끝점)
        
        Event(int pos, int type) {
            this.pos = pos;
            this.type = type;
        }
        
        @Override
        public int compareTo(Event o) {
            if (this.pos == o.pos) {
                // 같은 위치일 때 끝점 이벤트가 시작점 이벤트보다 먼저 오도록
                return Integer.compare(this.type, o.type);
            }
            return Integer.compare(this.pos, o.pos);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        List<Event> events = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            
            events.add(new Event(s, 1));
            events.add(new Event(e, -1));
        }
        
        Collections.sort(events);
        
        int activeSegments = 0;
        int maxOverlap = 0;
        
        for (Event event : events) {
            activeSegments += event.type;
            maxOverlap = Math.max(maxOverlap, activeSegments);
        }
        
        System.out.println(maxOverlap);
    }
}