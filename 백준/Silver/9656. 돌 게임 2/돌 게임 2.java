import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 돌게임 2
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        if (n % 2 == 1) {
            sb.append("CY");
        } else {
            sb.append("SK");
        }
        System.out.println(sb.toString());
    }
}