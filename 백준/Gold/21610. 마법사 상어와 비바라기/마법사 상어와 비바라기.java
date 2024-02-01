import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] arr;
    static ArrayList<Cloud> clouds = new ArrayList<>();

    static boolean[][] visited;
    static int ans;

    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        setUp();

        for (int i = 0; i < m; i++) {
            visited = new boolean[n][n];
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int cnt = Integer.parseInt(st.nextToken());
            moveCloudAndAddWater(dir, cnt);
            bugWater();
            remakeCloud();
        }
        calculate();

        StringBuilder sb = new StringBuilder();
        sb.append(ans);
        System.out.println(sb);
    }

    static void setUp() {
        clouds.add(new Cloud(n - 1, 0));
        clouds.add(new Cloud(n - 1, 1));
        clouds.add(new Cloud(n - 2, 0));
        clouds.add(new Cloud(n - 2, 1));
    }

    static void moveCloudAndAddWater(int dir, int cnt) {
        for (Cloud cloud : clouds) {
            cloud.x = (cloud.x + dx[dir] * cnt) % n;
            cloud.y = (cloud.y + dy[dir] * cnt) % n;
            if (cloud.x < 0) {
                cloud.x += n;
            }
            if (cloud.y < 0) {
                cloud.y += n;
            }

            arr[cloud.x][cloud.y]++;
            visited[cloud.x][cloud.y] = true;
        }
    }

    static void bugWater() {
        for (Cloud cloud : clouds) {
            int x = cloud.x;
            int y = cloud.y;
            int cnt = 0;
            for (int i = 1; i < 8; i += 2) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }

                if (arr[nx][ny] > 0) {
                    cnt++;
                }
            }
            arr[x][y] += cnt;
        }
    }

    static void remakeCloud() {
        clouds.clear();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || arr[i][j] < 2) {
                    continue;
                }
                arr[i][j] -= 2;
                clouds.add(new Cloud(i, j));
            }
        }
    }

    static void calculate() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += arr[i][j];
            }
        }
    }

    static class Cloud {
        int x;
        int y;

        Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}