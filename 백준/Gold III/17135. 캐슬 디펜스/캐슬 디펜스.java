import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int d;
    static int[][] arr;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static final int DOWN = 0;
    static int[] archers = new int[3];
    static int ans;
    static int enemyCount; // 남은 적의 수

    static class Position {
        int x;
        int y;
        int cnt; // 움직인 거리

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
            this.cnt = 0;
        }

        public Position(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        arr = new int[n + 1][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;

                if (num == 1) {
                    enemyCount++;
                }
            }
        }

        // 궁수를 배치한다.
        setArcher(0, 0);
        System.out.println(ans);
    }

    static void setArcher(int depth, int idx) { // 궁수 배치
        if (depth == 3) {
            int[][] tmpArr = cloneArr(arr);
            playGame(enemyCount, tmpArr);
            return;
        }

        for (int i = idx; i < m; i++) {
            arr[n][depth] = 2;
            archers[depth] = i;
            setArcher(depth + 1, i + 1);
            arr[n][depth] = 0;
            archers[depth] = 0;
        }
    }

    static int[][] cloneArr(int[][] arr) {
        int[][] tmpArr = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            tmpArr[i] = arr[i].clone();
        }
        return tmpArr;
    }

    static void playGame(int leftEnemyCount, int[][] arr) {
        int tmpAns = 0;
        while (leftEnemyCount != 0) {
            // 타격 - 궁수 1명마다
            int killCnt = 0;
            ArrayList<Position> enemies = new ArrayList<>(); // 타격한 적의 정보가 들어있습니다.
            attackEnemies(enemies, arr);
            for (Position enemy : enemies) {
                if (enemy != null) {
                    int x = enemy.x;
                    int y = enemy.y;

                    if (arr[x][y] == 1) { // 1이면 적을 죽인다.
                        arr[x][y] = 0;
                        leftEnemyCount--;
                        killCnt++;
                    }
                }
            }
            tmpAns += killCnt;

            for (int j = 0; j < m; j++) { // 제일 마지막 줄에 적이 아직 남아있으면 감소시켜줘야함
                if (arr[n - 1][j] == 1) {
                    leftEnemyCount--;
                }
            }

            // 이동
            for (int i = n - 1; i > 0; i--) {
                arr[i] = arr[i - 1];
            }
            arr[0] = new int[m];
        }
        ans = Math.max(ans, tmpAns); // 답 갱신
    }

    static void attackEnemies(ArrayList<Position> enemies, int[][] arr) {
        for (int archer : archers) {
            int archerX = n;
            int archerY = archer;

            boolean[][] visited = new boolean[n][m];
            //bfs를 시작해서 좌표를 찾아내는 것이 핵심이다.
            ArrayDeque<Position> q = new ArrayDeque<>();
            q.add(new Position(archerX, archerY));

            Position enemy = null; // 가장 가까운 적 탐색

            int moveCnt = d + 1; // 처음 탐색한 적과의 거리
            while (!q.isEmpty()) {
                Position position = q.poll();
                int x = position.x;
                int y = position.y;
                int cnt = position.cnt;

                if (cnt == d) { // 궁수의 사거리를 넘어가면 break;
                    break;
                }

                if (cnt + 1 > moveCnt) { // 찾은 enemy 보다 거리가 멀면 break;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) { // 범위 벗어나면 안됨
                        continue;
                    }

                    if (arr[nx][ny] == 1) { // 적을 탐색했다.
                        if (enemy == null) { // 처음 발견한 적이면
                            enemy = new Position(nx, ny);
                            moveCnt = cnt + 1; // moveCnt 정의
                        } else { // 처음 발견한 적이 아니라면, 가장 왼쪽인지 파악해야함.
                            if (ny < enemy.y) { // 새로 들어온 적이 더 왼쪽이라면 갱신해줘야함.
                                enemy = new Position(nx, ny); // 새롭게 enemy를 갱신해줍니다.
                            }
                        }
                    }

                    visited[nx][ny] = true;
                    q.add(new Position(nx, ny, cnt + 1));
                }
            }
            enemies.add(enemy);
        }
    }
}