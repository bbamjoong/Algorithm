import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static ArrayList<Node>[] li;
    static int[][] arr;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    static boolean setInputs() throws IOException {
        n = Integer.parseInt(br.readLine());
        if (n == 0) {
            return false;
        }

        li = new ArrayList[n * n];
        for (int i = 0; i < n * n; i++) {
            li[i] = new ArrayList<>();
        }

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n * n; i++) {
            int x = i / n;
            int y = i % n;
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }

                li[i].add(new Node(nx * n + ny, arr[nx][ny]));
            }
        }
        return true;
    }

    public static int[] dijkstra(int start) {
        int[] distance = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[start] = arr[start / n][start % n];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, arr[start / n][start % n]));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int dist = node.distance;
            int now = node.vertex;

            if (distance[now] < dist) {
                continue;
            }

            for (Node next : li[now]) {
                int nextVertex = next.vertex;
                int cost = dist + next.distance;

                if (cost < distance[nextVertex]) {
                    distance[nextVertex] = cost;
                    pq.add(new Node(nextVertex, cost));
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) throws IOException {
        int idx = 1;
        while (setInputs()) {
            int[] res = dijkstra(0);
            sb.append("Problem ").append(idx).append(": ").append(res[res.length - 1]).append("\n");
            idx++;
        }

        System.out.print(sb);
    }
}
