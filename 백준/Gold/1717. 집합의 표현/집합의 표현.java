import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
        return parent[x] = find(parent[x]); // find를 할 때 parent 값을 업데이트 해주기 때문에 속도가 더 빨라짐
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
        n = nextInt();
        m = nextInt();

        parent = new int[n + 1];
        for (int i = 1; i < n + 1; i++) { // 자기 자신을 부모로 갖는다.
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) { // m번 연산 실행
            int command = nextInt();
            int a = nextInt();
            int b = nextInt();

            if (command == 0) {
                union(a, b);
                continue;
            }

            if (find(a) == find(b)) { // 부모가 같으면
                sb.append("yes").append("\n");
            } else { // 부모가 다르면
                sb.append("no").append("\n");
            }

        }
        System.out.println(sb);
    }

    static final int SIZE = 1 << 13;
    static byte[] buffer = new byte[SIZE];
    static int index, size;

    static int nextInt() throws Exception {
        int n = 0;
        byte c;
        while ((c = read()) <= 32)
            ;
        boolean neg = c == '-' ? true : false;
        if (neg) {
            c = read();
        }
        do {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        while (isNumber(c = read()));
        if (neg) {
            return -n;
        }
        return n;
    }

    static boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    static byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) {
                buffer[0] = -1;
            }
        }
        return buffer[index++];
    }
}
