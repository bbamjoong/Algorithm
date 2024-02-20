import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int k; // 공사 깊이
    static int maxHeight; // 가장 높은 곳
    static int ans;
    static int[][] arr;
    static boolean[][] visited;


    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < T + 1; tc++) {
            sb.append("#").append(tc).append(" ");

            setInputs();
            makeTrail();

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void setInputs() throws Exception {
        ans = 0;
        maxHeight = 0;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
                maxHeight = Math.max(maxHeight, num);
            }
        }
    }

    static void makeTrail() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == maxHeight) { // 봉우리에서
                    visited[i][j] = true;
                    backTrack(i, j, 1, true); // 등산로 조성 시작
                    visited[i][j] = false;
                }
            }
        }
    }

    static void backTrack(int x, int y, int cnt, boolean canCut) {
        ans = Math.max(ans, cnt);
        for (int i = 0; i < 4; i++) { // 4방탐색
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) { // 범위 밖 X
                continue;
            }

            if (visited[nx][ny]) { // 이미 방문한 곳은 재방문 X
                continue;
            }

            if (arr[nx][ny] < arr[x][y]) { // 다음 이동할 곳이 더 낮은 곳
                visited[nx][ny] = true;
                backTrack(nx, ny, cnt + 1, canCut);
                visited[nx][ny] = false;
            }
            // 깎아서 갈 수 있는 길이고, 한번도 깎지 않았다면 깎아서 탐색
            if (canCut) {
                for (int cutAmount = 1; cutAmount <= k; cutAmount++) { // 1 ~ k 까지 깎을 수 있음
                    if ((arr[nx][ny] - cutAmount) < arr[x][y]) { // 다음 위치를 잘랐을 때 현재 위치보다 낮아야 함
                        arr[nx][ny] -= cutAmount; // 높이 깎고
                        backTrack(x + dx[i], y + dy[i], cnt + 1, false);
                        arr[nx][ny] += cutAmount; // 높이 복원
                    }
                }
            }
        }
    }
}