import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < T + 1; tc++) {
            sb.append("#").append(tc).append(" ");

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            LinkedList<Integer> linkedList = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                linkedList.add(Integer.parseInt(st.nextToken()));
            }

            Collections.sort(linkedList);

            int end = linkedList.get(n - 1);
            int boongEoBbang = 0;
            int idx = 0; // 사람을 의미함
            boolean possible = true;

            if (linkedList.get(0) == 0) { // 0초에 사람있으면 안됨
                possible = false;
            } else {
                for (int i = 1; i < end + 1; i++) { // i는 시간을 의미함
                    if (i % m == 0) { // 시간이 지나면 붕어빵 추가
                        boongEoBbang += k;
                    }
                    if (idx < n) { // 마지막사람 전까지
                        if (i == linkedList.get(idx)) {
                            idx++;
                            if (boongEoBbang > 0) {
                                boongEoBbang--;
                            } else {
                                possible = false;
                                break;
                            }
                        }
                    }
                }
            }

            if (possible) {
                sb.append("Possible").append("\n");
                continue;
            }
            sb.append("Impossible").append("\n");
        }
        System.out.println(sb);
    }
}