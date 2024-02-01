import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] arr;
    static List<Home> homeList = new ArrayList<>();
    static List<Chicken> chickenList = new ArrayList<>();
    static List<Integer> visited = new ArrayList<>();
    static int ans = (int) 1e9;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;

                if (num == 1) {
                    homeList.add(new Home(i, j));
                } else if (num == 2) {
                    chickenList.add(new Chicken(i, j));
                }
            }
        }
        dfs(0, 0);

        sb.append(ans);
        System.out.println(sb);
    }

    static void dfs(int start, int depth) {
        if (depth == m) {
            getAnswer();
            return;
        }

        for (int i = start; i < chickenList.size(); i++) {
            visited.add(i);
            dfs(i + 1, depth + 1);
            visited.remove(visited.size() - 1);
        }
    }

    static void getAnswer() {
        int cnt = 0;
        for (Home home : homeList) {
            int tmp = (int) 1e9;
            for (Integer i : visited) {
                tmp = Math.min(calculateDist(home, chickenList.get(i)), tmp);
            }
            cnt += tmp;
            if (tmp >= ans) {
                return;
            }
        }
        ans = Math.min(cnt, ans);
    }

    static int calculateDist(Home home, Chicken chicken) {
        return Math.abs(home.x - chicken.x) + Math.abs(home.y - chicken.y);
    }

    static class Home {
        int x;
        int y;

        public Home(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Chicken {
        int x;
        int y;

        public Chicken(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}