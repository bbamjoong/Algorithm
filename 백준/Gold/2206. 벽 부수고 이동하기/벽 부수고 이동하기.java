import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int m;
    static int n;

    static int[][] arr;
    static boolean[][][] visited;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int ans = -1;

    static class Node {
        int x, y, wall, cnt;

        public Node(int x, int y, int wall, int cnt) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.cnt = cnt;
        }
    }

    static void setInputs() throws Exception {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            String word = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = word.charAt(j) - '0';
            }
        }
        visited = new boolean[m][n][2];
    }

    static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;
        visited[0][0][1] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int wall = node.wall;
            int cnt = node.cnt;

            if (x == m - 1 && y == n - 1) {
                ans = cnt + 1;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) { // 범위 벗어나면 안됨
                    continue;
                }

                // 벽을 만남. 깬적없음.
                if (arr[nx][ny] == 1 && !visited[nx][ny][1] && wall == 0) {
                    visited[nx][ny][1] = true;
                    q.add(new Node(nx, ny, wall + 1, cnt + 1));
                }

                // 빈 칸을 만남. 방문한 적 없음
                else if (arr[nx][ny] == 0 && !visited[nx][ny][0] && wall == 0) {
                    visited[nx][ny][0] = true;
                    q.add(new Node(nx, ny, wall, cnt + 1));
                }

                // 빈 칸을 만남. 벽을 이미 깼음
                else if (arr[nx][ny] == 0 && !visited[nx][ny][1] && wall == 1) {
                    visited[nx][ny][1] = true;
                    q.add(new Node(nx, ny, wall, cnt + 1));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        setInputs();
        bfs();
        System.out.println(ans);
    }

}
