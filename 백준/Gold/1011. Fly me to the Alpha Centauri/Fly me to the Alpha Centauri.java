import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int x, y;
    static long[] dist;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        dist = new long[t];

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            dist[i] = getMovingCount(y - x); // 거리만큼 계산
        }

        for (int i = 0; i < t; i++) {
            sb.append(dist[i]);
            if (i != t - 1) {  // 마지막 test case 아니면 개행 추가
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    static long getMovingCount(int n) {
        long maxMovingDist = (long) Math.sqrt(n);
        long k = n - (long) Math.pow(maxMovingDist, 2);

        // 최대거리 이하만큼 최대한 이동할 것임. 어떻게 움직이는지는 모르겠으나 횟수는 구할 수 있음
        k = (k + maxMovingDist - 1) / maxMovingDist; // Ceil 계산.

        return 2 * maxMovingDist - 1 + k;
    }
}