import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int r;
    static int c;
    static char[][] arr;
    static int[][] visited;
    static ArrayDeque<Point> q;
    static int endX;
    static int endY;
    static boolean canEnd = false; // 끝낼 수 있나?

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void setInputs() throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new char[r][c];
        q = new ArrayDeque<>();
        for (int i = 0; i < r; i++) {
            String word = br.readLine();
            for (int j = 0; j < c; j++) {
                char c = word.charAt(j);
                arr[i][j] = c;

                if (c == '*') {
                    q.add(new Point(i, j));
                } else if (c == 'S') {
                    q.addFirst(new Point(i, j));
                } else if (c == 'D') {
                    endX = i;
                    endY = j;
                }
            }
        }
        visited = new int[r][c];
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Point now = q.pop();
            int x = now.x;
            int y = now.y;

            if (arr[endX][endY] == 'S') {
                canEnd = true;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
                    continue;
                }

                // 고슴도치가 다음 칸으로 이동할 때 . D 이면 이동 가능
                if (arr[x][y] == 'S' && (arr[nx][ny] == '.' || arr[nx][ny] == 'D')) {
                    arr[nx][ny] = 'S';
                    visited[nx][ny] = visited[x][y] + 1;
                    q.add(new Point(nx, ny));
                }

                // 물이 다음 칸으로 이동할 때 . S 이면 이동 가능 -> 물이 나중에 움직이니까 고슴도치 칸 잡아먹어도 됨.
                // 어차피 queue에는 고슴도치 칸이 들어있어서 괜찮음.
                else if (arr[x][y] == '*' && (arr[nx][ny] == '.' || arr[nx][ny] == 'S')) {
                    arr[nx][ny] = '*';
                    q.add(new Point(nx, ny));
                }
            }
        }
    }

    static void printAnswer() {
        if (canEnd) {
            System.out.println(visited[endX][endY]);
            return;
        }
        System.out.println("KAKTUS");
    }

    public static void main(String[] args) throws Exception {
        setInputs();
        bfs();
        printAnswer();
    }
}