import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int r;
    static int c;
    static int[][] arr;
    static int visited;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int ans;

    static void setInputs() throws Exception {
        ans = 1;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[r][c];
        for (int i = 0; i < r; i++) {
            String word = br.readLine();
            for (int j = 0; j < c; j++) {
                arr[i][j] = word.charAt(j) - 'A';
            }
        }
    }

    static void dfs(int x, int y, int cnt) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= r || ny >= c) { // 범위 밖 x
                continue;
            }

            if ((visited & (1 << arr[nx][ny])) > 0) { // 이미 방문한곳 x
                continue;
            }

            visited |= 1 << arr[nx][ny]; // 방문처리
            cnt++;
            ans = Math.max(ans, cnt); // 답 갱신

            dfs(nx, ny, cnt);

            // 백트래킹
            cnt--;
            //비트 해제
            visited &= ~(1 << arr[nx][ny]);
        }
    }

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < t + 1; tc++) {
            setInputs();

            visited = 1 << arr[0][0]; // i, j 방문처리
            dfs(0, 0, 1); // 1행 1열부터 시작

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
}