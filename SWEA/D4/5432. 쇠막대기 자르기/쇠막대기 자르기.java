import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case < T + 1; test_case++) {
            String a = br.readLine();
            int ans = 0;
            int stack = 0;

            for (char c : a.replace("()", "@").toCharArray()) {
                if (c == '(') {
                    stack++;
                } else if (c == '@') {
                    ans += stack;
                } else {
                    stack--;
                    ans++;
                }
            }
            sb.append("#").append(test_case).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
