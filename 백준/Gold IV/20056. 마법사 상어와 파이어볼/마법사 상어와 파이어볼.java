import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    // 방향
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    // 방향의 index정보
    static int[] allSame = {0, 2, 4, 6};
    static int[] different = {1, 3, 5, 7};

    static int n;
    static int m;
    static int k;
    static ArrayDeque<Integer>[][] arr;
    static ArrayList<FireBall> fireBalls = new ArrayList<>();
    static int index = 0;
    static int ans;

    static class FireBall {
        int x;
        int y;
        int matter;
        int speed;
        int direction;
        int index;
        boolean dead;

        public FireBall(int x, int y, int matter, int speed, int direction, int index) {
            this.x = x;
            this.y = y;
            this.matter = matter;
            this.speed = speed;
            this.direction = direction;
            this.index = index;

            dead = false;
        }
    }

    public static void main(String[] args) throws Exception {
        setInputs();

        for (int i = 0; i < k; i++) {
            moveFireBall();
            divideFireBalls();
        }
        calculateAnswer();

        System.out.println(ans);
    }

    static void setInputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new ArrayDeque[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int matter = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());

            arr[x][y].add(index); // index로 파이어볼 표시
            fireBalls.add(new FireBall(x, y, matter, direction, speed, index++)); // index는 추가해줘야됨
        }
    }

    static void moveFireBall() {
        // 파이어볼 이동
        for (FireBall fireBall : fireBalls) {
            if (!fireBall.dead) { // 죽지 않은 애들 이동
                arr[fireBall.x][fireBall.y].pollFirst(); // 떠나는 자리는 -1로 원상복구
                int speed = fireBall.speed % n;
                int nx = (fireBall.x + dx[fireBall.direction] * speed) % (n);
                int ny = (fireBall.y + dy[fireBall.direction] * speed) % (n);

                if (nx < 0) {
                    nx += n;
                }
                if (ny < 0) {
                    ny += n;
                }

                // 파이어볼의 좌표 갱신
                fireBall.x = nx;
                fireBall.y = ny;

                // arr에도 좌표 갱신(파이어볼의 index)
                arr[nx][ny].add(fireBall.index);
            }
        }
    }


    static void divideFireBalls() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j].size() >= 2) { // 파이어볼이 있으면 로직 시작\
                    int size = arr[i][j].size();
                    int matterSum = 0; // 질량의 합
                    boolean first = true;
                    boolean isDifferent = false;
                    int direction = 0;
                    int speedSum = 0;

                    for (Integer integer : arr[i][j]) {
                        FireBall fireBall = fireBalls.get(integer);
                        matterSum += fireBall.matter;
                        speedSum += fireBall.speed;

                        int fireBallDirection = fireBall.direction % 2;

                        if (first) {
                            direction = fireBallDirection;
                            first = false; // 처음이니??를 false로
                        } else {
                            // 방향의 홀짝이 다르다면
                            if (direction != fireBallDirection % 2) {
                                isDifferent = true;
                            }
                        }
                        // 나눈 파이어볼은 죽은 처리해야됨
                        fireBall.dead = true;
                        arr[i][j].poll();
                    }

                    int matter = matterSum / 5;
                    // 질량 0이면 만들지말고 그냥 나가
                    if (matter == 0) {
                        continue;
                    }
                    int speed = speedSum / size;
                    // 파이어볼 만들어준다.
                    for (int t = 0; t < 4; t++) {
                        int directionIndex = 0;
                        if (isDifferent) { // 홀짝이 달랐다.
                            directionIndex = different[t];
                        } else { // 홀짝이 같았다.
                            directionIndex = allSame[t];
                        }
                        arr[i][j].add(index);
                        fireBalls.add(new FireBall(i, j, matter, speed, directionIndex, index++));// 생성할 때 index 추가해라
                    }
                }
            }
        }
    }

    static void calculateAnswer() {
        for (FireBall fireBall : fireBalls) {
            if (!fireBall.dead) {
                ans += fireBall.matter;
            }
        }
    }
}