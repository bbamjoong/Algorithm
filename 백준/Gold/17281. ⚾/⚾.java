import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[][] infos;
    static int[] rotation;
    static boolean[] visited;

    static int ans;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());

        infos = new int[n + 1][10];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 10; j++) {
                infos[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotation = new int[10];
        rotation[4] = 1; // idx : idx번 타자 | value : value번 선수 -> 1번선수가 4번타자

        visited = new boolean[10];
        visited[1] = true; // 여기서는 idx가 idx번 선수 -> 1번선수가 4번타자가 되었으니, 1번선수는 true

        combination(1);

        System.out.println(ans);
    }

    static void combination(int depth) {
        if (depth == 10) { // 나머지 8명의 선수를 모두 조합했습니다.
            //로테이션이 다 정해졌으니까, 야구를 시작해봅시다.
            int score = playBaseBall();
            ans = Math.max(ans, score);
        }

        if (depth == 4) { // 4번 타자는 딱히 할일이 없다.
            combination(depth + 1);
        } else {
            for (int i = 2; i < 10; i++) { // 1번 선수는 이미 고정됨, 2번부터 9번 선수를 탐색
                if (!visited[i]) { // i번 선수를 방문하지 않았다.
                    visited[i] = true;
                    rotation[depth] = i;
                    combination(depth + 1);
                    visited[i] = false; // 백트래킹
                }
            }
        }
    }

    static int playBaseBall() {
        int idx = 1; // 선수의 번호
        int outCount; // 아웃카운트
        int gameScore = 0;
        for (int i = 1; i < n + 1; i++) { // 이닝.
            outCount = 0;
            int[] baseInfo = new int[4];

            while (outCount < 3) { // 선수들은 아웃카운트가 3이 될때까지 계속 게임을 진행합니다.
                int situation = infos[i][rotation[idx]];
                baseInfo[0] = 1; // 홈타자 추가

                if (situation == 0) { // 아웃상황
                    baseInfo[0] = 0;
                    outCount++; //아웃카운트 추가
                } else if (situation == 4) {
                    for (int c = 0; c < 4; c++) {
                        if (baseInfo[c] == 1) {
                            gameScore++;
                            baseInfo[c] = 0;
                        }
                    }
                } else { // 1, 2, 3일 때
                    for (int c = 3; c >= 0; c--) {
                        if (baseInfo[c] == 1) { // 마지막 주자가 홈드로 들어올 수 있으면 점수 추가
                            if (c + situation >= 4) {
                                gameScore++;
                                baseInfo[c] = 0;
                            } else {
                                baseInfo[c + situation] = 1;
                                baseInfo[c] = 0;
                            }
                        }
                    }
                }
                // 타석이 끝났으니 idx를 증가시켜줍니다.
                idx = (idx % 9) + 1; // 9번타자 뒤에는 1번타자가 와야하므로 모듈러 연산
            }
        }
        return gameScore;
    }
}
