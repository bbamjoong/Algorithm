import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int num;
    static long k; // 최대 20!

    static int[] arr;
    static boolean[] visited;
    static long[] fArr;

    static List<Integer> ansList;
    static long ans;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        init();
        calFactorial();

        if (num == 1) { // k번째 순서의 수열을 출력
            k = Long.parseLong(st.nextToken());
            // if) n == 4.  맨앞에 1이 올 경우. 남은 3가지 숫자의 정렬 가지수 3!
            // 계산은 하되, 필요없는 계산하지말고 팩토리얼 값 빼주면서 가자.
            for (int i = 0; i < n; i++) { // 몇번째 자리에 들어갈지.
                for (int j = 1; j <= n; j++) { // 사용할 숫자
                    if (visited[j]) {
                        continue;    //이미 사용된 숫자는 패스
                    }
                    if (k - fArr[n - 1 - i] > 0) {
                        k -= fArr[n - 1 - i];
                    } else {
                        ansList.add(j);
                        visited[j] = true;
                        break;
                    }
                }
            }
            for (Integer num : ansList) {
                sb.append(num).append(" ");
            }

        } else if (num == 2) {
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < n; i++) { // 확인중인 자리수
                for (int j = 1; j < arr[i]; j++) { // 확인중인 자리수에 올 수 있었던 더 작은 숫자들 확인.
                    if (!visited[j]) {
                        ans += fArr[n - 1 - i];
                    }

                }
                visited[arr[i]] = true;
            }
            sb.append(ans);
        }
        System.out.print(sb.toString());
    }

    static void calFactorial() {
        fArr = new long[21];
        fArr[0] = 1;
        for (int i = 1; i <= 20; i++) {
            fArr[i] = i * fArr[i - 1];
        }
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        num = Integer.parseInt(st.nextToken());
        long k = 0;
        arr = new int[n + 1];
        visited = new boolean[n + 1];
        ansList = new ArrayList<>();
        ans = 1;
    }
}