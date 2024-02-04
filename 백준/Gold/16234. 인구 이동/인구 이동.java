import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, l, r;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        setInputs();

        int ans = 0;

        while (true) {
            visited = new boolean[n][n]; // 방문 배열
            boolean stop = true; // 멈출거니..?

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        LinkedList<Pos> arr = bfs(i, j); // bfs를 통해 인구를 이동할 지역들을 받아온다.

                        if (arr.size() > 1) { // 이동할거니?
                            stop = false; // 이동할거면 멈추지 않을게

                            int sum = 0;
                            for (Pos pos : arr) { // 이동할 지역들의 합, 평균 구하기
                                sum += graph[pos.x][pos.y];
                            }
                            int avg = sum / arr.size();

                            for (Pos pos : arr) { // 이동할 지역들을 평균 값으로 갱신
                                graph[pos.x][pos.y] = avg;
                            }
                        }
                    }
                }
            }
            if (stop) {
                break;
            }
            ans++; // 다음 회차로 넘어가
        }

        System.out.println(ans);
    }

    static void setInputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static LinkedList<Pos> bfs(int a, int b) {
        Queue<Pos> q = new LinkedList<>();
        LinkedList<Pos> tmp = new LinkedList<>();

        q.add(new Pos(a, b));
        visited[a][b] = true;
        tmp.add(new Pos(a, b));

        while (!q.isEmpty()) {
            Pos pos = q.poll();
            int x = pos.x;
            int y = pos.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny]) { // 범위 내이고 아직 방문하지 않은 곳이고
                    if (l <= Math.abs(graph[nx][ny] - graph[x][y])
                            && Math.abs(graph[nx][ny] - graph[x][y]) <= r) { // 차이가 l~r 범위 이내이면
                        visited[nx][ny] = true;
                        q.add(new Pos(nx, ny));
                        tmp.add(new Pos(nx, ny));
                    }
                }
            }
        }

        return tmp;
    }

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
