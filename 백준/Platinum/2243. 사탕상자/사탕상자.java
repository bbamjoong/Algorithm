import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int SIZE = 1_000_001; // 사탕 맛 100만개
    static int[] tree = new int[SIZE * 4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if (A == 1) {
                int candy = query(1, SIZE, 1, B); // B개만큼 꺼내기
                sb.append(candy).append("\n");
            } else {
                int C = Integer.parseInt(st.nextToken()); // B맛을 C개만큼 조작
                update(1, SIZE, 1, B, C);
            }
        }
        System.out.println(sb.toString());
    }

    static int query(int s, int e, int idx, int target) {
        if (s == e) {
            update(1, SIZE, 1, s, -1); // s번째 idx까지 갱신하라는 의미.
            return s;
        }

        int mid = (s + e) / 2;
        if (target <= tree[idx * 2]) { // 개수를 비교. target이 더 크다면 그 다음 우선순위로 가겠지.
            return query(s, mid, idx * 2, target);
        } else {
            return query(mid + 1, e, idx * 2 + 1, target - tree[idx * 2]);
        }
    }

    static void update(int s, int e, int idx, int target, int diff) { // idx는 시작점. target은 목표.
        if (target < s || e < target) { // 범위 밖이면 끝
            return;
        }

        tree[idx] += diff; // 개수 갱신

        if (s == e) { // 리프 노드면 끝
            return;
        }

        int mid = (s + e) / 2;
        update(s, mid, idx * 2, target, diff);
        update(mid + 1, e, idx * 2 + 1, target, diff);
    }
}