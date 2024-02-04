import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, l, r;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        while (true) {
            visited = new boolean[n][n];
            boolean stop = true;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        LinkedList<int[]> arr = bfs(i, j);

                        if (arr.size() > 1) {
                            stop = false;

                            int sum = 0;
                            for (int[] pos : arr) {
                                sum += graph[pos[0]][pos[1]];
                            }
                            int avg = sum / arr.size();
                            
                            for (int[] pos : arr) {
                                graph[pos[0]][pos[1]] = avg;
                            }
                        }
                    }
                }
            }

            if (stop) {
                break;
            }

            ans++;
        }

        System.out.println(ans);
    }

    static LinkedList<int[]> bfs(int a, int b) {
        Queue<int[]> q = new LinkedList<>();
        LinkedList<int[]> tmp = new LinkedList<>();

        q.add(new int[]{a, b});
        visited[a][b] = true;
        tmp.add(new int[]{a, b});

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int x = pos[0];
            int y = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny]) {
                    if (l <= Math.abs(graph[nx][ny] - graph[x][y]) && Math.abs(graph[nx][ny] - graph[x][y]) <= r) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                        tmp.add(new int[]{nx, ny});
                    }
                }
            }
        }

        return tmp;
    }
}
