import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int ii = 0; ii < n; ii++) {
            for (int jj = 0; jj < m; jj++) {
                visited[ii][jj] = true;
                dfs(ii, jj, 1, arr[ii][jj]);
                visited[ii][jj] = false;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans);
        System.out.println(sb.toString());
    }

    static void dfs(int x, int y, int cnt, int total) {
        if (cnt == 4) {
            ans = Math.max(ans, total);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }

            if (!visited[nx][ny]) {
                if (cnt == 2) {
                    visited[nx][ny] = true;
                    dfs(x, y, cnt + 1, total + arr[nx][ny]);
                    visited[nx][ny] = false;
                }

                visited[nx][ny] = true;
                dfs(nx, ny, cnt + 1, total + arr[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }
}