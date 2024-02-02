import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 1; i < n + 1; i++) {
            arr.add(i);
        }

        sb.append("<");

        int num = 0;
        for (int i = 0; i < n; i++) {
            num += k - 1;
            if (num >= arr.size()) {
                num %= arr.size();
            }

            if (i == n - 1) {
                sb.append(arr.get(num)).append("");
            } else {
                sb.append(arr.get(num)).append(", ");
            }

            arr.remove(arr.get(num));
        }

        sb.append(">");

        System.out.println(sb);
    }
}