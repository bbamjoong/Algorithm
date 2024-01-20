import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int x;
    static int y;
    static int k;
    static int[][] arr;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[] dice = new int[7];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            sb.setLength(0);
            int direction = Integer.parseInt(st.nextToken());
            roll(direction);
        }
    }

    static void roll(int direction) {
        int nx = x + dx[direction - 1];
        int ny = y + dy[direction - 1];

        if (nx < 0 | ny < 0 | nx >= n | ny >= m) {
            return;
        } else {
            x = nx;
            y = ny;
            changeDice(direction);
            checkCondition();
            sb.append(dice[3]);
            System.out.println(sb.toString());
        }

    }

    private static void changeDice(int direction) {
        switch (direction) {
            case 1:
                swapValues(2, 6, 4);
                break;
            case 2:
                swapValues(4, 6, 2);
                break;
            case 3:
                swapValues(5, 6, 1);
                break;
            case 4:
                swapValues(1, 6, 5);
                break;
        }
    }

    private static void swapValues(int a, int b, int c) {
        int tmp = dice[3];
        dice[3] = dice[a];
        dice[a] = dice[b];
        dice[b] = dice[c];
        dice[c] = tmp;
    }

    private static void checkCondition() {
        if (arr[x][y] == 0) {
            arr[x][y] = dice[6];
        } else {
            dice[6] = arr[x][y];
            arr[x][y] = 0;
        }
    }
}