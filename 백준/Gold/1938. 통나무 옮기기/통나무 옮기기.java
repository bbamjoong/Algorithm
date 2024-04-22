import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[][] arr;

    static Train start;
    static Train end;

    static boolean[][][] visited;

    static int[] dx = {0, -1, 0, 1, -1, -1, 1, 1};
    static int[] dy = {-1, 0, 1, 0, -1, 1, -1, 1};

    static int ans;

    static class Train {
        int x;
        int y;
        int direction; // 0은 가로, 1은 세로
        int cnt;

        public Train(int x, int y, int direction) {
            super();
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.cnt = 0;
        }

        public Train(int x, int y, int direction, int cnt) {
            super();
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.cnt = cnt;
        }
    }

    static boolean checkInvalidRange(int x, int y) {
        if (x < 0 || y < 0 || x >= n || y >= n) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        ans = 0;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        ArrayList<int[]> bList = new ArrayList<>();
        ArrayList<int[]> eList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            for (int j = 0; j < n; j++) {
                char c = word.charAt(j);

                if (c == 'B') {
                    bList.add(new int[]{i, j});
                    arr[i][j] = 0;
                } else if (c == 'E') {
                    eList.add(new int[]{i, j});
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = c - '0';
                }
            }
        }

        // 가로로 놓여져있으면
        if (bList.get(0)[0] == bList.get(1)[0]) {
            start = new Train(bList.get(1)[0], bList.get(1)[1], 0);
            // 세로로 놓여져있으면
        } else {
            start = new Train(bList.get(1)[0], bList.get(1)[1], 1);
        }

        if (eList.get(0)[0] == eList.get(1)[0]) {
            end = new Train(eList.get(1)[0], eList.get(1)[1], 0);
            // 세로로 놓여져있으면
        } else {
            end = new Train(eList.get(1)[0], eList.get(1)[1], 1);
        }

        // bfs를 돌릴거임. 가로, 세로 기준.
        visited = new boolean[n][n][2];
        ArrayDeque<Train> q = new ArrayDeque<>();

        visited[start.x][start.y][start.direction] = true;
        q.add(start);

        while (!q.isEmpty()) {
            Train train = q.poll();
            int x = train.x;
            int y = train.y;
            int direction = train.direction;
            int cnt = train.cnt;

            if (x == end.x && y == end.y && direction == end.direction) {
                ans = cnt;
                break;
            }

            // 방향 옮기기
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 벗어나면 안됨
                if (direction == i % 2) {
                    int checkNx = nx + dx[i];
                    int checkNy = ny + dy[i];
                    if (checkInvalidRange(checkNx, checkNy)) {
                        continue;
                    }

                    // 이동할 곳이 1이면 안됨
                    if (arr[checkNx][checkNy] == 1) {
                        continue;
                    }

                } else {
                    if (checkInvalidRange(nx, ny)) {
                        continue;
                    }

                    // 이동할 곳이 1이면 안됨
                    if (direction == 0) {
                        if (arr[nx][ny] == 1 || arr[nx][ny - 1] == 1 || arr[nx][ny + 1] == 1) {
                            continue;
                        }
                    } else if (direction == 1) {
                        if (arr[nx][ny] == 1 || arr[nx - 1][ny] == 1 || arr[nx + 1][ny] == 1) {
                            continue;
                        }
                    }

                }

                // 이미 방문한 곳은 다시 안감
                if (visited[nx][ny][direction]) {
                    continue;
                }

                visited[nx][ny][direction] = true;
                q.add(new Train(nx, ny, direction, cnt + 1));
            }

            // 회전
            boolean canRotate = true;
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (checkInvalidRange(nx, ny) || arr[nx][ny] == 1) {
                    canRotate = false;
                    break;
                }
            }

            if (canRotate) {
                if (!visited[x][y][(direction + 1) % 2]) {
                    visited[x][y][(direction + 1) % 2] = true;
                    q.add(new Train(x, y, (direction + 1) % 2, cnt + 1));
                }
            }
        }

        System.out.println(ans);
    }
}
