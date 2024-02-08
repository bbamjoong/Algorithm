import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r;
    static int c;
    static Shark[][] arr;
    static Shark[][] tmpArr;

    static int UP = 1;
    static int DOWN = 2;
    static int RIGHT = 3;
    static int LEFT = 4;

    static int site; // 낚시꾼의 위치
    static int ans; // 답

    static class Shark {
        int x;
        int y;
        int speed;
        int direction;
        int size;

        public Shark(int x, int y, int speed, int direction, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }

    static void setInputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new Shark[r][c];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            // 상어 추가
            arr[x - 1][y - 1] = new Shark(x - 1, y - 1, speed, direction, size);
        }
    }

    static void catchShark() {
        for (int i = 0; i < r; i++) {
            if (arr[i][site] != null) {
                ans += arr[i][site].size;
                arr[i][site] = null;
                break;
            }
        }
        site++;
    }

    static void moveSharks() {
        tmpArr = new Shark[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                Shark shark = arr[i][j];
                if (shark != null) {
                    moveIndividualShark(i, j, shark);

                    Shark originalShark = tmpArr[shark.x][shark.y];
                    // 만약 상어의 위치가 겹친다?
                    if (originalShark != null) {
                        // 새로운 상어의 사이즈가 크면 새로운 상어를 넣고 아니면 현행 유지
                        if (shark.size > originalShark.size) {
                            tmpArr[shark.x][shark.y] = shark;
                        } // null이 아니었는데 기존 상어가 더 크면 아무 것도 안해줘도 됨
                    } else {
                        // 해당 좌표에 아무 상어도 없었으면 새로운 상어 배치
                        tmpArr[shark.x][shark.y] = shark;
                    }
                }
            }
        }
        arr = tmpArr; // 배열 바꾸기
    }

    static void moveIndividualShark(int x, int y, Shark shark) {
        int dir = shark.direction;
        int speed = shark.speed;
        if (dir == UP || dir == DOWN) {
            int cycle = (r - 1) * 2;

            // Row가 0이면서 아래로 내려가는 형태로 시작을 고정
            if (dir == UP) {
                speed += cycle - x;
            } else {
                speed += x;
            }

            speed %= cycle; // 1회 반복 사이클은 계산하지 말 것
            if (speed >= r) {
                shark.x = cycle - speed; // 행 증가
                shark.direction = UP; // 반 사이클을 넘어가면 위로 올라감
            } else {
                shark.x = speed; // 행 증가
                shark.direction = DOWN;
            }
        } else if (dir == LEFT || dir == RIGHT) {
            int cycle = (c - 1) * 2;

            // Col이 0이면서 오른쪽으로 가는 형태로 시작을 고정
            if (dir == LEFT) {
                speed += cycle - y;
            } else {
                speed += y;
            }

            speed %= cycle;
            if (speed >= c) {
                shark.y = cycle - speed; // 열 증가
                shark.direction = LEFT; // 반 사이클을 넘으면 왼쪽으로 감
            } else {
                shark.y = speed; // 열 증가
                shark.direction = RIGHT;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        setInputs();

        for (int i = 0; i < c; i++) {
            catchShark(); // 상어 잡기~
            moveSharks(); // 상어 도망가기~
        }

        System.out.println(ans);
    }
}