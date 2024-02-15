import static java.lang.Math.min;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int n;
    static int[][] coordinates;
    static int[][] dp;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.setLength(0); // StringBuilder 초기화
            n = Integer.parseInt(br.readLine());
 
            /**
             * 좌표 배열 설정
             */
            coordinates = new int[n + 2][2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            coordinates[0][0] = Integer.parseInt(st.nextToken());
            coordinates[0][1] = Integer.parseInt(st.nextToken());
            coordinates[n + 1][0] = Integer.parseInt(st.nextToken());
            coordinates[n + 1][1] = Integer.parseInt(st.nextToken());
            for (int i = 1; i < n + 1; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                coordinates[i][0] = a;
                coordinates[i][1] = b;
            }
 
            /**
             * DP의 행 : 현재의 위치
             * DP의 열 : 현재 위치에 도달하기 위한 비용(비트마스킹 이용)
             */
            dp = new int[n + 2][1 << (n + 2)];
            int ans = dfs(0, 1); // 회사(location : 0)에서 시작
 
            sb.append("#" + test_case + " " + ans);
            System.out.println(sb.toString());
        }
    }
 
    static int dist(int a, int b) {
        return Math.abs(coordinates[a][0] - coordinates[b][0]) + Math.abs(
                coordinates[a][1] - coordinates[b][1]);
    }
 
    static int dfs(int location, int state) {
        /**
         * 비트마스킹을 이용해 도착지점에서 부터 시작지점까지 거꾸로 거리를 계산하는 것이 핵심.
         */
        int value = dp[location][state]; // dp에 저장해둔 value 이용
 
        if (value != 0) { // 이미 저장된 최소 value가 있다면 이후 과정 생략
            return value;
        }
 
        if (state == (1 << (n + 1)) - 1) { // 마지막 고객까지 방문 완료 시 거리 계산
            return dist(location, n + 1);
        }
 
        value = (int) 1e9; // 저장된 value가 없다면 최소값을 구하기 위해 큰 값으로 설정
        for (int i = 1; i <= n + 1; i++) {
            // i번째 비트가 활성화 되어있는가? -> i번째 위치를 방문했다면 이후 과정 생략
            if ((state & (1 << i)) != 0) {
                continue;
            }
            // 현재 위치에서 다음 위치로 이동할 경우 거리 계산
            int cal_dist = dfs(i, state | (1 << i)) + dist(location, i);
            // 현재 위치에 도달하는 최소 값 갱신
            value = min(value, cal_dist);
        }
 
        // 위 과정에서 구한 최소 value를 dp에 갱신
        dp[location][state] = value;
 
        // 현재 위치에 도착하기 위한 최소 value 리턴
        return value;
    }
}