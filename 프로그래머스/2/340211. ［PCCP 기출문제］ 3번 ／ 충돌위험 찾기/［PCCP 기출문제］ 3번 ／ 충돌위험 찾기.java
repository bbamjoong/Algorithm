import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int numRobots = routes.length;
        int m = routes[0].length;
        final int TIME_LIMIT = 20000; // r, c = 100 최대 100번움직임. -> 20000이면 충분.

        // 1) 위치 기록 배열: [로봇번호][시간][0:r,1:c], 초기값 -1
        int[][][] pos = new int[numRobots][TIME_LIMIT][2];
        for (int i = 0; i < numRobots; i++) {
            for (int t = 0; t < TIME_LIMIT; t++) {
                pos[i][t][0] = -1;
                pos[i][t][1] = -1;
            }
        }

        // 2) 시뮬레이션: 각 로봇 별로 시간 순 좌표 채우기        
        for (int i = 0; i < numRobots; i++) {
            int t = 0;
            int curIdx = routes[i][0] - 1; // 로봇은 0번부터 idx 매겼음.
            int curR = points[curIdx][0];
            int curC = points[curIdx][1];
            
            pos[i][t][0] = curR;
            pos[i][t][1] = curC;
            t++;

            // 각 구간마다 r 먼저, c 다음으로 이동
            for (int j = 1; j < m; j++) { // i번째로봇의 타겟을 전부 순회
                int target = routes[i][j] - 1;
                int targetR = points[target][0];
                int targetC = points[target][1];
                
                while (curR != targetR) { // row 움직이기
                    if (curR < targetR) {
                        curR += 1;
                    } else {
                        curR -= 1;
                    }
                    
                    pos[i][t][0] = curR;
                    pos[i][t][1] = curC;
                    t++;
                }
                while (curC != targetC) { // col 움직이기
                    if (curC < targetC) {
                        curC += 1;
                    } else {
                        curC -= 1;
                    }
                                        
                    pos[i][t][0] = curR;
                    pos[i][t][1] = curC;
                    t++;
                }
            }
        }

        // 3) 시간별로 충돌 횟수 집계
        int collisions = 0;
        int[][] freq = new int[101][101];
        List<int[]> used = new ArrayList<>();

        for (int t = 0; t < TIME_LIMIT; t++) { // 시간별로
            used = new ArrayList<>();
            for (int i = 0; i < numRobots; i++) { // 로봇 충돌 검사
                int r = pos[i][t][0]; // i번째로봇의 t시간의 r좌표
                int c = pos[i][t][1]; // i번째로봇의 t시간의 c좌표
                
                if (r != -1) { // 끝났으면 좌표는 (-1, -1)로 설정했음
                    if (freq[r][c] == 0) {
                        used.add(new int[]{r, c});
                    }
                    freq[r][c]++;
                }
            }
            
            for (int[] p : used) { // 빈도가 2 이상인 좌표 찾기
                if (freq[p[0]][p[1]] > 1) {
                    collisions++;
                }
                freq[p[0]][p[1]] = 0; // freq 원복
            }
        }

        return collisions;
    }
}
