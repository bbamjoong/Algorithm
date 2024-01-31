import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[] arr;
    static int[] res;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            if (n == 0) {
                break;
            }

            arr = new int[n];
            res = new int[6];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int start, int depth) {
        if (depth == 6) {
            for (int re : res) {
                sb.append(re).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < n; i++) {
            res[depth] = arr[i];
            dfs(i + 1, depth + 1);
        }

    }
}
