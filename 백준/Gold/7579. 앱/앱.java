import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[] memories;
    static int[] costs;
    static int totalCost; // 전체 비용
    static int[][] dp;
    static int ans;

    static void setInputs() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        memories = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }

        costs = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            totalCost += costs[i];
        }
    }

    static void doKnapSack() {
        dp = new int[n + 1][totalCost + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < totalCost + 1; j++) { // 비용이 0일수도 있네요....이런
                int memory = memories[i - 1];
                int cost = costs[i - 1];

                if (j < cost) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost] + memory);
                }
            }
        }
    }

    static void getAnswer() {
        // knapsack을 이용해 마지막 메모리까지 모두 탐색을 했을 때
        // dp의 값이 M이상이면 정답. 이후 break;
        for (int i = 1; i < totalCost + 1; i++) {
            if (dp[n][i] >= m) {
                ans = i;
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        setInputs();
        doKnapSack();
        getAnswer();
        System.out.println(ans);
    }
}