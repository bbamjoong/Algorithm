import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static Shark shark;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int ans = 0; // 움직인 거리

    static class Shark {
        int size; // 크기
        int eatenFishCnt; // 먹은 물고기 개수
        int x; // 행
        int y; // 열

        public Shark(int x, int y) {
            this.size = 2;
            this.eatenFishCnt = 0;
            this.x = x;
            this.y = y;
        }
    }

    static class Position {
        int x;
        int y;
        int moveCnt;

        public Position(int x, int y, int moveCnt) {
            this.x = x;
            this.y = y;
            this.moveCnt = moveCnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;

                if (num == 9) {
                    shark = new Shark(i, j);
                }
            }
        }

        playGame();
        System.out.println(ans);
    }

    static void playGame() {
        while (true) {
            Position fishPosition = startSingleGame();

            if (fishPosition == null) { // 먹을 물고기 없으면 종료
                break;
            }
            // 먹을 물고기가 있다면 배열에서 해당 위치를 0으로 바꿈
            arr[fishPosition.x][fishPosition.y] = 0;
            // 상어 이동
            shark.x = fishPosition.x;
            shark.y = fishPosition.y;

            ans += fishPosition.moveCnt;

            shark.eatenFishCnt++;
            if (shark.eatenFishCnt == shark.size) {
                shark.size++;
                shark.eatenFishCnt = 0;
            }
        }
    }

    static Position startSingleGame() {
        boolean[][] visited = new boolean[n][n];
        visited[shark.x][shark.y] = true;

        Position rightFishPosition = null;
        int standard = 0; // 가까운 거리 기준

        arr[shark.x][shark.y] = 0;

        ArrayDeque<Position> q = new ArrayDeque<>();
        q.add(new Position(shark.x, shark.y, 0));
        while (!q.isEmpty()) {
            Position pos = q.poll();
            int x = pos.x;
            int y = pos.y;
            int cnt = pos.moveCnt;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 벗어 나면 안됨
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                } // 방문했거나, 물고기가 상어보다 크면 이동못함
                if (visited[nx][ny] || arr[nx][ny] > shark.size) {
                    continue;
                }

                // 방문처리 + 이동횟수 1 추가
                visited[nx][ny] = true;
                int moveCnt = cnt + 1;

                // 물고기가 상어보다 작으면 먹을 수 있음(같으면 못먹음)
                if (0 < arr[nx][ny] && arr[nx][ny] < shark.size) {
                    if (standard == 0) { // 처음 물고기를 먹을 예정임
                        standard = moveCnt; // 거리 기준 정해주고
                        rightFishPosition = new Position(nx, ny, moveCnt); // 먹을 후보!
                    } else if (moveCnt == standard) { // 거리 기준과 움직인 횟수가 같으면
                        if (rightFishPosition.x < nx) { // 기존 물고기가 더 위에 있으면 유지
                            continue;
                            // 새 먹이가 기존 물고기와 높이가 같을 때, 왼쪽에 있으면 교체
                        } else if (rightFishPosition.x == nx && rightFishPosition.y > ny) {
                            rightFishPosition = new Position(nx, ny, moveCnt);
                            // 새 먹이가 기존 물고기보다 높이 있으면 교체
                        } else if (rightFishPosition.x > nx) {
                            rightFishPosition = new Position(nx, ny, moveCnt);
                        }
                    }
                }
                // 모든작업이 끝나면 원소 추가
                q.add(new Position(nx, ny, moveCnt));
            }
        }
        return rightFishPosition;
    }
}