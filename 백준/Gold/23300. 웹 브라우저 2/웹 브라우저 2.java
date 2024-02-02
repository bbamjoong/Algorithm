import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static final String BACK = "B";
    static final String FRONT = "F";
    static final String ACCESS = "A";
    static final String COMPRESSION = "C";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        ArrayDeque<Integer> back = new ArrayDeque<>();
        ArrayDeque<Integer> front = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int now = 0;

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals(BACK)) {
                if (back.isEmpty()) {
                    continue;
                }
                front.addFirst(now);
                now = back.pollLast();
            } else if (command.equals(FRONT)) {
                if (front.isEmpty()) {
                    continue;
                }
                back.addLast(now);
                now = front.poll();
            } else if (command.equals(ACCESS)) {
                int num = Integer.parseInt(st.nextToken());
                front.clear();
                if (now != 0) {
                    back.addLast(now);
                }
                now = num;
            } else if (command.equals(COMPRESSION)) {
                ArrayDeque<Integer> tmp = new ArrayDeque<>();

                while (!back.isEmpty()) {
                    int num = back.pollLast();
                    tmp.addFirst(num);
                    while (!back.isEmpty() && (back.peekLast() == num)) {
                        back.pollLast();
                    }
                }
                back = tmp;
            }
        }
        sb.append(now).append("\n");

        if (back.isEmpty()) {
            sb.append(-1).append("\n");
        } else {
            while (!back.isEmpty()) {
                sb.append(back.pollLast()).append(" ");
            }
            sb.append("\n");
        }

        if (front.isEmpty()) {
            sb.append(-1).append("\n");
        } else {
            for (Integer i : front) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}