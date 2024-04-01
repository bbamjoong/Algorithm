import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int p = 1_234_567_891; // 소수
    static int n;
    static int r;
    static long[] factorials;
    static long ans;

    static void setInputs() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        factorials = new long[n + 1];
        factorials[0] = 1; // nC0 = 1
        for (int i = 1; i <= n; i++) {
            factorials[i] = (factorials[i - 1] * i) % p;
        }
    }

    static long getAns(long n, int x) {
        if (x == 1) { // n^1 == n (mod p)
            return n % p;
        }

        long tmp = getAns(n, x / 2); // 절반 나눈 것을 계산

        if (x % 2 == 0) { // x가 짝수
            return (tmp * tmp) % p;
        } // x가 홀수
        return (n * getAns(n, x - 1)) % p;
    }

    static void fermat() {
        long a_1 = (factorials[r] * factorials[n - r]) % p; // a ^ -1
        long a_p_2 = getAns(a_1, p - 2); // a ^(p-2)
        ans = (factorials[n] * a_p_2) % p;
    }

    /**
     * {n! * (n-r)!} ^ -1을 a라고 한다. a^(p-2) = a^-1 이다. 따라서 n! * a^(p-2)가 정답이 된다.
     */

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            setInputs();
            fermat();
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.printf(sb.toString());
    }
}