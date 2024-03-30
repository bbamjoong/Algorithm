import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int cnt;
    static int n;
    static String parent;
    static String pattern;

    static void setInputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        n = Integer.parseInt(br.readLine());

        // 글자
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(st.nextToken());
        }
        parent = sb.toString() + sb.toString();

        // 패턴
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(st.nextToken());
        }
        pattern = sb.toString();
    }

    static void KMP(String parent, String child) {
        int[] table = makeTable(child);

        int parentLength = parent.length();
        int childLength = child.length();

        int idx = 0; // 몇개가 대응되냐
        for (int i = 0; i < parentLength - 1; i++) { // 돌림판은 원형이니까 처음, 마지막이 겹치지 않게 최대 length에서 1을 뺀 것을 경계값으로.
            // 일치하지 않으면 일치하게 만들기 위해 다시 idx를 1개 빼준다.
            while (idx > 0 && parent.charAt(i) != child.charAt(idx)) {
                idx = table[idx - 1];
            }

            if (parent.charAt(i) == child.charAt(idx)) {
                // 자식의글자 끝까지 일치했으면 끝!
                if (idx == childLength - 1) {
                    // i는 끝쪽 위치, idx는 글자 개수니까 i - idx + 1
                    cnt++;
                    idx = table[idx];
                } else {
                    idx++;
                }
            }
        }
    }

    static int[] makeTable(String pattern) {
        int n = pattern.length();
        int[] table = new int[n];

        int idx = 0;
        for (int i = 1; i < n; i++) {
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

    static void printAnswer() {
        int k = gcd(n, cnt);
        System.out.println((cnt / k) + "/" + (n / k));
    }

    static int gcd(int a, int b) {
        while (b > 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static void main(String[] args) throws Exception {
        setInputs();
        KMP(parent, pattern);
        printAnswer();
    }
}