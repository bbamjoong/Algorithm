class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int len = sticker.length;

        if (len == 1) return sticker[0];

        int [] dp = new int[len]; // i번째 idx 위치에서 얻을 수 있는 최대 합.

        // 첫 번째 스티커를 뗐을때
        dp[0] = sticker[0];
        dp[1] = dp[0];
        
        for (int i = 2; i < len - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + sticker[i], dp[i - 1]);
        }
        answer = dp[len - 2];

        // 첫 번째 스티커를 안뗐을때
        dp[0] = 0;
        dp[1] = sticker[1];
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + sticker[i], dp[i - 1]);
        }
        answer = Math.max(answer, dp[len - 1]);
        
        return answer;
    }
}