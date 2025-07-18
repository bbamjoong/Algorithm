class Solution {
    static int[][] sum;
    static int N, M;
 
    public static int solution(int[][] board, int[][] skill) {
        N = board.length;
        M = board[0].length;
 
        sum = new int[N + 1][M + 1];
        for (int[] s : skill) {
            int x1 = s[1], y1 = s[2];
            int x2 = s[3], y2 = s[4];
            int degree = s[5];
            if (s[0] == 1) degree *= -1;
 
            sum[x1][y1] += degree;
            sum[x1][y2 + 1] += (degree * -1);
            sum[x2 + 1][y1] += (degree * -1);
            sum[x2 + 1][y2 + 1] += degree;
        }
        
        operate();
 
        // 살아남은 건물 확인
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + sum[i][j] > 0) answer++;
            }
        }
        return answer;
    }
 
    // 누적합 계산
    private static void operate() {
        // 상하
        for (int y = 1; y < N; y++) {
            for (int x = 0; x < M; x++) {
                sum[y][x] += sum[y - 1][x];
            }
        }
        // 좌우
        for (int x = 1; x < M; x++) {
            for (int y = 0; y < N; y++) {
                sum[y][x] += sum[y][x - 1];
            }
        }
    }
 
}