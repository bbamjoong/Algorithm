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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String pattern = br.readLine();

        int[] arr = makeTable(pattern);
        // 접미사의 길이를 구해줌. -> 전체 길이에서 접미사 길이를 빼주면 된다.
        // 어차피 접두사가 뒤에 오면 똑같아지니까
        int suffixLength = arr[pattern.length() - 1];

        System.out.println(n - suffixLength);
    }
}