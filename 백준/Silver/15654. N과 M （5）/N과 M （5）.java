import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int[] values;
    static int[] res;
    static boolean[] visited;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        values = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(values);

        res = new int[m];
        visited = new boolean[n + 1];
        dfs(0);

        System.out.println(sb);
    }

    static void dfs(int depth) {

        if (depth == m) {
            for (int i : res) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i < n + 1; i++) {
            // 방문하지 않았다면
            if (!visited[i]) {
                res[depth] = values[i];

                visited[i] = true;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}