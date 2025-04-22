import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        
        dp[0][0] = triangle[0][0];
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int fromLeftUp = 0;
                if (j > 0) {
                    fromLeftUp = dp[i - 1][j - 1];
                }
                
                int fromUp = 0;
                if (i > j) {
                    fromUp = dp[i - 1][j];
                }
                
                // 둘 중 큰 값을 골라서 더하기
                dp[i][j] = triangle[i][j] + Math.max(fromLeftUp, fromUp);
            }
        }
        
        // 마지막 행에서 최대값 찾기
        int answer = 0;
        for (int x : dp[n - 1]) {
            if (x > answer) {
                answer = x;
            }
        }
        return answer;
    }
}