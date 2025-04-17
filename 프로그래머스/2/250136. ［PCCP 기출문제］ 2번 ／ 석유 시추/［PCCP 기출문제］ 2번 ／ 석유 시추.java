import java.util.*;
public class Solution {

    static int[] parent;
    static int[] oil;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if (a == b) {
            return;
        }
        
        if (a < b) {
            parent[b] = a;
            oil[a] += oil[b];
        } else {
            parent[a] = b;
            oil[b] += oil[a];
        }
    }

    public int solution(int[][] land) {
        int depth = land.length;
        int width = land[0].length;
        int size = depth * width;

        parent = new int[size];
        oil = new int[size];

        // 초기화
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < size; i++) {
            int row = i / width;
            int col = i % width;

            if (land[row][col] == 0) {
              continue;  
            }

            oil[i] = 1; // 자기 자신이 석유 1칸

            if (row > 0 && land[row - 1][col] == 1) { // 위쪽 체크
                int up = i - width;
                union(i, up);
            }

            // 좌: i - 1 (단, 같은 행인지 확인 필요)
            if (col > 0 && land[row][col - 1] == 1) { // 왼쪽 체크
                int left = i - 1;
                union(i, left);
            }
        }


        int answer = 0;

        for (int col = 0; col < width; col++) {
            Set<Integer> usedGroups = new HashSet<>();
            
            int total = 0;
            for (int row = 0; row < depth; row++) {
                if (land[row][col] == 1) {
                    int idx = row * width + col;
                    int group = find(idx);

                    if (!usedGroups.contains(group)) {
                        usedGroups.add(group);
                        total += oil[group];
                    }
                }
            }
            
            answer = Math.max(answer, total);
        }

        return answer;
    }
}
