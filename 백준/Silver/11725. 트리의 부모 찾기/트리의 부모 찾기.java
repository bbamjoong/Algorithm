import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static List<Integer>[] graph;
    static int[] parents;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        parents = new int[n + 1];
        visited = new boolean[n + 1];
        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < parents.length; i++) {
            sb.setLength(0);
            sb.append(parents[i]);
            System.out.println(sb.toString());
        }
    }

    static void dfs(int parent) {
        visited[parent] = true;
        for (Integer children : graph[parent]) {
            if (visited[children]) {
                continue;
            }
            dfs(children);
            parents[children] = parent;
        }

    }
}