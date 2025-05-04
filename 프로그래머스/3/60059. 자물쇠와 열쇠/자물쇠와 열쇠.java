import java.util.*;

class Solution {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int lockSize;
    static int keySize;
    static int gridSize;
    static int offset;
    static int totalZeros;

    static int[][] lockGrid;
    static int[][] board;        // 확장된 그리드 (3 * lockSize)
    static int[][][] keyRot;     // [rotation][x][y]
    static boolean[][][] visited; // [x][y][rotation]

    public boolean solution(int[][] key, int[][] lock) {
        lockSize = lock.length;
        keySize = key.length;
        offset = lockSize;
        gridSize = lockSize * 3;

        lockGrid = lock;
        totalZeros = 0;
        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                if (lockGrid[i][j] == 0) totalZeros++;
            }
        }

        // 확장된 board에 lock을 중앙 배치
        board = new int[gridSize][gridSize];
        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                board[offset + i][offset + j] = lockGrid[i][j];
            }
        }

        // key의 회전 상태 미리 계산
        keyRot = new int[4][keySize][keySize];
        keyRot[0] = key;
        for (int r = 1; r < 4; r++) {
            keyRot[r] = rotate(keyRot[r-1]);
        }

        int limit = gridSize - keySize;
        visited = new boolean[limit+1][limit+1][4];
        Deque<int[]> dq = new ArrayDeque<>();

        visited[0][0][0] = true;
        dq.offer(new int[]{0, 0, 0});

        while (!dq.isEmpty()) {
            int[] st = dq.poll();
            int x = st[0], y = st[1], r = st[2];

            if (canUnlock(x, y, r)) return true;

            // 회전
            int nr = (r + 1) % 4;
            if (!visited[x][y][nr]) {
                visited[x][y][nr] = true;
                dq.offer(new int[]{x, y, nr});
            }
            // 이동
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k], ny = y + dy[k];
                if (nx < 0 || ny < 0 || nx > limit || ny > limit) continue;
                if (!visited[nx][ny][r]) {
                    visited[nx][ny][r] = true;
                    dq.offer(new int[]{nx, ny, r});
                }
            }
        }
        return false;
    }

    static boolean canUnlock(int x, int y, int r) {
        int count = 0;
        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                if (keyRot[r][i][j] == 0) continue;
                
                int bx = x + i;
                int by = y + j;
                
                if (board[bx][by] == 1) return false; // 돌기-돌기 충돌
                
                // 중앙 lock 영역의 홈이라면 카운트
                int li = bx - offset;
                int lj = by - offset;
                if (li >= 0 && li < lockSize && lj >= 0 && lj < lockSize) count++;
            }
        }
        return count == totalZeros;
    }

    static int[][] rotate(int[][] arr) {
        int[][] tmp = new int[keySize][keySize];
        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                tmp[j][keySize - 1 - i] = arr[i][j];
            }
        }
        return tmp;
    }
}
