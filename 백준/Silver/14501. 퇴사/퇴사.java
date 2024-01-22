import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] T;
    static int[] P;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        T = new int[n + 1];
        P = new int[n + 1];

        StringTokenizer st;
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        dfs(1, 0);
        System.out.println(ans);
    }

    static void dfs(int now, int price) {
        if (now >= n + 1) {
            ans = Math.max(ans, price);
            return;
        }

        if (now + T[now] <= n + 1) {
            dfs(now + T[now], price + P[now]);
        }

        if (now + 1 <= n + 1) {
            dfs(now + 1, price);
        }
    }
}