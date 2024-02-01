import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int[] singleDigitPrime = {2, 3, 5, 7};
    static int[] moreThanTwoDigitPrime = {1, 3, 7, 9};
    static int n;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i : singleDigitPrime) {
            dfs(1, i);
        }

        System.out.println(sb);
    }

    static void dfs(int depth, int res) {
        if (depth == n) {
            sb.append(res).append("\n");
            return;
        }

        for (int num : moreThanTwoDigitPrime) {
            int tmpRes = res * 10 + num;
            if (checkPrime(tmpRes)) {
                dfs(depth + 1, tmpRes);
            }
        }
    }

    static boolean checkPrime(int num) {
        /**
         * num = N * M = sqrt(num) * sqrt(num)이다.
         * N과 M 중 어떤 한 값은 sqrt(num)보다 클 수 있다.(N이라 하자)
         * 이 말은 다른 한 값은 sqrt(num)보다 작다는 뜻이다.(M이라 하자)
         * 그렇다면 M까지 소수 체크만 하면 모든 값을 체크할 수 있다는 뜻이다.
         * (sqrt(num)를 초과하는 숫자는 체크할 필요가 없다는 뜻이다.)
         * 따라서 sqrt(num)까지 체크해주도록 한다.
         */
        for (int i = 2; i < Math.sqrt(num) + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}