import java.util.*;

class Solution {
    
    static List<Integer>[] graph; // 인접그래프
    static boolean[] visited;
    static int[][] dp;
    
    
    public int solution(int n, int[][] lighthouse) {
        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < lighthouse.length; i++) {
            int start = lighthouse[i][0];
            int end = lighthouse[i][1];
            
            graph[start].add(end);
            graph[end].add(start);
        }
        
        visited = new boolean[n + 1];
        dp = new int[n + 1][2];
        for (int i = 0; i < n + 1; i++) {
            dp[i][1] = 1; // 얘가 얼리어답터일때는 값이 1이어야하니까. 일단 초기화해두는 것.
        }
        
        dfs(1); // 1번이 루트라고 그냥 가정하면됨.
        
        
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    static void dfs(int parent) {
        visited[parent] = true;
        for (int child : graph[parent]) {
            if (visited[child]) {
                continue;
            }
            dfs(child);
            // child 방문이 끝난 이후부터는 부모로 돌아왔으니 작업을 한다.
            dp[parent][0] += dp[child][1];
            dp[parent][1] += Math.min(dp[child][0], dp[child][1]);
            
            // System.out.println("dp[parent][0] : " + dp[parent][0] + ", dp[parent][1] : " + dp[parent][1]);
        }
        
        
    }
}