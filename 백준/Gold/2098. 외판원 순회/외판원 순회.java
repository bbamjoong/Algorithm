import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static int[][] dp;
    static int MAX = 16_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[n][(1 << n) - 1]; // 열은 활성화된 비트에 따라 구성된다. 0000 ~ 1111;

        System.out.println(dfs(0, 1));
    }

    static int dfs(int now, int visited) {
        if (visited == (1 << n) - 1) { // 모든 도시 탐색 시
            if (arr[now][0] == 0) {
                return MAX;
            }
            return arr[now][0];
        }
        if (dp[now][visited] != 0) { // 이미 최소값이 있으면 return;
            return dp[now][visited];
        }

        dp[now][visited] = MAX;

        for (int i = 0; i < n; i++) {
            if ((visited & (1 << i)) == 0 && arr[now][i] != 0) { // 아직 방문하지 않았고, 가는 경로가 있다면
                dp[now][visited] = Math.min(dfs(i, visited | (1 << i)) + arr[now][i], dp[now][visited]);   // 최소비용 갱신
            }
        }
        return dp[now][visited];
    }
}