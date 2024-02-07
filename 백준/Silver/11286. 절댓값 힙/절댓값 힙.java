import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] - o2[0] == 0) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                if (heap.isEmpty()) {
                    sb.append(0).append("\n");
                    continue;
                }
                sb.append(heap.poll()[1]).append("\n");
            } else {
                heap.add(new int[]{Math.abs(x), x});
            }
        }
        System.out.println(sb);
    }
}