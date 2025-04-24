import java.util.*;

public class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int dist = Math.abs(x - r) + Math.abs(y - c);
        
        if (dist > k || (k - dist) % 2 != 0) {
            return "impossible";
        }

        // 3) 방향과 델타를 사전순(d < l < r < u)에 맞춰 정의
        char[]   dirs = { 'd', 'l', 'r', 'u' };
        int[][]  dx   = { {  1,  0 },  // d  아래
                          {  0, -1 },  // l  왼쪽
                          {  0,  1 },  // r  오른쪽
                          { -1,  0 }   // u  위
                        };

        StringBuilder answer = new StringBuilder();
        int cx = x, cy = y;

        int rem = k;
        while (rem > 0) {
            for (int i = 0; i < dirs.length; i++) {
                int nx = cx + dx[i][0];
                int ny = cy + dx[i][1];

                if (nx < 1 || nx > n || ny < 1 || ny > m) {
                    continue;
                }

                int ndist = Math.abs(nx - r) + Math.abs(ny - c);
                if (ndist <= rem - 1 && ((rem - 1 - ndist) % 2 == 0)) {
                    answer.append(dirs[i]);
                    cx = nx;
                    cy = ny;
                    rem--;
                    break;
                }
            }
        }

        return answer.toString();
    }
}