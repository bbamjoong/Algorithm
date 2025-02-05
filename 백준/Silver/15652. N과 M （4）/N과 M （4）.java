import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int[] res;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        res = new int[m];
        dfs(0, 1);

        System.out.println(sb);
    }

    static void dfs(int depth, int index) {

        if (depth == m) {
            for (int i : res) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = index; i < n + 1; i++) {
            res[depth] = i;
            dfs(depth + 1, i);
        }
    }
}