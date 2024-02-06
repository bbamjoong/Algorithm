import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int r;
    static int[][] matrix;
    static int startX = 0;
    static int startY = 0;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 행
        m = Integer.parseInt(st.nextToken()); // 열
        r = Integer.parseInt(st.nextToken()); // 횟수

        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int squareCnt = Math.min(n, m) / 2;
        for (int i = 0; i < squareCnt; i++) {
            for (int j = 0; j < r % (((n - 1) + (m - 1)) * 2); j++) {
                rotate();
            }
            startX++;
            startY++;
            n -= 2;
            m -= 2;
        }
        startX -= squareCnt;
        startY -= squareCnt;
        n += 2 * squareCnt;
        m += 2 * squareCnt;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void rotate() {
        int x = startX;
        int y = startY;
        int tmp = matrix[x][y];

        for (int i = 0; i < n - 1; i++) {
            int tmp2 = matrix[++x][y];
            matrix[x][y] = tmp;
            tmp = tmp2;
        }

        for (int i = 0; i < m - 1; i++) {
            int tmp2 = matrix[x][++y];
            matrix[x][y] = tmp;
            tmp = tmp2;
        }

        for (int i = 0; i < n - 1; i++) {
            int tmp2 = matrix[--x][y];
            matrix[x][y] = tmp;
            tmp = tmp2;
        }

        for (int i = 0; i < m - 1; i++) {
            int tmp2 = matrix[x][--y];
            matrix[x][y] = tmp;
            tmp = tmp2;
        }
    }
}