import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int l;
    static int[][] graph;
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        cnt = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 행 검사
        for (int i = 0; i < n; i++) {
            int[] arr = graph[i];
            if (check(arr)) {
                cnt++;
            }
        }

        // 열 검사
        for (int j = 0; j < n; j++) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = graph[i][j];
            }
            if (check(arr)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    static boolean check(int[] arr) {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n - 1; i++) {
            if (Math.abs(arr[i] - arr[i + 1]) > 1) {
                return false;
            } else if (arr[i] == arr[i + 1]) {
                continue;
            } else if (arr[i] > arr[i + 1]) {
                for (int j = i + 1; j <= i + l; j++) {
                    if (j >= n || arr[j] != arr[i + 1] || visited[j]) {
                        return false;
                    }
                    visited[j] = true;
                }
            } else if (arr[i] < arr[i + 1]) {
                for (int j = i; j > i - l; j--) {
                    if (j < 0 || arr[j] != arr[i] || visited[j]) {
                        return false;
                    }
                    visited[j] = true;
                }
            }
        }
        return true;
    }
}