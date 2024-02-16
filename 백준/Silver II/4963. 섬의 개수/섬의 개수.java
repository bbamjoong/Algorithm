import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1, 0, 1, 1, -1, -1};
    static int[] dy = {0, -1, 0, 1, 1, -1, 1, -1};

    static void bfs(int[][] graph, int x, int y) {
        int n = graph.length;
        int m = graph[0].length;
        graph[x][y] = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int a = current[0];
            int b = current[1];
            for (int i = 0; i < 8; i++) {
                int nx = a + dx[i];
                int ny = b + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                if (graph[nx][ny] == 1) {
                    graph[nx][ny] = 0;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int[][] graph = new int[n][m];
            int res = 0;
            if (m == 0 && n == 0) {
                break;
            } else {
                for (int i = 0; i < n; i++) {
                    st = new StringTokenizer(br.readLine());
                    for (int j = 0; j < m; j++) {
                        graph[i][j] = Integer.parseInt(st.nextToken());
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (graph[i][j] == 1) {
                            bfs(graph, i, j);
                            res++;
                        }
                    }
                }
                sb.append(res).append("\n");
            }
        }
        System.out.println(sb);
    }
}