import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] res;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        res = new int[m];
        dfs(0);
        System.out.println(sb.toString());
    }

    static void dfs(int cnt) {
        if (cnt == m) {
            for (int i = 0; i < m; i++) {
                sb.append(res[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i < n + 1; i++) {
            res[cnt] = i;
            dfs(cnt + 1);
        }
    }
}
