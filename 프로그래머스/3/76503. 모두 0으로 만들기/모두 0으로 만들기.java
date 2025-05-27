import java.util.*;

public class Solution {
    static long[] weight;
    static int[] parent;
    static int[] depth;
    static ArrayList<Integer>[] graph;
    static long totalMoves = 0;

    public static long solution(int[] a, int[][] edges) {
        int n = a.length;
        weight = new long[n];
        parent = new int[n];
        depth = new int[n];
        graph = new ArrayList[n];
        totalMoves = 0;

        for (int i = 0; i < n; i++) {
            weight[i] = a[i];
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        bfs(0); // 루트는 0

        // depth 기준으로 정렬
        List<Integer> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) nodes.add(i);
        Collections.sort(nodes, (u, v) -> depth[v] - depth[u]);

        for (int node : nodes) {
            if (node == 0) continue; // 루트는 마지막에 확인
            
            int p = parent[node];
            weight[p] += weight[node];
            totalMoves += Math.abs(weight[node]);
        }
        if (weight[0] == 0) return totalMoves;
        return -1;
    }

    // bfs로 depth와 parent 계산
    static void bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[weight.length];
        
        queue.add(root);
        visited[root] = true;
        parent[root] = -1;
        depth[root] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    parent[next] = now;
                    depth[next] = depth[now] + 1;
                    queue.add(next);
                }
            }
        }
    }
}