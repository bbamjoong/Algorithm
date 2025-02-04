import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int f;
    static int s;
    static int g;
    static int u;
    static int d;

    static int[] dy;
    static boolean[] visited;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int ans = -1;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        dy = new int[]{u, -d};
        visited = new boolean[f + 1];

        ArrayDeque<int[]> q = new ArrayDeque();
        q.add(new int[]{s, 0});

        // queue가 빌때까지 탐색 ㄱㄱ
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int now = p[0];
            int cnt = p[1];

            if (now == g) {
                ans = cnt;
                break;
            }

            for (int i = 0; i < 2; i++) {
                int next = now + dy[i];

                // 범위 벗어나면 안됨
                if (next <= 0 || next > f) {
                    continue;
                }

                if (visited[next]) {
                    continue;
                }

                visited[next] = true;
                q.add(new int[]{next, cnt + 1});
            }
        }

        if (ans == -1) {
            System.out.println("use the stairs");
            return;
        }
        System.out.println(ans);
    }
}