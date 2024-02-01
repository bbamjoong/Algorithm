import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Food[] foods = new Food[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sourTaste = Integer.parseInt(st.nextToken());
            int bitterTaste = Integer.parseInt(st.nextToken());
            foods[i] = new Food(sourTaste, bitterTaste);
        }

        int ans = (int) 1e9;
        for (int i = 1; i < (1 << n); i++) {
            int sourCnt = 1;
            int bitterCnt = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    sourCnt *= foods[j].sourTaste;
                    bitterCnt += foods[j].bitterTaste;
                }
            }
            int tasteDifference = Math.abs(sourCnt - bitterCnt);
            ans = Math.min(tasteDifference, ans);
        }

        sb.append(ans);
        System.out.println(sb);
    }

    static class Food {
        int sourTaste;
        int bitterTaste;

        public Food(int sourTaste, int bitterTaste) {
            this.sourTaste = sourTaste;
            this.bitterTaste = bitterTaste;
        }
    }
}