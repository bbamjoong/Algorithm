import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int r; // 행
    static int c; // 열
    static int t; // 초
    static int[][] arr; // 보드
    static ArrayList<Robot> robots = new ArrayList<>();

    // 오른쪽 -> 위쪽 -> 왼쪽 -> 아래쪽
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static StringBuilder sb = new StringBuilder();

    static class Robot {
        int x;
        int y;

        public Robot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void setInputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        arr = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;

                if (num == -1) { // -1이면 로봇 추가
                    robots.add(new Robot(i, j));
                }
            }
        }
    }

    static void spreadDusts() {
        int[][] tmpArr = new int[r][c];

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (arr[x][y] > 0) { // 퍼질 수 있는 먼지가 존재하면
                    int dustAmount = arr[x][y];
                    int alreadySpreadAmount = 0;
                    for (int i = 0; i < 4; i++) { // 4방향 탐색
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        // 공기청정기 or 맵 밖으로 벗어나면 안됨
                        if (nx < 0 || ny < 0 || nx >= r || ny >= c || tmpArr[nx][ny] == -1) {
                            continue;
                        }

                        int spreadAmount = dustAmount / 5; // 퍼트릴 공기의 양
                        tmpArr[nx][ny] += spreadAmount; // 임시 보드에 먼지를 더해주고
                        alreadySpreadAmount += spreadAmount; // 퍼트린 먼지 양을 더해주고
                    }
                    // 4방 탐색이 끝나고 남은 먼지를 임시 보드에 더해준다.
                    tmpArr[x][y] += dustAmount - alreadySpreadAmount;
                } else if (arr[x][y] == -1) { // 청소기 복제
                    tmpArr[x][y] = -1;
                }
            }
        }
        arr = tmpArr;
    }

    static void activateRobot(int robotX, int robotY, int changeDirectionTo) {
        int nx = robotX;
        int ny = robotY + 1;
        int tmp1 = 0; // 이전 칸 저장할 것임
        int tmp2; // 현재 칸 저장할 것임
        int dir = 0;
        while (true) {
            // 값을 이전 칸의 값으로 바꿨다.
            tmp2 = arr[nx][ny];
            arr[nx][ny] = tmp1;
            tmp1 = tmp2;

            //좌표를 옮겨주고
            nx += dx[dir];
            ny += dy[dir];
            if (nx < 0 || ny < 0 || nx >= r || ny >= c) { // 범위 벗어나면
                // 다시 예전 좌표로 바꾸고
                nx -= dx[dir];
                ny -= dy[dir];
                /**
                 * 위쪽은 (dir + 1) % 4;
                 * 아래쪽은 (dir + 3) % 4;
                 */
                dir = (dir + changeDirectionTo) % 4;
                // 바꾼 방향으로 가기
                nx += dx[dir];
                ny += dy[dir];
            }

            if (nx == robotX && ny == robotY) { // 로봇 만나면 종료
                return;
            }
        }
    }

    static int calculateAnswer() {
        int ans = 2;
        for (int[] values : arr) {
            for (int value : values) {
                ans += value;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        setInputs();

        int upperRobotX = robots.get(0).x;
        int upperRobotY = robots.get(0).y;

        int underRobotX = robots.get(1).x;
        int underRobotY = robots.get(1).y;

        for (int i = 0; i < t; i++) {
            spreadDusts();
            activateRobot(upperRobotX, upperRobotY, 1);
            activateRobot(underRobotX, underRobotY, 3);
        }

        int ans = calculateAnswer();
        System.out.println(ans);
    }
}