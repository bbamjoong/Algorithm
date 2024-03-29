import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int ans = 0;
    static ArrayList<Integer> arr = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    static int[] makeTable(String pattern) {
        int n = pattern.length();
        int[] table = new int[n];

        int idx = 0;
        for (int i = 1; i < n; i++) {
            // 접두사와 접미사의 일치하는 개수임.
            // 일치하지 않으면 일치하게 만들기 위해 다시 idx를 1 빼줘버림.
            while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
                idx = table[idx - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(idx)) {
                idx++;
                table[i] = idx;
            }
        }

        return table;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String pattern = br.readLine();

            if (pattern.equals(".")) {
                break;
            }

            int[] arr = makeTable(pattern);
            int len = pattern.length();

            // 전체에서 접두사 길이를 뺀 것이 len 의 약수여야함.
            if (len % (len - arr[len - 1]) != 0) {
                sb.append("1").append("\n");
            } else { // 나머지 없이 잘 나누어 떨어지면 나누면 됩니다.
                sb.append((len / (len - arr[len - 1]))).append("\n");
            }
        }
        System.out.println(sb);
    }
}