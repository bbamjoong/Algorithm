import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static List<Node>[] graph;
    static int[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        visited = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            visited[i] = -1;
        }

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, w));
            graph[b].add(new Node(a, w));
        }

        visited[1] = 0;
        dfs(1, 0);

        int max = 0;
        int maxIndex = 0;
        for (int i = 1; i < n + 1; i++) {
            if (visited[i] > max) {
                max = visited[i];
                maxIndex = i;
            }
        }

        for (int i = 1; i < n + 1; i++) {
            visited[i] = -1;
        }

        visited[maxIndex] = 0;
        dfs(maxIndex, 0);

        max = 0;
        for (int i = 1; i < n + 1; i++) {
            if (visited[i] > max) {
                max = visited[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(max);
        System.out.println(sb);

    }

    static void dfs(int node, int weight) {
        for (Node nextNode : graph[node]) {
            if (visited[nextNode.next] == -1) {
                visited[nextNode.next] = weight + nextNode.weight;
                dfs(nextNode.next, weight + nextNode.weight);
            }
        }
    }

    static class Node {
        int next;
        int weight;

        Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }
}