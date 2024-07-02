import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int k;

    static int start;
    static int end;
    static int ans = 0;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        init();
        moveBasket();        // 범위 내에 사과가 존재하면 이동 X, 범위 밖이라면 왼쪽, 오른쪽 판단 후 최소거리 이동
        System.out.println(ans);
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());

        start = 1;
        end = m;
    }

    static void moveBasket() throws IOException {
        for (int i = 0; i < k; i++) {
            int num = Integer.parseInt(br.readLine());
            if (start > num) {
                ans += start - num;
                end -= start - num;
                start = num;
            } else if (end < num) {
                ans += num - end;
                start += num - end;
                end = num;
            }
        }
    }
}