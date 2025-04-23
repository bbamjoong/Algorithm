class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int MOD = 1_000_000_007;
        int[][] dp = new int[n + 1][m + 1];

        for (int[] puddle : puddles) {
            int i = puddle[0];  // 가로
            int j = puddle[1];  // 세로
            dp[j][i] = -1;
        }

        dp[1][1] = 1;  // 시작점 초기화

        // i: 가로(열), j: 세로(행)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 && j == 1) continue;
                if (dp[i][j] == -1) continue;

                if (dp[i - 1][j] != -1) {
                    dp[i][j] += dp[i - 1][j] % MOD; // 왼쪽
                }
                if (dp[i][j - 1] != -1) {
                    dp[i][j] += dp[i][j - 1] % MOD; // 위쪽
                }

                dp[i][j] %= MOD;
            }
        }

        return dp[n][m];
    }
}
