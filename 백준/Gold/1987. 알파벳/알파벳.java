import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] arr;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int visited;
    static int ans = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = word.charAt(j) - 'A';
            }
        }

        visited = 1 << arr[0][0]; // i, j 방문처리
        dfs(0, 0, 1);

        System.out.println(ans);
    }

    static void dfs(int x, int y, int cnt) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) { // 범위 벗어나면 안됨
                continue;
            }

            if ((visited & (1 << arr[nx][ny])) > 0) { // 방문한적 있으면 안됨
                continue;
            }

            visited |= 1 << arr[nx][ny]; // 방문처리
            cnt++;
            ans = Math.max(ans, cnt); // 다음칸을 방문할 수 있으면 답을 갱신해줍니다.

            dfs(nx, ny, cnt); // 카운트 하나 늘려주고 들어감

            cnt--;
            visited &= ~(1 << arr[nx][ny]); // 갈 곳이 없어서 다시 나왔으면 방문해제
        }
    }
}