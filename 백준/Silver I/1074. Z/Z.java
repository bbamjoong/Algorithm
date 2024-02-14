import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int r;
    static int c;
    static int ans = -1;

    static void check(int x, int y, int size) {
        if (x == r && y == c) {
            ans++;
            System.out.println(ans);
            return;
        }

        if (size == 0) {
            ans++;
            return;
        }

        if (!(x <= r && r < x + Math.pow(2, size) && y <= c && c < y + Math.pow(2, size))) {
            ans += (int) Math.pow(2, size * 2);
            return;
        }

        check(x, y, size - 1);
        check(x, y + (int) Math.pow(2, size - 1), size - 1);
        check(x + (int) Math.pow(2, size - 1), y, size - 1);
        check(x + (int) Math.pow(2, size - 1), y + (int) Math.pow(2, size - 1), size - 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        check(0, 0, n);
    }
}
