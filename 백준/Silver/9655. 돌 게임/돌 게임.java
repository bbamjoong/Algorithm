import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        if (n % 2 == 0) {
            sb.append("CY");
        } else {
            sb.append("SK");
        }
        System.out.println(sb);
    }
}