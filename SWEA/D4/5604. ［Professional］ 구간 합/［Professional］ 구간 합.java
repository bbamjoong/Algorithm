import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long start, end;

    static long[] arr;

    static long num;
    static long ans;

    static void setInputs() throws Exception {
        st = new StringTokenizer(br.readLine());
        start = Long.parseLong(st.nextToken());
        end = Long.parseLong(st.nextToken());
    }

    static long findNumbers(long n) {
        ans = 0; // 리턴할 답은 0으로 초기화
        num = 1; // num의 초기값은 1
        arr = new long[10]; // 0 ~ 9 까지 숫자 몇개 있는지 저장
        while (n > 0) {
            n = setToNine(n);

            if (n < 10) { // 10 미만이면 끝 자리수가 1 ~ 9까지 다양할것임.
                for (int i = 1; i <= n; i++) {
                    ans += i * num;
                }
                return ans;
            }

            // 10 이상이라면 아래의 과정을 반드시 1번은 거침.
            for (int i = 0; i <= 9; i++) {
                ans += i * ((n / 10 + 1) * num);
            }

            num *= 10; // 자리수를 나타냄. 따라서 자리수가 커질 때마다 10씩 곱해줘야함.
            n /= 10; // 현재 숫자를 10씩 나눠줌.
        }

        return ans;
    }

    static long setToNine(long n) { // 끝 자리수를 9로 맞춤.
        while (n % 10 != 9 && n > 0) {
            String s = String.valueOf(n);
            for (int i = 0; i < s.length(); i++) {
                int digit = s.charAt(i) - '0'; // 숫자 정보
                ans += num * digit;
            }
            n--;
        }
        return n;
    }

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            setInputs();
            long tmp1 = findNumbers(start - 1);
            long tmp2 = findNumbers(end);

            sb.append("#").append(tc).append(" ").append(tmp2 - tmp1).append("\n");
        }
        System.out.printf(sb.toString());
    }
}