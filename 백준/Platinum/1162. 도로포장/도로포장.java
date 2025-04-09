import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int k;
    static ArrayList<road>[] li;

    static class road implements Comparable<road> {
        int to;
        long weight;
        int count;

        public road(int to, long weight, int count) {
            this.to = to;
            this.weight = weight;
            this.count = count;
        }

        @Override
        public int compareTo(road o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        li = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            li[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            li[a].add(new road(b, w, 0));
            li[b].add(new road(a, w, 0));
        }
    }

    static long[][] dijkstra() {
        long[][] distance = new long[n + 1][k + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(distance[i], Long.MAX_VALUE);
        }

        distance[0][0] = 0;
        distance[1][0] = 0;

        PriorityQueue<road> pq = new PriorityQueue<>();
        pq.offer(new road(1, 0, 0));

        while (!pq.isEmpty()) {
            road cur = pq.poll();

            if (distance[cur.to][cur.count] < cur.weight) { // 이미 distance 배열이 갱신 되어 더 작으면 로직 진행 X
                continue;
            }

            for (road next : li[cur.to]) {
                // 포장을 하는 경우. (이동 비용이0이니까 count만 1 증가시켜서 체크)
                if (cur.count < k && distance[cur.to][cur.count] < distance[next.to][cur.count + 1]) {
                    distance[next.to][cur.count + 1] = distance[cur.to][cur.count];
                    pq.offer(new road(next.to, distance[next.to][cur.count + 1], cur.count + 1));
                }

                // 포장을 안하는 경우.
                if (distance[next.to][cur.count] > distance[cur.to][cur.count] + next.weight) {
                    distance[next.to][cur.count] = distance[cur.to][cur.count] + next.weight;
                    pq.offer(new road(next.to, distance[next.to][cur.count], cur.count));
                }
            }
        }

        return distance;
    }


    public static void main(String[] args) throws Exception {
        init();
        long[][] distance = dijkstra();

        long ans = Long.MAX_VALUE;
        for (int j = 0; j < k + 1; j++) {
            ans = Math.min(ans, distance[n][j]);
        }

        System.out.println(ans);
    }
}