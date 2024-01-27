import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] graph;
    static boolean[] visited;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new int[n + 1][n + 1];
        visited = new boolean[n + 1];

        StringTokenizer st;
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        StringBuilder sb = new StringBuilder();
        sb.append(ans);
        System.out.println(ans);
    }

    static void dfs(int start, int cnt) {
        if (cnt == n / 2) {
            int cal = calculate();
            ans = Math.min(cal, ans);
            return;
        }
        for (int i = start + 1; i < n + 1; i++) {
            visited[i] = true;
            dfs(i, cnt + 1);
            visited[i] = false;
        }
    }

    static int calculate() {
        int cntStart = 0;
        int cntEnd = 0;

        for (int i = 1; i < n; i++) {
            loop:
            for (int j = i + 1; j < n + 1; j++) {
                if (visited[i] && visited[j]) {
                    cntStart += graph[i][j];
                    cntStart += graph[j][i];
                } else if (!visited[i] && !visited[j]) {
                    cntEnd += graph[i][j];
                    cntEnd += graph[j][i];
                }
            }
        }
        return Math.abs(cntStart - cntEnd);
    }
}
