import java.util.Arrays;

class Solution {
    static final int INF = 1_000_000_000;

    public int solution(int alp, int cop, int[][] problems) {
        int targetA = 0;
        int targetC = 0;
        for (int[] p : problems) {
            targetA = Math.max(targetA, p[0]);
            targetC = Math.max(targetC, p[1]);
        }
        
        if (alp >= targetA && cop >= targetC) return 0; // 이미 충분히 큰 상황이면 바로 return

        alp = Math.min(alp, targetA);
        cop = Math.min(cop, targetC);
        int[][] dp = new int[targetA + 1][targetC + 1];
        for (int i = 0; i <= targetA; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[alp][cop] = 0; // 시작점 초기화

        for (int i = alp; i <= targetA; i++) {
            for (int j = cop; j <= targetC; j++) {
                int cur = dp[i][j];
                if (cur == INF) continue;

                if (i < targetA) dp[i + 1][j] = Math.min(dp[i + 1][j], cur + 1);
                if (j < targetC) dp[i][j + 1] = Math.min(dp[i][j + 1], cur + 1);

                for (int[] p : problems) {
                    int reqA = p[0];
                    int reqC = p[1];
                    int rewardA = p[2];
                    int rewardC = p[3];
                    int cost = p[4];
                    if (i >= reqA && j >= reqC) {
                        int ni = Math.min(targetA, i + rewardA);
                        int nj = Math.min(targetC, j + rewardC);
                        dp[ni][nj] = Math.min(dp[ni][nj], cur + cost);
                    }
                }
            }
        }

        return dp[targetA][targetC];
    }
}