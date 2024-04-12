import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine()); // 어차피 얘 안씀

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(st.nextToken()));
        }

        long ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
            sb.append(ans).append("\n");
        }
        System.out.printf(sb.toString());
    }
}