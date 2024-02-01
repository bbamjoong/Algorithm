import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] arr;
    static ArrayList<Cloud> clouds = new ArrayList<>();

    static boolean[][] existClouds;
    static int ans;

    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        setInitialClouds(); // 구름 추가

        for (int i = 0; i < m; i++) {
            existClouds = new boolean[n][n];
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int cnt = Integer.parseInt(st.nextToken());
            moveCloudAndAddWater(dir, cnt); // 구름 이동 + 물 추가
            bugWater(); // 물복사 버그!
            remakeCloud(); // 구름 재생성
        }
        calculate(); // 답 계산

        StringBuilder sb = new StringBuilder();
        sb.append(ans);
        System.out.println(sb);
    }

    static void setInitialClouds() {
        clouds.add(new Cloud(n - 1, 0));
        clouds.add(new Cloud(n - 1, 1));
        clouds.add(new Cloud(n - 2, 0));
        clouds.add(new Cloud(n - 2, 1));
    }

    static void moveCloudAndAddWater(int dir, int cnt) {
        for (Cloud cloud : clouds) {
            // 모듈러 연산으로 n-1 초과 방지
            cloud.x = (cloud.x + dx[dir] * cnt) % n;
            cloud.y = (cloud.y + dy[dir] * cnt) % n;

            // 좌표가 음수면 n 더해주기
            if (cloud.x < 0) {
                cloud.x += n;
            }
            if (cloud.y < 0) {
                cloud.y += n;
            }

            arr[cloud.x][cloud.y]++; // 물 추가
            existClouds[cloud.x][cloud.y] = true; // 구름 존재여부 체크
        }
    }

    static void bugWater() {
        for (Cloud cloud : clouds) {
            int x = cloud.x;
            int y = cloud.y;
            int cnt = 0; // 물복사버그로 추가될 물의 양

            for (int i = 1; i < 8; i += 2) { // 1, 3, 5, 7이 대각선입니다.
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) { // 범위 벗어나면 continue;
                    continue;
                }

                if (arr[nx][ny] > 0) { // 물이 존재하면 cnt++
                    cnt++;
                }
            }
            arr[x][y] += cnt; // 물복사버그!!
        }
    }

    static void remakeCloud() {
        clouds.clear(); // 구름 ArrayList 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (existClouds[i][j] || arr[i][j] < 2) { // 구름이 존재하거나 물이 2보다 적으면 continue
                    continue;
                }
                arr[i][j] -= 2; // 물의 양 2 감소
                clouds.add(new Cloud(i, j)); // 새로 구름 추가
            }
        }
    }

    static void calculate() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += arr[i][j];
            }
        }
    }

    static class Cloud {
        int x;
        int y;

        Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}