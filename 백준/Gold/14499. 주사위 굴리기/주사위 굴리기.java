import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, x, y, k;
    static int[][] graph;
    static int[] action;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[] dice = new int[7];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        action = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            action[i] = Integer.parseInt(st.nextToken());
        }

        for (int i : action) {
            int nx = x + dx[i - 1];
            int ny = y + dy[i - 1];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                x = nx;
                y = ny;
                turn(i);
            }
        }
    }

    static void compare() {
        if (graph[x][y] == 0) {
            graph[x][y] = dice[6];
        } else {
            dice[6] = graph[x][y];
            graph[x][y] = 0;
        }
    }

    static void turn(int direction) {
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

        compare();
        System.out.println(dice[3]);
    }

    private static void swapValues(int a, int b, int c) {
        int tmp = dice[3];
        dice[3] = dice[a];
        dice[a] = dice[b];
        dice[b] = dice[c];
        dice[c] = tmp;
    }
}