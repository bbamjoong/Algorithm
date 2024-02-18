import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());

        int sqrtA = (int) Math.sqrt(a);
        if (sqrtA * sqrtA < a) {
            sqrtA++;
        }
        int sqrtB = (int) Math.sqrt(b);
        
        int sum = 0;
        int min = sqrtA * sqrtA;
        for (int i = sqrtA; i <= sqrtB; i++) {
            sum += i * i;
        }
        
        if (sum == 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(sum);
        System.out.println(min);
    }
}