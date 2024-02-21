import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[][] graph;
    static int n;
    static int cnt = 0;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static boolean dfs(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= n) { // 범위 벗어나면 false
            return false;
        }

        if (graph[x][y] == 1) { // 1이면 0으로 바꿈
            graph[x][y] = 0;
            cnt++; // 개수 증가
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                dfs(nx, ny); // dfs
            }
            return true; // 다 바꿨으니 true 반환
        }
        return false; // 조건 만족하지 못하면 false 반환
    }

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            for (int j = 0; j < n; j++) {
                graph[i][j] = word.charAt(j) - '0';
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o1 - o2));

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j)) {
                    index++;
                    pq.add(cnt);
                    cnt = 0;
                }
            }
        }

        sb.append(index).append("\n");
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }

        System.out.println(sb);
    }
}
