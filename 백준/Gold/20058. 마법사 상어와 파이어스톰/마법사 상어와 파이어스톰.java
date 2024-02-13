import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int q;
    static int[][] matrix;
    static int[][] meltedIce;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int sumOfIce;
    static int biggestIce = 1;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 입력값 처리
    static void setInputs() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        n = (int) Math.pow(2, n);
        matrix = new int[n][n];
        meltedIce = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    // 파이어스톰
    static void fireStorm(int l) {
        rotateMatrix(l);
        reduceIce();
        updateMatrix();
    }

    // 배열 회전
    static void rotateMatrix(int l) {
        int[][] tmpMatrix = new int[n][n];
        for (int i = 0; i < n; i += l) {
            for (int j = 0; j < n; j += l) {
                rotatePartOfMatrix(i, j, l, tmpMatrix);
            }
        }
        matrix = tmpMatrix;
    }

    // 부분 배열 회전
    static void rotatePartOfMatrix(int x, int y, int l, int[][] tmpMatrix) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                tmpMatrix[x + i][y + j] = matrix[x + l - 1 - j][y + i];
            }
        }
    }

    // 배열 갱신(감소한 얼음 더해주기)
    static void updateMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] += meltedIce[i][j];
                meltedIce[i][j] = 0; // 초기조건으로 다시 돌려주기
            }
        }
    }

    //얼음 제거
    static void reduceIce() {
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                // 검사할 칸이 0이라면 continue;
                if (matrix[x][y] == 0) {
                    continue;
                }

                int iceCnt = 0;
                // 0이 아니라면 인접한 칸에 얼음여부 확인
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // 범위 밖을 벗어나면 continue
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                        continue;
                    }

                    // 해당 칸이 0이라면 continue
                    if (matrix[nx][ny] == 0) {
                        continue;
                    }

                    // 얼음이 있으면 iceCnt++
                    iceCnt++;
                }

                // 인접한 얼음이 3개 미만이면 얼음이 녹았다고 처리해줌
                if (iceCnt < 3 && matrix[x][y] > 0) {
                    meltedIce[x][y]--;
                }
            }
        }
    }

    // 결과 계산
    static void calculateResult() {
        countIce();
        findBiggestIce();
    }

    // 얼음의 합 계산
    static void countIce() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumOfIce += matrix[i][j];
            }
        }
        sb.append(sumOfIce).append("\n"); // 얼음의 합
    }

    // 가장 큰 얼음 덩어리를 찾는다.
    static void findBiggestIce() { // bfs
        boolean[][] visited = new boolean[n][n]; // 방문체크

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) { // 얼음이 없는 칸이면
                    visited[i][j] = true; // 방문 처리 후 continue
                    continue;
                }
                if (!visited[i][j]) { // 방문안했으면 bfs돌림
                    visited[i][j] = true; // 방문 처리
                    bfs(i, j, visited); // bfs 시작
                }
            }
        }

        if (biggestIce == 1) { // 얼음 덩어리가 초기 값이면 0으로 출력 (문제의 조건)
            biggestIce = 0;
        }
        sb.append(biggestIce);
    }

    // bfs를 이용해 cnt 계산
    static void bfs(int i, int j, boolean[][] visited) {
        int cnt = 1; // 얼음 덩어리의 크기
        ArrayDeque<Position> queue = new ArrayDeque<>();
        queue.add(new Position(i, j));

        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            int x = pos.x;
            int y = pos.y;

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) { // 범위 벗어나면 continue
                    continue;
                }

                if (matrix[nx][ny] == 0) { // 얼음이 없으면 continue;
                    continue;
                }

                if (!visited[nx][ny]) { // 마지막 조건으로, 방문하지 않은 곳이면
                    cnt++; // 얼음덩어리의 카운트를 추가해준다.
                    visited[nx][ny] = true; // 방문처리
                    queue.add(new Position(nx, ny));
                }
            }
        }

        biggestIce = Math.max(biggestIce, cnt); // 최대 크기인 것을 답으로 처리
    }

    public static void main(String[] args) throws Exception {
        setInputs();

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int l = Integer.parseInt(st.nextToken());
            l = (int) Math.pow(2, l);
            fireStorm(l);
        }

        calculateResult();

        System.out.println(sb);
    }
}