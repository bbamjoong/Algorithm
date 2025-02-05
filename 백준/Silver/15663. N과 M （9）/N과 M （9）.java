import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] values, res;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        values = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(values);

        visited = new boolean[n];
        res = new int[m];

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

        int lastUsed = -1;  // 이전에 사용한 값 저장
        for (int i = 0; i < n; i++) {
            if (!visited[i] && values[i] != lastUsed) {
                res[depth] = values[i];
                lastUsed = values[i];

                visited[i] = true;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}
