import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int m;
    static int[] parent; // 부모 노드 저장

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parent[b] = a;
            return;
        }
        parent[a] = b;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i < n + 1; i++) { // 자기 자신을 부모로 갖는다.
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) { // m번 연산 실행
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command == 0) {
                union(a, b);
            } else if (command == 1) {
                if (find(a) == find(b)) { // 부모가 같으면
                    sb.append("yes").append("\n");
                } else { // 부모가 다르면
                    sb.append("no").append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}