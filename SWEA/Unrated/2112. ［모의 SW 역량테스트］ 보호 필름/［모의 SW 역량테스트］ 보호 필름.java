import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int d;
    static int w;
    static int k;
    static int ans;
    static int[][] arr;
    static int[][] tmp;

    static void setInputs(int tc) throws Exception {
        sb.append("#").append(tc).append(" ");
        ans = (int) 1e9;

        st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[d][w];
        tmp = new int[d][w];
        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
                tmp[i][j] = num;
            }
        }
    }

    static void bruteForce(int depth, int cnt) {
        if (ans <= cnt) {
            return;
        }

        if (depth == d) {
            if (check()) {
                ans = cnt;
            }
            return;
        }

        bruteForce(depth + 1, cnt);

        changeValue(depth, 0);
        bruteForce(depth + 1, cnt + 1);

        changeValue(depth, 1);
        bruteForce(depth + 1, cnt + 1);

        arr[depth] = tmp[depth].clone();
    }

    static boolean check() {
        for (int y = 0; y < w; y++) {
            int initialValue = arr[0][y];
            int cnt = 1;
            int max = 0;

            for (int x = 1; x < d; x++) {
                if (arr[x][y] == initialValue) { // 값이 같으면 cnt++
                    cnt++;
                } else { // 값이 다르면 cnt를 1로 다시 초기화 한다.
                    cnt = 1;
                }
                initialValue = arr[x][y]; // 값을 뒤로 옮기고
                max = Math.max(cnt, max); // 최대 개수 갱신
            }
            if (max < k) {
                return false;
            }
        }
        return true;
    }

    static void changeValue(int depth, int value) {
        for (int i = 0; i < w; i++) {
            arr[depth][i] = value;
        }
    }

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            setInputs(tc);
            bruteForce(0, 0);

            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}