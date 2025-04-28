import java.util.*;

class Solution {
    
    static List<int[]> li;
    static int[] parent;
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if (a < b) parent[b] = parent[a];
        else parent[a] = parent[b];
    }
    
    public int solution(int n, int[][] costs) {
        li = new ArrayList<>();
        for (int i = 0; i < costs.length; i++) {
            li.add(new int[] {costs[i][0], costs[i][1], costs[i][2]});
        }
        parent = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            parent[i] = i;
        }
        
        Collections.sort(li, (a, b) -> {return a[2] - b[2];});
        
        int cnt = 0;
        int answer = 0;
        for (int i = 0; i < li.size(); i++) {
            int[] info = li.get(i);
            int a = info[0];
            int b = info[1];
            int w = info[2];
            
            if (find(a) != find(b)) {
                union(a, b);
                cnt++;
                answer += w;
            }
            if (cnt == n-1) break;
        }
        
        return answer;
    }
}