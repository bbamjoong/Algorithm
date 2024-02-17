import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m; // 활성 바이러스 개수
    static int[][] arr;
    static ArrayList<Info> infos = new ArrayList<>();

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int zeroCnt = 0;
    static int ans = (int) 1e9;

    static class Info {
        int x;
        int y;
        int cnt;
        boolean selected;

        public Info(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
            this.selected = false;
        }
    }

    static void setInputs() throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
                if (num == 0) {
                    zeroCnt++;
                } else if (num == 2) {
                    infos.add(new Info(i, j));
                }
            }
        }
    }

    static void setVirus(int depth, int idx) {
        if (depth == m) {
            getAnswer();
            return;
        }

        for (int i = idx; i < infos.size(); i++) {
            int infoX = infos.get(i).x;
            int infoY = infos.get(i).y;
            if (arr[infoX][infoY] == 2) {
                arr[infoX][infoY] = 3;
                infos.get(i).selected = true;
                setVirus(depth + 1, i + 1);
                arr[infoX][infoY] = 2;
                infos.get(i).selected = false;
            }
        }
    }

    static void getAnswer() {
        int zeroByBfs = 0;
        int maxCnt = 0;
        Queue<Info> q = new ArrayDeque<>();
        for (Info info : infos) {
            if (info.selected) {
                q.add(new Info(info.x, info.y, 0));
            }
        }

        while (!q.isEmpty()) {
            Info info = q.poll();
            int x = info.x;
            int y = info.y;
            int cnt = info.cnt;

            if (cnt == ans) { // 이미 답안과 cnt가 같아지면 더 구할 필요가 없음
                break;
            }

            if (zeroByBfs == zeroCnt) { // 0이 다 없어 졌으면
                ans = Math.min(ans, maxCnt);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) { // 범위 벗어나면 안됨
                    continue;
                }

                if (arr[nx][ny] == 1 || arr[nx][ny] == 3 || arr[nx][ny] == 4 || arr[nx][ny] == 5) { // 벽이거나 이미 활성화된 곳 X
                    continue;
                } else if (arr[nx][ny] == 2) { // 비활성 바이러스는 활성화
                    arr[nx][ny] = 4; // 비활성 바이러스 -> 활성 바이러스는 5로 변경
                } else if (arr[nx][ny] == 0) { // 빈 곳이면 5로
                    arr[nx][ny] = 5;
                    zeroByBfs++; // 0을 제거한 카운트를 늘려줌
                }
                q.add(new Info(nx, ny, cnt + 1));
                maxCnt = Math.max(maxCnt, cnt + 1);
            }
        }

        restoreArr();
    }

    static void restoreArr() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 4) {
                    arr[i][j] = 2;
                } else if (arr[i][j] == 5) {
                    arr[i][j] = 0;
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        setInputs();
        setVirus(0, 0);

        if (ans == (int) 1e9) {
            System.out.println(-1);
            return;
        }
        System.out.println(ans);
    }
}