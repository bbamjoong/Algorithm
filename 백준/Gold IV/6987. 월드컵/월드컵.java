import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] games;
    static boolean isGameEnd;
    static int gameCnt = 15;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        calculateGames(gameCnt); // 경우의 수 저장

        for (int tc = 0; tc < 4; tc++) {
            isGameEnd = false;
            st = new StringTokenizer(br.readLine());

            int[][] gameResult = new int[3][6];
            boolean flag = true;

            int winCnt = 0;
            int loseCnt = 0;

            for (int i = 0; i < 6; i++) {
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());

                gameResult[0][i] = win;
                winCnt++;

                gameResult[1][i] = draw;

                gameResult[2][i] = lose;
                loseCnt++;

                if (win + draw + lose != 5) { // 5번 경기
                    flag = false;
                    break;
                }
            }

            if (flag) {
                backTracking(gameResult, 0);
                if (isGameEnd) {
                    sb.append(1).append(" ");
                    continue;
                }
                sb.append(0).append(" ");

            } else {
                sb.append(0).append(" ");
            }

        }
        System.out.print(sb);
    }

    static void calculateGames(int size) {
        games = new int[size][2];
        int index = 0;
        while (index < 6) {
            for (int i = 0; i < 5; i++) {
                for (int j = i + 1; j < 6; j++) {
                    games[index][0] = i;
                    games[index][1] = j;
                    index++;
                }
            }
        }
    }

    // 백트래킹 함수
    static void backTracking(int[][] worldCup, int matchCount) {
        if (isGameEnd) {
            return;
        }

        if (matchCount == gameCnt) {
            isGameEnd = true;
            return;
        }

        int myTeam = games[matchCount][0];
        int enemyTeam = games[matchCount][1];

        if (worldCup[0][myTeam] > 0 && worldCup[2][enemyTeam] > 0) {
            worldCup[0][myTeam]--;
            worldCup[2][enemyTeam]--;
            backTracking(worldCup, matchCount + 1);
            worldCup[0][myTeam]++;
            worldCup[2][enemyTeam]++;
        }

        if (worldCup[1][myTeam] > 0 && worldCup[1][enemyTeam] > 0) {
            worldCup[1][myTeam]--;
            worldCup[1][enemyTeam]--;
            backTracking(worldCup, matchCount + 1);
            worldCup[1][myTeam]++;
            worldCup[1][enemyTeam]++;
        }

        if (worldCup[2][myTeam] > 0 && worldCup[0][enemyTeam] > 0) {
            worldCup[2][myTeam]--;
            worldCup[0][enemyTeam]--;
            backTracking(worldCup, matchCount + 1);
            worldCup[2][myTeam]++;
            worldCup[0][enemyTeam]++;
        }
    }
}