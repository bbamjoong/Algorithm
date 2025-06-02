class Solution {
    public int solution(String s) {
        int n = s.length();
        if (n == 0) return 0;

        boolean[][] dp = new boolean[n][n]; // dp[i][j] == true면 index i~j가 팰린드롬
        int maxLen = 1; 
        
        for (int i = 0; i < n; i++) { // 초기화
            dp[i][i] = true;
        }

        // 길이 2짜리 팰린드롬 처리: 인접 문자가 같으면 dp[i][i+1] = true
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                maxLen = 2;
            }
        }

        // 길이 3 이상: 길이 len인 구간을 검사 // len = 3부터 n까지
        for (int len = 3; len <= n; len++) {
            // i는 구간의 시작 인덱스, j = i + len - 1 은 구간의 끝 인덱스
            for (int i = 0; i <= n - len; i++) {
                
                int j = i + len - 1;
                // 양 끝 문자가 같고, 안쪽 구간(i+1, j-1)이 팰린드롬이면
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    maxLen = len;
                }
            }
        }

        return maxLen;
    }
}
