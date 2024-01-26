import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int x, y, d;
    static int[][] graph;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(x, y, d, 0);
    }

    static void dfs(int x, int y, int d, int cnt) {
        // 현재칸이 청소되지 않았다면 청소 후 cnt+1
        if (graph[x][y] == 0) {
            graph[x][y] = 2;
            cnt += 1;
        }

        // 주변 네칸이 벽이거나 청소가 되었다면
        if (graph[x - 1][y] != 0 && graph[x + 1][y] != 0 && graph[x][y - 1] != 0 && graph[x][y + 1] != 0) {
            int nx = x - dx[d];
            int ny = y - dy[d];

            // 바라보는 방향의 뒤쪽 칸이 벽이라 후진이 불가능하다면
            if (graph[nx][ny] == 1) {
                System.out.println(cnt);
                return;
            }
            // 후진이 가능하다면
            else {
                dfs(nx, ny, d, cnt);
            }
        }
        // 청소할 공간이 있다면
        else {
            // 반시계로 90도 회전
            d = (d + 3) % 4;
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 회전한 방향의 앞쪽 칸이 청소가 가능한 칸이라면 전진
            if (graph[nx][ny] == 0) {
                dfs(nx, ny, d, cnt);
            }
            // 아니라면 다시 반시계로 90도 회전해야하므로 현재좌표에서 dfs 호출
            else {
                dfs(x, y, d, cnt);
            }
        }
    }
}