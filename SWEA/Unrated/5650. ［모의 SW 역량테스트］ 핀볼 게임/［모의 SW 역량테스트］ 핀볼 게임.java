import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    static int[][] graph;
    static int[][] wormhole;
    static int ans;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] changeDirection = {{0, 0, 0, 0}, {1, 2, 3, 0}, {1, 3, 0, 2},
            {3, 0, 1, 2}, {2, 0, 3, 1}, {1, 0, 3, 2}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        for (int test_case = 1; test_case <= T; test_case++) {
            ans = 0;
            sb.setLength(0);
            n = Integer.parseInt(br.readLine().trim());

            wormhole = new int[5][4];
            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < 4; ++j) {
                    wormhole[i][j] = -1;
                }
            }

            graph = new int[n][n];

            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < n; j++) {
                    int inputNum = Integer.parseInt(st.nextToken());
                    graph[i][j] = inputNum;
                    if (inputNum >= 6) {
                        if (wormhole[graph[i][j] - 6][0] == -1) {
                            wormhole[graph[i][j] - 6][0] = i;
                            wormhole[graph[i][j] - 6][1] = j;
                        } else {
                            wormhole[graph[i][j] - 6][2] = i;
                            wormhole[graph[i][j] - 6][3] = j;
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] == 0) {
                        for (int direction = 0; direction < 4; direction++) {
                            int cntcnt = check(i, j, direction);
                            ans = Math.max(cntcnt, ans);
                        }
                    }
                }
            }
            sb.append("#" + test_case + " " + ans);
            System.out.println(sb);
        }
    }

    static int check(int x, int y, int direction) {
        int cnt = 0;
        int nx = x;
        int ny = y;

        while (true) {
            nx += dx[direction];
            ny += dy[direction];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                if (direction % 2 == 0) {
                    direction++;
                } else {
                    direction--;
                }
                cnt++;
                continue;
            }
            if ((graph[nx][ny] == -1) || (nx == x && ny == y)) {
                return cnt;
            }

            if (graph[nx][ny] >= 1 && graph[nx][ny] <= 5) {
                int num = graph[nx][ny];
                direction = changeDirection[num][direction];
                cnt++;
            } else if (graph[nx][ny] > 5) {
                int num = graph[nx][ny];
                if (wormhole[num - 6][0] == nx && wormhole[num - 6][1] == ny) {
                    nx = wormhole[num - 6][2];
                    ny = wormhole[num - 6][3];
                } else {
                    nx = wormhole[num - 6][0];
                    ny = wormhole[num - 6][1];
                }
            }
        }
    }
}