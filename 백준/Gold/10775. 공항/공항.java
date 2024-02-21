import java.io.BufferedReader;
import java.io.InputStreamReader;

// 완전탐색 시 10^5 * 10^5 => 시간초과
// 경로 압축이 필요함
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int g; // 게이트 개수
    static int p; // 비행기 개수
    static int[] parent; // 도킹 완료하면 1로 부모 변경, 도킹 불가능하면 0으로 부모 변경

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
        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());

        parent = new int[g + 1];
        for (int i = 0; i < g + 1; i++) { // 게이트의 부모를 저장함.
            parent[i] = i;
        }

        int cnt = 0;

        for (int i = 0; i < p; i++) {
            int num = Integer.parseInt(br.readLine());
            if (find(num) == 0) {
                break;
            }
            cnt++;
            union(find(num), find(num) - 1); // 앞 칸에 도킹함.
        }

        System.out.println(cnt);
    }
}