import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static Food[] foods;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case < T + 1; test_case++) {
            int ans = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int maxValue = Integer.parseInt(st.nextToken());
            foods = new Food[count];

            for (int i = 0; i < count; i++) {
                st = new StringTokenizer(br.readLine());
                foods[i] = new Food(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            for (int i = 1; i < (1 << count); i++) {
                int kcalTmp = 0;
                int scoreTmp = 0;
                boolean flag = true;

                for (int j = 0; j < count; j++) {
                    if ((i & (1 << j)) > 0) {
                        kcalTmp += foods[j].kcal;
                        scoreTmp += foods[j].score;
                    }
                    if (kcalTmp > maxValue) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    ans = Math.max(ans, scoreTmp);
                }
            }
            sb.append("#").append(test_case).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static class Food {
        int score;
        int kcal;

        public Food(int score, int kcal) {
            this.score = score;
            this.kcal = kcal;
        }
    }
}