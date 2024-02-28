import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] arr;
    static int[][][] dp; // 1. 가로 / 2. 대각선 / 3. 세로

    static void setInputs() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        // 자바는 3차원 배열에서 z, x, y 축 순서임...??
        // [정보][행][열]
        dp = new int[3][n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void setFirstRow() {
        // 첫번째 파이프가 놓여져 있으므로 0행1열은 1로 치환
        dp[0][0][1] = 1;

        // 1행은 가로 파이프밖에 놓이지 못한다.
        // 1행 마지막 열은 둘 필요 없음.
        for (int i = 2; i < n - 1; i++) {
            if (arr[0][i] == 0) { // 벽이 아니어야함.
                dp[0][0][i] = 1;
                continue;
            }
            break; // 1행에서 값 배치중에 벽 있으면 break; 둘 필요 없음.
        }
    }

    static void getDP() {
        // dp를 탐색할 때, 1행 & (1열 & 2열)은 확인할 필요가 없다.
        // 파이프가 1행의 1열 2열에 가로 모양으로 놓여있으므로, 1열과 2열은 고려할 필요가 없고
        // 1행은 가로 파이프밖에 놓이지 못하므로 탐색 할 필요가 없다.
        for (int r = 1; r < n; r++) {
            for (int c = 2; c < n; c++) {

                // 대각선은 문제의 조건에 따라 3칸이 모두 0이어야 됨
                // 왼쪽 위 점의 가로 + 대각선 + 세로 모두 더해주면 됨
                if (arr[r][c] == 0 && arr[r][c - 1] == 0 && arr[r - 1][c] == 0) {
                    dp[1][r][c] = dp[0][r - 1][c - 1] + dp[1][r - 1][c - 1] + dp[2][r - 1][c - 1];
                }

                // 가로, 세로는 탐색할 위치만 0이면 됨
                if (arr[r][c] == 0) {
                    // 가로는 이전 점의 가로 + 대각선
                    dp[0][r][c] = dp[0][r][c - 1] + dp[1][r][c - 1];
                    // 세로는 이전 점의 세로 + 대각선
                    dp[2][r][c] = dp[2][r - 1][c] + dp[1][r - 1][c];
                }
            }
        }
    }

    static int getAns() {
        int ans = 0;
        for (int i = 0; i < 3; i++) {
            ans += dp[i][n - 1][n - 1];
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        setInputs();
        setFirstRow();
        getDP();

        int ans = getAns();
        System.out.println(ans);
    }
}
