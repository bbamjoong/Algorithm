import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;
    static int[] parent; // 부모 노드 저장
    static int[] depth; // depth 저장

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]); // find를 할 때 parent 값을 업데이트 해주기 때문에 속도가 더 빨라짐
    }

    static int union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (a < b) {
                parent[b] = a;
                depth[a] += depth[b];
                return depth[a];
            } else {
                parent[a] = b;
                depth[b] += depth[a];
                return depth[b];
            }
        }
        return depth[a];
    }

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int f = Integer.parseInt(br.readLine());
            parent = new int[2 * f];
            depth = new int[2 * f];
            for (int i = 0; i < 2 * f; i++) { // 자기 자신을 부모로 갖는다.
                parent[i] = i;
                depth[i] = 1; // depth = 개수도 저장함.
            }

            int idx = 0;
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < f; i++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                if (!map.containsKey(a)) { // 없는 이름이면 put
                    map.put(a, idx++);
                }

                if (!map.containsKey(b)) { // 없는 이름이면 put
                    map.put(b, idx++);
                }

                int cnt = union(map.get(a), map.get(b));
                sb.append(cnt).append("\n");
            }
        }
        System.out.println(sb);
    }
}