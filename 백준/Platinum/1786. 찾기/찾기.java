import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int ans = 0;
    static ArrayList<Integer> arr = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    static int[] makeTable(String pattern) {
        int n = pattern.length();
        int[] table = new int[n];

        int idx = 0;
        for (int i = 1; i < n; i++) {
            // 접두사와 접미사의 일치하는 개수임.
            // 일치하지 않으면 일치하게 만들기 위해 다시 idx를 1 빼줘버림.
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

    static void KMP(String parent, String child) {
        int[] table = makeTable(child);

        int parentLength = parent.length();
        int childLength = child.length();

        int idx = 0; // 몇개가 대응되냐
        for (int i = 0; i < parentLength; i++) {
            // 일치하지 않으면 일치하게 만들기 위해 다시 idx를 1개 빼준다.
            while (idx > 0 && parent.charAt(i) != child.charAt(idx)) {
                idx = table[idx - 1];
            }

            if (parent.charAt(i) == child.charAt(idx)) {
                // 자식의글자 끝까지 일치했으면 끝!
                if (idx == childLength - 1) {
                    // i는 끝쪽 위치, idx는 글자 개수니까 i - idx + 1
                    ans++;
                    arr.add(i - idx + 1);
                    idx = table[idx];
                } else {
                    idx++;
                }
            }
        }
    }

    static void printAnswer() {
        sb.append(ans).append("\n");
        for (Integer i : arr) {
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String parent = br.readLine();
        String child = br.readLine();

        KMP(parent, child);

        printAnswer();
    }
}