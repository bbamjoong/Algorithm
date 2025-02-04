import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static int n;
    static int e;
    static int v1;
    static int v2;
    static int maxValue = 200_000 * 1_000;

    static ArrayList<Node>[] li;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Node implements Comparable<Node> {
        int next;
        int cost;

        public Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        init();

        /**
         * 1에서 시작하는 다익스트라
         * v1에서 시작하는 다익스트라
         * v2에서 시작하는 다익스트라
         *
         * 총 3번의 다익스트라 구현
         */
        int[] dist1 = di(1);
        int[] distV1 = di(v1);
        int[] distV2 = di(v2);
        // 1 -> v1 -> v2 -> n
        int distV1First = dist1[v1] + distV1[v2] + distV2[n];
        // 1 -> v2 -> v1 -> n
        int distV2First = dist1[v2] + distV2[v1] + distV1[n];

        // 도달하지 못한 경우 20억. -> 20억이 3번 더해지면 끔찍합니다.
        long ans = Math.min(distV1First, distV2First);

        if (ans >= maxValue) {
            System.out.println(-1);
            return;
        }
        System.out.println(ans);
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        li = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            li[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            li[start].add(new Node(end, weight));
            li[end].add(new Node(start, weight));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
    }

    static int[] di(int start) {
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            dist[i] = 200_000 * 1_000;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (visited[now.next]) {
                continue;
            }

            // 현재 방문하지 않은 노드라면?
            visited[now.next] = true;

            for (Node next : li[now.next]) {
                // 다음 노드 방문하지 않아야함
                if (visited[next.next]) {
                    continue;
                }

                // 다음 방문하는 노드가 더 긴 거리면 안돼요
                if (dist[next.next] < dist[now.next] + next.cost) {
                    continue;
                }
                dist[next.next] = dist[now.next] + next.cost;

                pq.add(new Node(next.next, dist[next.next]));
            }
        }

        return dist;
    }
}