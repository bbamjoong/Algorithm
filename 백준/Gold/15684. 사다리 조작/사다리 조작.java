import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int h;
    static int[][] graph;
    static StringBuilder sb = new StringBuilder();
    static int ans = (int) 1e9;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        graph = new int[h][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a][b] = -1;
            graph[a][b + 1] = 1;
        }

        if (findNeedLadderNum() > 3) { // 사다리를 3개 초과 설치해야한다면
            sb.append(-1);
            System.out.println(sb);
            return;
        } else {
            dfs(0, 0, 0);
        }

        if (ans > 3) {
            ans = -1;
        }
        sb.append(ans);
        System.out.println(sb);
    }

    private static void dfs(int radderCnt, int x, int y) {
        if (radderCnt > 3) { // 사다리 개수가 3개 넘으면 안된다.
            return;
        }

        if (radderCnt >= ans) { // 현재 답보다 더 많은 개수 설치하려고 하면 안된다.
            return;
        }

        if (checkResult()) { // 검사 시 true라면 ans 갱신
            ans = Math.min(ans, radderCnt);
        }

        for (int i = x; i < h; i++) {
            for (int j = y; j < n - 1; j++) {
                if (graph[i][j] == 0 && graph[i][j + 1] == 0) {
                    graph[i][j] = -1;
                    graph[i][j + 1] = 1;

                    dfs(radderCnt + 1, i, j + 2);

                    graph[i][j] = 0;
                    graph[i][j + 1] = 0;
                }
            }
            y = 0;
        }
    }

    static boolean checkResult() { // 원래 자기 사다리로 골인하는지??
        for (int i = 0; i < n; i++) {
            int x = 0;
            int y = i;

            while (x != h) {
                if (graph[x][y] == -1) {
                    y++;
                } else if (graph[x][y] == 1) {
                    y--;
                }
                x++;
            }
            if (i != y) {
                return false;
            }
        }
        return true;
    }

    static int findNeedLadderNum() { // 자기 자신의 줄에 사다리가 짝수개가 걸려있어야 한다.
        int cnt = 0;
        for (int j = 0; j < n - 1; j++) {
            int num = 0;
            for (int i = 0; i < Main.h; i++) {
                if (graph[i][j] == 1) {
                    num++;
                }
            }
            if (num % 2 == 1) {
                cnt++;
            }
        }
        return cnt;
    }
}