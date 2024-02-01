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
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;

                if (num >= 1 && num <= 5) { // cctvList에 cctv 추가
                    cctvList.add(new CCTV(i, j, num));
                }
            }
        }
        dfs(0);

        sb.append(ans);
        System.out.println(sb);
    }

    static void dfs(int cnt) {
        if (cnt == cctvList.size()) {
            calculate();
            return;
        }

        CCTV cctv = cctvList.get(cnt);
        switch (cctv.num) {
            case (1):
                startCCTV1(cctv, cnt);
                break;
            case (2):
                startCCTV2(cctv, cnt);
                break;
            case (3):
                startCCTV3(cctv, cnt);
                break;
            case (4):
                startCCTV4(cctv, cnt);
                break;
            case (5):
                startCCTV5(cctv, cnt);
        }
    }

    static void startCCTV1(CCTV cctv, int cnt) {
        for (int i = 0; i < 4; i++) {
            go(cctv, i, false);
            dfs(cnt + 1);
            go(cctv, i, true);
        }
    }

    static void startCCTV2(CCTV cctv, int cnt) {
        for (int i = 0; i < 2; i++) {
            go(cctv, i, false);
            go(cctv, i + 2, false);
            dfs(cnt + 1);
            go(cctv, i, true);
            go(cctv, i + 2, true);
        }
    }

    private static void startCCTV3(CCTV cctv, int cnt) {
        for (int i = 0; i < 4; i++) {
            go(cctv, i, false);
            go(cctv, (i + 1) % 4, false);
            dfs(cnt + 1);
            go(cctv, i, true);
            go(cctv, (i + 1) % 4, true);
        }
    }

    static void startCCTV4(CCTV cctv, int cnt) {
        for (int i = 0; i < 4; i++) {
            go(cctv, i, false);
            go(cctv, (i + 3) % 4, false);
            go(cctv, (i + 1) % 4, false);
            dfs(cnt + 1);
            go(cctv, i, true);
            go(cctv, (i + 3) % 4, true);
            go(cctv, (i + 1) % 4, true);
        }
    }

    private static void startCCTV5(CCTV cctv, int cnt) {
        for (int i = 0; i < 4; i++) {
            go(cctv, i, false);
        }
        dfs(cnt + 1);
        for (int i = 0; i < 4; i++) {
            go(cctv, i, true);
        }
    }

    // remove : true -> 백트래킹을 하면서 감시 구역을 다시 원래대로 돌리는 경우
    // remove : false -> 감시구역 탐색
    static void go(CCTV cctv, int dir, boolean remove) {
        int nx = cctv.x;
        int ny = cctv.y;

        while (true) {
            nx += dx[dir];
            ny += dy[dir];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || arr[nx][ny] == 6) {
                break;
            }

            if (remove && arr[nx][ny] != 0) {  // 백트래킹을 해야하는 경우
                arr[nx][ny]--;
                continue;
            }

            arr[nx][ny]++; // CCTV가 감시하는 구역이면 ++
        }
    }

    static void calculate() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    cnt++;
                }

                if (cnt >= ans) {
                    return;
                }
            }
        }
        ans = Math.min(ans, cnt);
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