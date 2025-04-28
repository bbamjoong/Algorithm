import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static class Robot {
        int tailX;
        int tailY;
        int headX;
        int headY;
        int dir;
        int cnt;

        public Robot(int tailX, int tailY, int headX, int headY, int dir, int cnt) {
            this.tailX = tailX;
            this.tailY = tailY;
            this.headX = headX;
            this.headY = headY;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    static int n;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] board) {
        n = board.length;
        map = board;
        return bfs();
    }

    static int bfs() {
        Queue<Robot> q = new LinkedList<>();
        boolean[][][] visited = new boolean[n][n][2];
        q.add(new Robot(0, 0, 1, 0, 0, 0));

        while (!q.isEmpty()) {
            Robot robot = q.poll();
            int tailX = robot.tailX;
            int tailY = robot.tailY;
            int headX = robot.headX;
            int headY = robot.headY;
            int dir = robot.dir;
            int cnt = robot.cnt;

            if ((tailX == n - 1 && tailY == n - 1) || (headX == n - 1 && headY == n - 1)) {
                return cnt;
            }

            if (visited[tailY][tailX][dir] && visited[headY][headX][dir]) {
                continue;
            }
            visited[tailY][tailX][dir] = true;
            visited[headY][headX][dir] = true;

            for (int d = 0; d < 4; d++) {
                int nTailX = tailX + dx[d];
                int nTailY = tailY + dy[d];
                int nHeadX = headX + dx[d];
                int nHeadY = headY + dy[d];

                if (check(nTailX, nTailY, nHeadX, nHeadY)) {
                    q.add(new Robot(nTailX, nTailY, nHeadX, nHeadY, dir, cnt + 1));
                }
            }

            if (dir == 0) {
                if (check(tailX, tailY - 1, headX, headY - 1)) {
                    q.add(new Robot(headX, headY, headX, headY - 1, 1, cnt + 1));
                    q.add(new Robot(tailX, tailY, tailX, tailY - 1, 1, cnt + 1));
                }
                if (check(tailX, headY + 1, headX, headY + 1)) {
                    q.add(new Robot(headX, headY, headX, headY + 1, 1, cnt + 1));
                    q.add(new Robot(tailX, tailY, tailX, tailY + 1, 1, cnt + 1));
                }

            } else if (dir == 1) {
                if (check(tailX + 1, tailY, headX + 1, headY)) {
                    q.add(new Robot(headX, headY, headX + 1, headY, 0, cnt + 1));
                    q.add(new Robot(tailX, tailY, tailX + 1, tailY, 0, cnt + 1));
                }
                if (check(tailX - 1, tailY, headX - 1, headY)) {
                    q.add(new Robot(headX, headY, headX - 1, headY, 0, cnt + 1));
                    q.add(new Robot(tailX, tailY, tailX - 1, tailY, 0, cnt + 1));
                }
            }
        }
        return -1;
    }


    static boolean check(int x1, int y1, int x2, int y2) {
        return x1 >= 0 && x2 >= 0 && x1 <= n - 1 && x2 <= n - 1
                && y1 >= 0 && y2 >= 0 && y1 <= n - 1 && y2 <= n - 1
                && map[y1][x1] != 1 && map[y2][x2] != 1;
    }
}