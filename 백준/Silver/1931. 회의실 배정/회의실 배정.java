import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static class Meeting implements Comparable<Meeting> {
        long start;
        long end;

        public Meeting(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting m) {
            if (this.end == m.end) {
                return (int) (this.start - m.start);
            }
            return (int) (this.end - m.end);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        LinkedList<Meeting> meetings = new LinkedList<>();

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());
            meetings.add(new Meeting(start, end));
        }

        Collections.sort(meetings);

        int cnt = 0;

        long end = 0;
        for (Meeting meeting : meetings) {
            if (meeting.start >= end) {
                end = meeting.end;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}