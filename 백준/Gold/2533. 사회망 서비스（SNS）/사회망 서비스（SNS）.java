import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[n + 1];
        dp = new int[n + 1][2];
        dfs(1);

        int ans = Math.min(dp[1][0], dp[1][1]);
        StringBuilder sb = new StringBuilder();
        sb.append(ans);
        System.out.println(sb.toString());
    }

    static void dfs(int parent) {
        visited[parent] = true;
        dp[parent][1] = 1; // 얼리어댑터일때는 1로 초기화. // Child 계산 전에 초기화 되기 때문에 상관 없음.
        for (Integer child : graph[parent]) {
            if (visited[child]) { // 이미 방문한 노드면 continue;
                continue;
            }
            dfs(child);
            // 부모가 얼리어댑터가 아니라면 -> 자식은 얼리어댑터여야 한다.
            // 따라서 자식이 얼리어댑터일 때의 개수만큼 더해준다.
            // 최종적으로 Leaf에서 Root까지 계속 더해주는 형태의 dp이다.
            dp[parent][0] += dp[child][1];
            // 부모가 얼리어댑터라면 -> 자식은 얼리어댑터 or 얼리어댑터가 아니어도 상관 없다.
            // 따라서 최소 값을 더해준다.
            dp[parent][1] += Math.min(dp[child][0], dp[child][1]);
        }
    }
}