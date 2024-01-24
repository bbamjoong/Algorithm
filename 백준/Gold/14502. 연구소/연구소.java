import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int n;
    static int m;
    static int[][] graph;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        wall(0, 0);

        System.out.println(ans);
    }

    static void wall(int cnt, int depth) {
        if (cnt == 3) {
            bfs();
            return;
        }

        for (int i = depth; i < n * m; i++) {
            if (graph[i / m][i % m] == 0) {
                graph[i / m][i % m] = 1;
                wall(cnt + 1, depth + 1);
                graph[i / m][i % m] = 0;
            }
        }
    }

    static void bfs() {
        int[][] visited = new int[n][m];

        Queue<Node> q = new LinkedList<>();
        addVirus(q);

        while (!q.isEmpty()) {
            Node poll = q.poll();
            int x = poll.x;
            int y = poll.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                if (visited[nx][ny] == 0 && graph[nx][ny] == 0) {
                    visited[nx][ny] = 1;
                    graph[nx][ny] = 3;
                    q.add(new Node(nx, ny));
                }
            }
        }
        calculateCnt();
        initializeGraph();
    }

    static void addVirus(Queue<Node> q) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 2) {
                    q.add(new Node(i, j));
                }
            }
        }
    }

    static void calculateCnt() {
        int bfs_cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 0) {
                    bfs_cnt++;
                }
            }
        }
        ans = Math.max(ans, bfs_cnt);
    }

    static void initializeGraph() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 3) {
                    graph[i][j] = 0;
                }
            }
        }
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}