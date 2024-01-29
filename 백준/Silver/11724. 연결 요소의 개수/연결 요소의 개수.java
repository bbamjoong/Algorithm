import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static int cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        arr = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 1; i < m + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            arr[b].add(a);
        }

        for (int i = 1; i < n + 1; i++) {
            if (!visited[i]) {
                if (arr[i].isEmpty()) {
                    cnt++;
                    visited[i] = true;
                } else {
                    dfs(i, 0);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    static void dfs(int start, int depth) {
        visited[start] = true;

        for (int i : arr[start]) {
            if (!visited[i]) {
                dfs(i, depth + 1);
            }
        }
    }
}