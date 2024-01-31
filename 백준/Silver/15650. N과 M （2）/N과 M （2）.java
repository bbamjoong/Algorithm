import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];

        dfs(1, 0);
        System.out.println(sb);
    }

    static void dfs(int start, int depth) {
        if (depth == m) {
            for (int i = 1; i < visited.length; i++) {
                if (visited[i]) {
                    sb.append(i).append(" ");
                }
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < n + 1; i++) {
            visited[i] = true;
            dfs(i + 1, depth + 1);
            visited[i] = false;
        }
    }
}