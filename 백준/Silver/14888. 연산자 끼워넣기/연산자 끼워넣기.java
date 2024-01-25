import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] nums;
    static int[] operators;

    static int minAns = Integer.MAX_VALUE;
    static int maxAns = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        operators = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, nums[0], operators[0], operators[1], operators[2], operators[3]);
        StringBuilder sb = new StringBuilder();
        sb.append(maxAns + "\n");
        sb.append(minAns);
        System.out.println(sb);
    }

    static void dfs(int depth, int total, int plus, int minus, int multiple, int divide) {
        if (depth == n) {
            minAns = Math.min(minAns, total);
            maxAns = Math.max(maxAns, total);
            return;
        }
        if (plus > 0) {
            dfs(depth + 1, total + nums[depth], plus - 1, minus, multiple, divide);
        }
        if (minus > 0) {
            dfs(depth + 1, total - nums[depth], plus, minus - 1, multiple, divide);
        }
        if (multiple > 0) {
            dfs(depth + 1, total * nums[depth], plus, minus, multiple - 1, divide);
        }
        if (divide > 0) {
            dfs(depth + 1, total / nums[depth], plus, minus, multiple, divide - 1);
        }
    }
}