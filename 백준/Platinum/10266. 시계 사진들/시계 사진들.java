import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int SIZE = 360_000;
    static int[] parent;
    static int[] pattern;

    static void setInputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        // 시계1
        parent = new int[SIZE * 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            parent[num] = 1;
            parent[SIZE + num] = 1;
        }

        // 시계2
        pattern = new int[SIZE];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            pattern[num] = 1;
        }
    }

    static boolean KMP(int[] parent, int[] child) {
        int[] table = makeTable(child);

        int parentLength = parent.length;
        int childLength = child.length;

        int idx = 0;
        for (int i = 0; i < parentLength - 1; i++) {
            while (idx > 0 && parent[i] != child[idx]) {
                idx = table[idx - 1];
            }

            if (parent[i] == child[idx]) {
                if (idx == childLength - 1) {
                    return true;
                } else {
                    idx++;
                }
            }
        }
        return false;
    }

    static int[] makeTable(int[] pattern) {
        int n = pattern.length;
        int[] table = new int[n];

        int idx = 0;
        for (int i = 1; i < n; i++) {
            while (idx > 0 && pattern[i] != pattern[idx]) {
                idx = table[idx - 1];
            }

            if (pattern[i] == pattern[idx]) {
                idx++;
                table[i] = idx;
            }
        }

        return table;
    }


    public static void main(String[] args) throws Exception {
        setInputs();
        if (KMP(parent, pattern)) {
            System.out.println("possible");
        } else {
            System.out.println("impossible");
        }
    }
}