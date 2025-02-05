import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int[] res;
    static boolean[] visited;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        res = new int[m];
        visited = new boolean[n + 1];
        dfs(0, 1);
    }

    static void dfs(int depth, int index) {

        if (depth == m) {
            for (int i : res) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = index; i < n + 1; i++) {

            if (!visited[i]) {
                visited[i] = true;
                res[depth] = i;
                dfs(depth + 1, i + 1);

                visited[i] = false;
            }
        }
    }
}