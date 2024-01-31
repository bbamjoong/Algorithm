import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] arr = new int[9];
    static int[] res = new int[7];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dfs(0, 0, 0);
        System.out.println(sb);
    }

    static void dfs(int start, int depth, int sm) {
        if (depth == 7) {
            if (sm == 100) {
                for (int re : res) {
                    sb.append(re).append("\n");
                }
            }
            return;
        }

        for (int i = start; i < arr.length; i++) {
            res[depth] = arr[i];
            dfs(i + 1, depth + 1, sm + arr[i]);
        }
    }
}
