import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        sort(data);
        return cal(data);
    }

    // x기준으로 오름차순. 단 y도 이후 오름차순.
    private void sort(int[][] data) {
        Arrays.sort(data, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
    }

    private int cal(int[][] data) {
        int ans = 0;
        int m = data.length;

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (isParallel(data[i], data[j])) continue;
                if (isEmptyInterior(i, j, data)) ans++;
            }
        }
        return ans;
    }

    private boolean isParallel(int[] p, int[] q) {
        return p[0] == q[0] || p[1] == q[1];
    }

    private boolean isEmptyInterior(int i, int j, int[][] data) {
        int x1 = data[i][0];
        int y1 = data[i][1];
        int x2 = data[j][0];
        int y2 = data[j][1];
        int ymin = Math.min(y1, y2);
        int ymax = Math.max(y1, y2);

        for (int k = i + 1; k < j; k++) {
            int xk = data[k][0];
            int yk = data[k][1];
            if (x1 < xk && xk < x2 && ymin < yk && yk < ymax) return false;
        }
        return true;
    }
}
