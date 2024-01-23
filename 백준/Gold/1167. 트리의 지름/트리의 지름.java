import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static List<Node>[] graph;
    static boolean[] visited;
    static int[] lengthSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];
        lengthSum = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parentNode = Integer.parseInt(st.nextToken());
            while (true) {
                int childNode = Integer.parseInt(st.nextToken());
                if (childNode == -1) {
                    break;
                }
                int weight = Integer.parseInt(st.nextToken());
                graph[parentNode].add(new Node(childNode, weight));
            }
        }

        visited[1] = true;
        dfs(1, 0);
        int max = 0;
        int maxIndex = 0;

        for (int i = 1; i < n + 1; i++) {
            if (lengthSum[i] > max) {
                max = lengthSum[i];
                maxIndex = i;
            }
        }

        visited = new boolean[n + 1];
        lengthSum = new int[n + 1];

        visited[maxIndex] = true;
        dfs(maxIndex, 0);

        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            if (lengthSum[i] > ans) {
                ans = lengthSum[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans);
        System.out.println(sb);
    }

    static class Node {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    static void dfs(int node, int cost) {
        for (Node childrenNode : graph[node]) {
            if (!visited[childrenNode.vertex]) {
                visited[childrenNode.vertex] = true;
                lengthSum[childrenNode.vertex] = cost + childrenNode.weight;
                dfs(childrenNode.vertex, cost + childrenNode.weight);
            }
        }
    }
}