import java.util.*;
import java.io.*;

public class Main {

    static boolean[][] dp;
    static int[] weights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        weights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[N + 1][15001]; // 무게 차이는 최대 15000

        dfs(0, 0);

        int T = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int bead = Integer.parseInt(st.nextToken());
            if (bead > 15000) sb.append("N ");
            else if (dp[N][bead]) sb.append("Y ");
            else sb.append("N ");
        }

        System.out.println(sb.toString());
    }

    static void dfs(int idx, int weight) {
        if (idx > weights.length || dp[idx][weight]) return;

        dp[idx][weight] = true;

        if (idx == weights.length) return;

        dfs(idx + 1, weight); // 사용 안함
        dfs(idx + 1, weight + weights[idx]); // 왼쪽
        dfs(idx + 1, Math.abs(weight - weights[idx])); // 오른쪽
    }
}
