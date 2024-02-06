import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        for (int test_case = 1; test_case < 11; test_case++) {
            sb.append("#").append(test_case).append(" ");
            int ans = 1; //답

            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); // 노드의 번호
                String b = st.nextToken(); // 노드에 들어갈 String

                boolean isParent = false; // 부모니 아니니?
                while (st.hasMoreTokens()) { // 만약 리프가 있다면 추가해줘야겠지
                    st.nextToken();
                    isParent = true;
                }

                if (isParent) { // 부모는 무조건 연산자여야함
                    try {
                        Integer.parseInt(b);
                        ans = 0;
                    } catch (NumberFormatException e) {
                        continue;
                    }
                } else { // 자식인데
                    if (b.equals("+") || b.equals("-") || b.equals("*") || b.equals("/")) {
                        ans = 0;
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}