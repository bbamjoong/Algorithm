import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final Integer MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        // 건물을 큰 순서부터 1개씩 배치했을 때, N개의 건물을 배치 시 왼쪽 오른쪽 보이는 건물의 배열
        int[][][] dp = new int[N + 1][L + 1][R + 1];
        dp[1][1][1] = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= L; j++) {
                for (int k = 1; k <= R; k++) {
                    // dp[i - 1][j - 1][k] -> 이 경우 가장 왼쪽에 (가장 짧은 건물보다 1 작은 건물을 두면) L이 1 증가함.
                    // dp[i - 1][j][k - 1] -> 이 경우 가장 오른쪽에 (가장 짧은 건물보다 1 작은 건물을 두면) R이 1 증가함.
                    // (i - 2) * dp[i-1][j][k] -> 건물들 사이에 (가장 짧은 건물보다 1 작은 건물을 두면) L R 변동이 없다.
                    int left = dp[i - 1][j - 1][k];
                    int right = dp[i - 1][j][k - 1];
                    long mid = (long) (i - 2) * dp[i - 1][j][k] % MOD; // 오버플로우 가능.

                    dp[i][j][k] = (int) ((left + right + mid) % MOD);
                }
            }
        }

        System.out.println(dp[N][L][R]);
    }
}