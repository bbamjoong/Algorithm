import java.util.*;

class Solution {
    
    static List<int[]> distPerms; // 순열 결과를 담을 list
    static boolean[] used;
    static int[] perm; // 순열 결과 int 배열
    static int[] extended;
    
    public int solution(int n, int[] weak, int[] dist) {
        int m = weak.length; // weak 배열의 길이
        int d = dist.length; // dist 배열의 길이
        int answer = d + 1;

        extended = new int[m * 2]; // 취약점 위치를 원형으로 처리하기 위해 길이를 두 배로 늘림
        for (int i = 0; i < m; i++) {
            extended[i] = weak[i];
            extended[i + m] = weak[i] + n;
        }
        
        // ### 디버깅
        // System.out.println(Arrays.toString(extended));
        // ###
        
        distPerms = new ArrayList<>(); // dist 배열의 모든 순열을 재귀적으로 생성
        used = new boolean[d];
        perm = new int[d];
        doP(dist, 0);

        for (int[] curDistP : distPerms) { // 순열들을 하나씩 확인
            // ### 디버깅
            // System.out.println(Arrays.toString(cur));
            // ###
            for (int start = 0; start < m; start++) { // 각 취약점을 시작점으로 시도 (취약점은 m개 존재)
                int count = 1;  // 투입한 친구 수
                int limit = extended[start] + curDistP[0]; // 처음 투입된 친구가 커버할 수 있는 최대 위치
                
                // start부터 start + m - 1까지의 취약점 커버
                for (int idx = start; idx < start + m; idx++) {
                    if (extended[idx] > limit) { // 취약점에 친구들을 배치하는데, limit 보다 먼 취약점에만 배치하면됨
                        count++;
                        if (count > d) break;
                        limit = extended[idx] + curDistP[count - 1];
                    }
                }
                
                answer = Math.min(answer, count);
            }
        }

        if (answer > d) return -1;
        return answer;
    }

    static void doP(int[] dist, int depth) { // 순열 생성
        if (depth == dist.length) {
            distPerms.add(perm.clone()); // 배열의 값이 기본형이라 shallow copy도 괜찮음.
            return;
        }
        for (int i = 0; i < dist.length; i++) {
            if (!used[i]) {
                used[i] = true;
                perm[depth] = dist[i];
                doP(dist, depth + 1);
                used[i] = false;
            }
        }
    }
}
