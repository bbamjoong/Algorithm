import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Line {
        int x1, y1, x2, y2;

        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    static int n;
    static Line[] lines;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        lines = new Line[n + 1];
        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        int x1, y1, x2, y2;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            lines[i] = new Line(x1, y1, x2, y2);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                // 두 선분이 일직선으로 만나거나, cross로 만나거나.
                int iParent = find(i);
                int jParent = find(j);

                if (iParent != jParent) {
                    if (isCrossed(lines[i], lines[j])) { // cross 검증을 해야함. 올바른경우만 union.
                        union(i, j);
                    }
                }
            }
        }

        int[] cnt = new int[n + 1];
        int max = 0;
        int size = 0;

        for (int i = 1; i <= n; i++) { // 몇번 분리집합이 가장 큰지 체크
            cnt[find(i)]++;
        }

        for (int i = 1; i <= n; i++) {
            if (cnt[i] == 0) {
                continue;
            }
            size++;

            max = Math.max(max, cnt[i]);
        }

        System.out.println(size + "\n" + max);
    }

    public static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        int result = (x1 * y2 + x2 * y3 + x3 * y1) - (y1 * x2 + y2 * x3 + y3 * x1);
        if (result == 0) {
            return 0;
        }
        if (result > 0) { // 시계
            return 1;
        }
        return -1; // 반시계
    }

    public static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x > y) {
            parents[x] = y;
            return;
        }

        parents[y] = x;
    }

    public static boolean isCrossed(Line l1, Line l2) {
        // 체크 두번 해야 됨. |  ----  같은 경우가 있을 수 있음.
        int check1 = ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x1, l2.y1) * ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x2, l2.y2);
        int check2 = ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x1, l1.y1) * ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x2, l1.y2);

        if (check1 == 0 && check2 == 0) { // 일직선인 경우
            return isOverlapped(l1, l2);
        }
        return check1 <= 0 && check2 <= 0; // ㅗ 와 같은 경우도 있을 수 있기 때문에 <= 0 으로 처리해야됨.
    }

    public static boolean isOverlapped(Line l1, Line l2) {
        // 선분이 --     ---  와 같이 떨어져 있는 경우만 생각.
        if (Math.max(l1.x1, l1.x2) < Math.min(l2.x1, l2.x2)) {
            return false;
        }
        if (Math.max(l2.x1, l2.x2) < Math.min(l1.x1, l1.x2)) {
            return false;
        }

        if (Math.max(l1.y1, l1.y2) < Math.min(l2.y1, l2.y2)) {
            return false;
        }
        if (Math.max(l2.y1, l2.y2) < Math.min(l1.y1, l1.y2)) {
            return false;
        }
        return true;
    }
}