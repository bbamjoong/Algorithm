import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        StringBuilder sb = new StringBuilder();
        if (n % 7 == 0 || n % 7 == 2) {
            sb.append("CY");
        } else {
            sb.append("SK");
        }
        System.out.println(sb.toString());
    }
}