import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    // 동 남,서,북
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int n;
    static int m;
    static int[][] arr;
    static ArrayList<CCTV> cctvList = new ArrayList<>();
    static int wallX;
    static int wallY;
    static int ans = (int) 1e9;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int a = Integer.parseInt(st.nextToken());
                arr[i][j] = a;

                if (a >= 1 && a <= 5) {
                    cctvList.add(new CCTV(i, j, a));
                }
                if (a == 6) {
                    wallX = i;
                    wallY = j;
                }
            }
        }
        dfs(0);
        sb.append(ans);
        System.out.println(sb);

    }

    static void dfs(int cnt) {
        if (cnt == cctvList.size()) {
            int cntOfZero = calculate();
            if (cntOfZero >= ans) {
                return;
            }
            ans = Math.min(ans, cntOfZero);
            return;
        }

        CCTV cctv = cctvList.get(cnt);
        if (cctv.num == 1) {
            for (int i = 0; i < 4; i++) {
                go(cctv, i, false);
                dfs(cnt + 1);
                go(cctv, i, true);
            }
        } else if (cctv.num == 2) {
            for (int i = 0; i < 2; i++) {
                go(cctv, i, false);
                go(cctv, i + 2, false);
                dfs(cnt + 1);
                go(cctv, i, true);
                go(cctv, i + 2, true);
            }
        } else if (cctv.num == 3) {
            for (int i = 0; i < 4; i++) {
                go(cctv, i, false);
                go(cctv, (i + 1) % 4, false);
                dfs(cnt + 1);
                go(cctv, i, true);
                go(cctv, (i + 1) % 4, true);
            }
        } else if (cctv.num == 4) {
            for (int i = 0; i < 4; i++) {
                go(cctv, i, false);
                go(cctv, (i + 3) % 4, false);
                go(cctv, (i + 1) % 4, false);
                dfs(cnt + 1);
                go(cctv, i, true);
                go(cctv, (i + 3) % 4, true);
                go(cctv, (i + 1) % 4, true);
            }
        } else if (cctv.num == 5) {
            go(cctv, 0, false);
            go(cctv, 1, false);
            go(cctv, 2, false);
            go(cctv, 3, false);
            dfs(cnt + 1);
            go(cctv, 0, true);
            go(cctv, 1, true);
            go(cctv, 2, true);
            go(cctv, 3, true);

        }


    }


    static void go(CCTV cctv, int dir, boolean remove) {
        int nx = cctv.x;
        int ny = cctv.y;
        while (true) {
            nx += dx[dir];
            ny += dy[dir];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || arr[nx][ny] == 6) {
                break;
            }

            if (remove) {
                if (arr[nx][ny] != 0) { // 백트래킹을 해야하는 경우
                    arr[nx][ny]--;
                    continue;
                }
            }

            arr[nx][ny]++;

        }
    }

    static int calculate() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static class CCTV {
        int x;
        int y;
        int num;

        CCTV(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}