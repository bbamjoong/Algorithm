import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int ans; // 답

    public static void main(String[] args) throws Exception {
        int[][] arr = new int[101][101];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int r = x; r < x + 10; r++) {
                for (int c = y; c < y + 10; c++) {
                    arr[c][r] = 1;
                }
            }
        }

        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                calculate(i, j, arr);
            }
        }

        System.out.println(ans);
    }

    static void calculate(int x, int y, int[][] arr) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        if (arr[x][y] == 1) {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (arr[nx][ny] == 0 || nx < 0 || ny < 0 || nx >= 101 || ny >= 101) {
                    ans++;
                }
            }
        }
    }
}