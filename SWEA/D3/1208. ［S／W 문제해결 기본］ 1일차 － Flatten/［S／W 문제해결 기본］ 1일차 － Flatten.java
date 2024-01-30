import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int dump;
    static int[] arr;
    static int maxIndex = 0;
    static int minIndex = 101;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int test_case = 1; test_case < 11; test_case++) {
            dump = Integer.parseInt(br.readLine());
            arr = new int[101];
            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()) {
                int a = Integer.parseInt(st.nextToken());
                arr[a]++;

                if (a > maxIndex) {
                    maxIndex = a;
                }

                if (a < minIndex) {
                    minIndex = a;
                }
            }

            while (maxIndex - minIndex > 1 && dump != 0) {
                arr[maxIndex]--;
                arr[maxIndex - 1]++;
                arr[minIndex]--;
                arr[minIndex + 1]++;
                dump--;

                if (arr[maxIndex] == 0) {
                    maxIndex--;
                }
                if (arr[minIndex] == 0) {
                    minIndex++;
                }
            }
            sb.append("#").append(test_case).append(" ").append(maxIndex - minIndex).append("\n");
        }
        System.out.printf(sb.toString());
    }
}