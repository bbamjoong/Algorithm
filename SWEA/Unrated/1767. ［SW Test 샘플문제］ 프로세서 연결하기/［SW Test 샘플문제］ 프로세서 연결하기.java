import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[][] arr;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static ArrayList<Pos> posList;
    static int ans;

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void setInputs() throws Exception {
        ans = (int) 1e9; // static이므로 초기화 해주어야 함

        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        posList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 1) {
                    if (i == 0 || j == 0 || i == n - 1 || j == n - 1) { // 테두리
                        continue;
                    }
                    posList.add(new Pos(i, j));
                }
            }
        }
    }

    static void connectCore() {
        int size = posList.size();
        for (int i = size; i > 0; i--) {
            int[] posArr = new int[i];
            combination(0, 0, i, posArr);
            if (ans != (int) 1e9) {
                break;
            }
        }
    }

    static void combination(int depth, int idx, int maxSize, int[] posArr) {
        if (depth == maxSize) {
            // 이제 연결 구현 로직을 짬
            setLine(0, 0, maxSize, posArr);
            return;
        }

        for (int i = idx; i < posList.size(); i++) {
            posArr[depth] = i;
            combination(depth + 1, i + 1, maxSize, posArr);
            // 배열 쓰니까 백트래킹을 해도 방문처리 따로 안해줘도 됨
        }
    }

    static void setLine(int idx, int tmpAns, int maxSize, int[] posArr) {
        if (idx == maxSize) {
            ans = Math.min(ans, tmpAns);
            return;
        }

        int posIdx = posArr[idx]; // posArr에 있는 정보는 posList에서 활성화된 전선의 index임
        int x = posList.get(posIdx).x;
        int y = posList.get(posIdx).y;

        for (int i = 0; i < 4; i++) {
            // 연결
            int tmpLength = 0;
            while (true) {
                x += dx[i];
                y += dy[i];

                if (x < 0 || x >= n || y < 0 || y >= n) { // 범위 끝까지 갔으면 성공
                    setLine(idx + 1, tmpAns + tmpLength, maxSize, posArr);
                    break;
                }

                if (arr[x][y] != 0) { // 실패 -> 복구로 바로 넘어감
                    break;
                }

                arr[x][y] = 2;
                tmpLength++;
            }

            // 실패 시 복구
            while (true) {
                x -= dx[i];
                y -= dy[i];

                if (x == posList.get(posIdx).x && y == posList.get(posIdx).y) { // 복구하다가 원래대로 돌아오면 끝
                    break;
                }
                arr[x][y] = 0; // 복구
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            setInputs();
            connectCore();
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
