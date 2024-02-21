import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<Character> vowel = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    static ArrayList<String> arr = new ArrayList<>();
    static ArrayList<Character> res = new ArrayList<>();
    static int n, m;

    public static void main(String[] args) throws Exception {
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr.add(st.nextToken());
        }

        arr.sort(String::compareTo);

        dfs();
    }

    // 모음, 자음 개수를 반환하는 함수
    static int[] count_vc(ArrayList<Character> list) {
        int[] counts = new int[2];
        for (char c : list) {
            if (vowel.contains(c)) {
                counts[0]++;
            } else {
                counts[1]++;
            }
        }
        return counts;
    }

    static void dfs() {
        if (res.size() == n) {
            int[] counts = count_vc(res);
            // 모음 1개 이상, 자음 2개 이상일 시 출력
            if (counts[0] >= 1 && counts[1] >= 2) {
                StringBuilder sb = new StringBuilder();
                for (char c : res) {
                    sb.append(c);
                }
                System.out.println(sb);
            }
            return;
        }

        for (String s : arr) {
            if (res.isEmpty() || res.get(res.size() - 1) < s.charAt(0)) {
                res.add(s.charAt(0));
                dfs();
                res.remove(res.size() - 1);
            }
        }
    }
}
