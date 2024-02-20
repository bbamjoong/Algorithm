import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int m;
    static int c;
    static int[][] arr;

    static int ans;

    // 1번 일꾼은 무조건 2번 일꾼 앞에 위치하도록 한다.
    static Worker worker1;
    static Worker worker2;

    static class Worker {
        int x;
        int y;
        int[] honeys;
        int ans;

        public Worker(int size) {
            honeys = new int[size];
            ans = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < T + 1; tc++) {
            sb.append("#").append(tc).append(" ");

            setInputs();
            setWorkers();

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void setInputs() throws IOException {
        ans = 0;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void setWorkers() {
        worker1 = new Worker(m);
        worker2 = new Worker(m);

        for (int i = 0; i < n * n; i++) {
            if (i % n > n - m) { // 범위를 벗어나면 안돼요.
                continue;
            }
            // 1번 일꾼의 위치를 정했습니다.
            worker1.x = i / n;
            worker1.y = i % n;

            for (int idx = 0; idx < m; idx++) {
                worker1.honeys[idx] = arr[worker1.x][worker1.y + idx];
            }

            // 2번 일꾼의 위치를 정합니다.
            for (int j = i + m; j < n * n; j++) {
                worker2.ans = 0; // 일꾼 2번은 계속 조합을 찾기위해 움직이기때문에 매 경우마다 ans를 0으로 초기화 해줘야 함.

                if (j % n > n - m) { // 범위를 벗어나면 안돼요.
                    continue;
                }
                worker2.x = j / n;
                worker2.y = j % n;

                for (int idx = 0; idx < m; idx++) {
                    worker2.honeys[idx] = arr[worker2.x][worker2.y + idx];
                }

                // 위치를 다 정했으니 꿀벌통에서 꿀을 채집합시다.
                getHoney(worker1);
                getHoney(worker2);

                ans = Math.max(ans, worker1.ans + worker2.ans);
            }
        }
    }

    static void getHoney(Worker worker) {
        for (int i = m; i > 0; i--) { // 가장 큰 범위부터 조합을 찾습니다.
            combination(0, 0, i, 0, 0, worker);
        }
    }

    static void combination(int idx, int depth, int size, int sum, int honey, Worker worker) {
        if (sum > c) {
            return;
        }

        if (depth == size) {
            worker.ans = Math.max(honey, worker.ans);
            return;
        }

        for (int i = idx; i < m; i++) {
            combination(i + 1, depth + 1, size, sum + worker.honeys[i], (int) (honey + Math.pow(worker.honeys[i], 2)),
                    worker);
        }
    }
}