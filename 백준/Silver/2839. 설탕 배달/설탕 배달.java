import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int res = 0;

        while (n > 0) {
            if (n % 5 == 0) {
                res += n / 5;
                break;
            }
            n -= 3;
            res++;
        }
        if (n < 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(res);
    }
}