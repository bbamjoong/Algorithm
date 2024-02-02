import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> arr = new ArrayDeque<>();
        for (int i = 1; i < n + 1; i++) {
            arr.add(i);
        }

        if (n == 1) {
            sb.append(arr.poll());
        } else {
            while (true) {
                arr.pollFirst();
                int a = arr.pollFirst();
                arr.add(a);

                if (arr.size() == 1) {
                    sb.append(arr.poll());
                    break;
                }
            }
        }

        System.out.println(sb);
    }
}