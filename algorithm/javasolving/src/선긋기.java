public class 선긋기 {
    static class Line implements Comparable<Line>{
        int start, end;

        Line(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line other){
            return Integer.compare(this.start, other.start);
        }
    }


}
