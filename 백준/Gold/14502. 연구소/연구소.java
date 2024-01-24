import java.io.BufferedReader;
import java.io.InputStreamReader;
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

        wall();
        StringBuilder sb = new StringBuilder();
        sb.append(ans);
        System.out.println(sb.toString());
    }

    static void wall() {
        int area = n * m;

        for (int first = 0; first < area - 2; first++) {
            if (graph[first / m][first % m] == 0) {
                graph[first / m][first % m] = 1;
            } else {
                continue;
            }

            for (int second = first + 1; second < area - 1; second++) {
                if (graph[second / m][second % m] == 0) {
                    graph[second / m][second % m] = 1;
                } else {
                    continue;
                }

                for (int third = second + 1; third < area; third++) {
                    if (graph[third / m][third % m] == 0) {
                        graph[third / m][third % m] = 1;
                    } else {
                        continue;
                    }

                    update();

                    graph[third / m][third % m] = 0;
                }
                graph[second / m][second % m] = 0;
            }
            graph[first / m][first % m] = 0;
        }
    }

    static void update() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (graph[x][y] == 2) {
                    dfs(x, y);
                }
            }
        }
        calculateCnt();
    }

    static void dfs(int x, int y) {
        int nx;
        int ny;
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || graph[nx][ny] != 0) {
                continue;
            }

            graph[nx][ny] = 3;
            dfs(nx, ny);
        }
    }

    static void calculateCnt() {
        int bfs_cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 0) {
                    bfs_cnt++;
                } else if (graph[i][j] == 3) {
                    graph[i][j] = 0;
                }
            }
        }
        ans = Math.max(ans, bfs_cnt);
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