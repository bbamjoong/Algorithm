import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int[][] arr;
    static boolean[][] visited;
    static int ans;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static class Pos implements Comparable<Pos> {
        int x;
        int y;
        int time;

        public Pos(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Pos p) { // 복구시간 기준으로 오름차순 정렬
            return this.time - p.time;
        }
    }

    static void setInputs() throws IOException {
        ans = 0;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = word.charAt(j) - '0';
            }
        }
        visited = new boolean[n][n];
    }

    static void bfs() {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        // 0, 0추가해주기
        visited[0][0] = true;
        pq.add(new Pos(0, 0, 0));

        while (!pq.isEmpty()) {
            Pos info = pq.poll();
            int x = info.x;
            int y = info.y;
            int time = info.time;

            if (x == n - 1 && y == n - 1) { // pq썼으니까 먼저 도달하는게 정답임.
                ans = time;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) { // 범위 벗어나면 안됨.
                    continue;
                }

                if (visited[nx][ny]) { // 이미 방문한 곳 안됨.
                    continue;
                }

                visited[nx][ny] = true;
                pq.add(new Pos(nx, ny, time + arr[nx][ny]));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            setInputs();
            bfs();
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.printf(sb.toString());
    }
}