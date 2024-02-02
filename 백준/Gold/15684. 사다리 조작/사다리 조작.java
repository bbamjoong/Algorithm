import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int h;
    static int[][] graph;
    static int ans = (int) 1e9;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // 설치할 수 있는 사다리의 개수
        h = Integer.parseInt(st.nextToken());

        graph = new int[h][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a - 1][b - 1] = -1; // 사다리 왼쪽은 -1
            graph[a - 1][b] = 1; // 사다리 오른쪽은 1
        }

        dfs(0, 0, 0);

        if (ans > 3) {
            ans = -1;
        }

        sb.append(ans);
        System.out.println(sb);
    }

    static void dfs(int radderCnt, int x, int y) {
        if (radderCnt > 3) { // 사다리 개수가 3개 넘으면 안된다.
            return;
        }

        if (radderCnt >= ans) { // 현재 답보다 더 많은 개수 설치하려고 하면 안된다.
            return;
        }

        if (check()) { // 검사 시 true라면 ans 갱신
            ans = Math.min(ans, radderCnt);
        }

        for (int i = x; i < h; i++) {
            for (int j = y; j < n - 1; j++) {
                if (graph[i][j] == 0 && graph[i][j + 1] == 0) {
                    graph[i][j] = -1;
                    graph[i][j + 1] = 1;

                    //사다리 설치 후 2칸 뒤부터 탐색
                    dfs(radderCnt + 1, i, j + 2);

                    graph[i][j] = 0;
                    graph[i][j + 1] = 0;
                }
            }
            y = 0; // 다음 행으로 넘어갈 때 0열 부터 탐색
        }

    }

    static boolean check() { // 원래 자기 사다리로 골인하는지??
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
}