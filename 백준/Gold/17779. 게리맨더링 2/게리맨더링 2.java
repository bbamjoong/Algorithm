import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[][] graph;
    static int total;
    static int[] people;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        setInputs();
        gerrymandering();

        System.out.println(ans);
    }

    static void setInputs() throws Exception {
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                total += graph[i][j];
            }
        }
    }

    static void gerrymandering() {
        /*
           x는 문제의 조건에 의해 0 ~ n-3 의 값을 가질 수 있다.
           y는 문제의 조건에 의해 1 ~ n-2 의 값을 가질 수 있다.
         */
        for (int x = 0; x < n - 2; x++) {
            for (int y = 1; y < n - 1; y++) {
                // d1, d2는 문제의 조건을 이용해 조건이 맞지 않다면 continue 처리
                /*
                  1 ≤ x < x+d1+d2 ≤ N 에 따라서
                  d1 : x가 1이고 d2이 1일 때 -> d1은 최대 n-2
                  d2 : d1이 정해지면 조건에 맞춰서 범위를 조절 (1 <= d2 <= n - x - d1)
                 */
                for (int d1 = 1; d1 < n - 1; d1++) {
                    for (int d2 = 1; d2 <= n - x - d1; d2++) {
                        // 1 ≤ y-d1 < y < y+d2 ≤ N 조건을 만족 해야 하기 때문에 조건문 추가
                        if (y - d1 < 1) {
                            continue;
                        }
                        if (y + d2 > n) {
                            continue;
                        }
                        ans = Math.min(ans, solve(x, y, d1, d2));
                    }
                }
            }
        }
    }

    static int solve(int x, int y, int d1, int d2) {
        people = new int[5];

        calculateSection1(x, y, d1); // 1구역 : 0 ~ x + d1 -1 행까지 탐색
        calculateSection2(x, y, d2); // 2구역 : 0 ~ x + d2 행까지 탐색
        calculateSection3(x, y, d1, d2); // 3구역 : x + d1 ~ n-1 행까지 탐색
        calculateSection4(x, y, d1, d2); // 4구역 : x + d2 + 1 ~ n-1행까지 탐색

        return calculateAnswer();
    }

    static void calculateSection1(int x, int y, int d1) {
        int standard = y;
        // 위에서 아래로 내려오면서 탐색
        for (int r = 0; r < x + d1; r++) {
            if (r >= x) {
                standard--;
            }
            for (int c = 0; c <= standard; c++) {
                people[0] += graph[r][c];
            }
        }
    }

    static void calculateSection2(int x, int y, int d2) {
        int standard = y + 1;
        for (int r = 0; r <= x + d2; r++) {
            // 탐색하는 행이 x보다 위쪽이거나 같으면 탐색할 열의 범위가 줄어들지 않는다.
            // x보다 작을 때 줄어든다.
            if (r > x) {
                standard++;
            }
            for (int c = standard; c < n; c++) {
                people[1] += graph[r][c];
            }
        }
    }

    static void calculateSection3(int x, int y, int d1, int d2) {
        int standard = y - d1;
        for (int r = x + d1; r < n; r++) {
            for (int c = 0; c < standard; c++) {
                people[2] += graph[r][c];
            }
            // 탐색하는 행이 개리맨더링 마름모 제일 아래쪽에 도달하는 순간부터는 탐색할 열이 증가하지 않음
            if (r < x + d1 + d2) {
                standard++;
            }
        }
    }

    static void calculateSection4(int x, int y, int d1, int d2) {
        int standard = y + d2;
        for (int r = x + d2 + 1; r < n; r++) {
            for (int c = standard; c < n; c++) {
                people[3] += graph[r][c];
            }
            // 개리맨더링 마름모 제일 아래쪽까지는 탐색할 열을 하나씩 줄여준다
            if (r <= x + d1 + d2) {
                standard--;
            }
        }
    }

    static int calculateAnswer() {
        people[4] = total - (people[0] + people[1] + people[2] + people[3]);
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int p : people) {
            max = Math.max(max, p);
            min = Math.min(min, p);
        }
        return max - min;
    }
}
