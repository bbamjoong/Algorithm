import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 0;
        dp[3] = 1;
        dp[4] = 0;
        dp[5] = 0;

        for (int i = 6; i <= n; i++) {
            if (dp[i - 1] + dp[i - 3] + dp[i - 4] == 0) {
                dp[i] = 1;
            } else {
                dp[i] = 0;
            }
        }

        if (dp[n] == 1) {
            System.out.println("CY");
        } else {
            System.out.println("SK");
        }
    }
}