import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long mn;
    static long mx;

    static boolean[] arr;
    static int ans;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        mn = Long.parseLong(st.nextToken());
        mx = Long.parseLong(st.nextToken());

        int size = (int) (mx - mn);
        arr = new boolean[size + 1];

        findPowNum();

        for (int i = 0; i < size + 1; i++) {
            if (!arr[i]) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    static void findPowNum() {
        for (long i = 2; i <= Math.sqrt(mx); i++) {
            long powNum = i * i; // 현재 제곱수 계산

            long start;
            if (mn % powNum == 0) { // 나눠 떨어지면 arr[0] 부터 제곱수
                start = mn / powNum;
            } else {
                start = (mn / powNum) + 1;
            }

            // 찾은 start 포인트 부터 변환
            for (long j = start; j * powNum <= mx; j++) {
                if (arr[(int) (j * powNum - mn)]) {
                    continue;
                }
                arr[(int) (j * powNum - mn)] = true;
            }
        }
    }
}