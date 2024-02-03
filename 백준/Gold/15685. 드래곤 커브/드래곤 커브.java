import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] graph = new int[101][101];
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            graph[x][y] = 1;

            ArrayList<Integer> dir = new ArrayList<>();
            dir.add(d);

            // 세대 수 만큼 반복
            makeCurveDirection(dir, g);

            // 커브 좌표 체크
            checkCurveCoordinate(dir, x, y);
        }
        // 결과 계산
        int ans = calculateAnswer();
        sb.append(ans);
        System.out.println(sb);
    }

    static void checkCurveCoordinate(ArrayList<Integer> dir, int x, int y) {
        for (Integer integer : dir) {
            x += dx[integer];
            y += dy[integer];

            if (x < 0 || y < 0 || x >= 101 || y >= 101) {
                break;
            }

            graph[x][y] = 1;
        }
    }

    static void makeCurveDirection(ArrayList<Integer> dir, int g) {
        for (int i = 0; i < g; i++) {
            // 제일 끝쪽부터 새로운 커브가 만들어짐
            for (int j = dir.size() - 1; j > -1; j--) {
                dir.add((dir.get(j) + 1) % 4);
            }
        }
    }

    static int calculateAnswer() {
        int ans = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (graph[i][j] == 1 && graph[i + 1][j] == 1 && graph[i][j + 1] == 1 && graph[i + 1][j + 1] == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }
}