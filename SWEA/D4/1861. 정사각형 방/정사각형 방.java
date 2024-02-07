import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int[][] arr;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};


    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            sb.append("#").append(tc).append(" ");

            n = Integer.parseInt(br.readLine());

            arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int roomNumber = Integer.MAX_VALUE;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int tmp = bfs(i, j, 1);

                    if (tmp > cnt) { // 구한 값이 답안 cnt보다 클 때
                        cnt = tmp;
                        roomNumber = arr[i][j];
                    } else if ((tmp == cnt) && (arr[i][j] < roomNumber)) { // 구한 값과 cnt가 같으면 더 작은 방 숫자 출력하기
                        roomNumber = arr[i][j];
                    }
                }
            }
            sb.append(roomNumber).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    static int bfs(int x, int y, int cnt) {
        int nx;
        int ny;
        while (true) {
            boolean findNextRoom = false;
            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }

                if (arr[nx][ny] - arr[x][y] == 1) { // 1이다?
                    cnt++;
                    x = nx; // x 좌표 갱식
                    y = ny; // y 좌표 갱신
                    findNextRoom = true; // 다음 방을 찾았습니다.
                }
            }

            if (!findNextRoom) { // 만약 다음 방을 못찾았다면 break 해야죠
                break;
            }
        }
        return cnt;
    }
}