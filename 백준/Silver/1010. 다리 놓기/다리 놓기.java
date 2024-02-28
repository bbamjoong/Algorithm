import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[] dp = new int[r + 1];
            dp[1] = n;
            for (int j = 2; j < r + 1; j++) {
                dp[j] = dp[j - 1] * (n - j + 1) / j;
            }
            sb.append(dp[r]).append("\n");
        }
        System.out.println(sb);
    }
}