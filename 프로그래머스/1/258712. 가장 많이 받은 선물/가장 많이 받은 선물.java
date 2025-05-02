import java.util.*;

class Solution {
    static int n;
    static int[] giftDegree;
    static int[][] giftGraph; // 입출력 예시 표
    
    public int solution(String[] friends, String[] gifts) {
        n = friends.length;
        Map<String, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < n; i++) idxMap.put(friends[i], i); // index기록

        giftDegree = new int[n];
        giftGraph = new int[n][n];

        for (String gift : gifts) {
            String[] g = gift.split(" ");
            int from = idxMap.get(g[0]);
            int to = idxMap.get(g[1]);
            
            giftDegree[from]++;
            giftDegree[to]--;
            giftGraph[from][to]++;
        }

        return cal();
    }

    private int cal() {
        int max = 0;
        
        for (int i = 0; i < n; i++) {
            int count = 0;
            
            for (int j = 0; j < n; j++) {
                if (i == j) continue; // 본인이면 제외
                
                // 문제 조건
                if (giftGraph[i][j] > giftGraph[j][i] ||
                    (giftGraph[i][j] == giftGraph[j][i] && giftDegree[i] > giftDegree[j])) {
                    count++;
                }
            }
            
            max = Math.max(max, count); // 선물 지수 비교해서 높은 것을 저장
        }
        return max;
    }
}