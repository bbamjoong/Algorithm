import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] graph;
    static int cnt;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        for (int i = 0; i < graph.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < graph[0].length; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(x, y, d);

        System.out.println(cnt);
    }

    static void dfs(int x, int y, int d) {
        if (graph[x][y] == 0) {
            graph[x][y] = 2;
            cnt++;
        }

        boolean canClean = false;
        for (int i = 0; i < 4; i++) {
            d = turnCounterClockwise(d);
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && ny >= 0 && nx < n && ny < m && graph[nx][ny] == 0) {
                canClean = true;
                dfs(nx, ny, d);
                break;
            }
        }

        if (!canClean) {
            int backX = x - dx[d];
            int backY = y - dy[d];
            if (backX >= 0 && backY >= 0 && backX < n && backY < m && graph[backX][backY] != 1) {
                dfs(backX, backY, d);
            }
        }
    }

    static int turnCounterClockwise(int d) {
        return (d + 3) % 4;
    }
}
