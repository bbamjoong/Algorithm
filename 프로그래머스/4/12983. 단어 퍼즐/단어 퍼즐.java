import java.util.*;

class Solution {
    public int solution(String[] strs, String t) {
        int N = t.length();
        // dist[i] = 길이 i까지 만들 때 쓰는 최소 조각 수 (방문 안 했으면 -1)
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        dist[0] = 0; // 아무것도 만들지 않은 상태에선 0 조각

        while (!q.isEmpty()) {
            int i = q.poll();
            
            if (i == N) {
                return dist[i];
            }

            for (String piece : strs) {
                int len = piece.length();
                int j = i + len;
                
                if (j > N || dist[j] != -1) { // 경계 체크 & 아직 방문 안 한 상태
                    continue;
                }

                boolean match = true;
                for (int k = 0; k < len; k++) {
                    if (t.charAt(i + k) != piece.charAt(k)) {
                        match = false;
                        break;
                    }
                }
                
                if (!match) { // 일치하지 않으면 넘기기
                    continue;
                }

                // 새로운 상태 j를 dist[i]+1 조각으로 방문
                dist[j] = dist[i] + 1;
                q.add(j);
            }
        }

        // 끝까지 N에 못 가면 불가능
        return -1;
    }
}
