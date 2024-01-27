import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] graph;
    static boolean[] visited;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new int[n + 1][n + 1];
        visited = new boolean[n + 1];

        StringTokenizer st;
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0, new ArrayList<>());
        StringBuilder sb = new StringBuilder();
        sb.append(ans);
        System.out.println(ans);
    }

    static void dfs(int start, int cnt, ArrayList<Integer> arr) {
        if (cnt == n / 2) {
            int cal = calculate();
            ans = Math.min(cal, ans);
            return;
        }
        for (int i = start + 1; i < n + 1; i++) {
            visited[i] = true;
            arr.add(i);
            dfs(i, cnt + 1, arr);
            arr.remove(arr.indexOf(i));
            visited[i] = false;
        }
    }

    static int calculate() {
        ArrayList<Integer> start = new ArrayList<>();
        ArrayList<Integer> end = new ArrayList<>();
        int cntStart = 0;
        int cntEnd = 0;

        for (int i = 1; i < visited.length; i++) {
            if (visited[i]) {
                start.add(i);
            } else {
                end.add(i);
            }
        }

        for (Integer i : start) {
            for (Integer integer : start) {
                if (!i.equals(integer)) {
                    cntStart += graph[i][integer];
                }
            }
        }

        for (Integer i : end) {
            for (Integer integer : end) {
                if (!i.equals(integer)) {
                    cntEnd += graph[i][integer];
                }
            }
        }

        return Math.abs(cntStart - cntEnd);
    }
}