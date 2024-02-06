import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int m;
    static ArrayList<Integer> snacks;
    static int answer;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            answer = -1;
            sb.append("#").append(tc).append(" ");

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            snacks = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                snacks.add(Integer.parseInt(st.nextToken()));
            }

            pickSnack(0, 0, 0);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static void pickSnack(int start, int snackCount, int sumOfWeight) {
        if (sumOfWeight > m) { // 무게 초과하면 종료
            return;
        }

        if (snackCount == 2) {
            answer = Math.max(answer, sumOfWeight);
            return;
        }

        for (int i = start; i < snacks.size(); i++) {
            pickSnack(i + 1, snackCount + 1, sumOfWeight + snacks.get(i));
            if (answer == m) { // 만약 답이 m이면 최대치에 도달했으니 더이상 탐색을 할 필요가 없다.
                return;
            }
        }
    }
}
