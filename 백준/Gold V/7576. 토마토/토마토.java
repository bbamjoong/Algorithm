import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int m, n;

    static void setInputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0];
            int y = pos[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] == 0) {
                    arr[nx][ny] = arr[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    private static Integer getAns() {
        int ans = 0;
        for (int[] row : arr) {
            for (int j : row) {
                if (j == 0) {
                    return null;
                }
                ans = Math.max(ans, j);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        setInputs();
        bfs();
        Integer ans = getAns();
        if (ans == null) {
            System.out.println(-1);
            return;
        }
        System.out.println(ans - 1);
    }
}