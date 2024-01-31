import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[9];
        int sm = 0;
        for (int i = 0; i < 9; i++) {
            int a = Integer.parseInt(br.readLine());
            arr[i] = a;
            sm += a;
        }

        int idx1 = 0;
        int idx2 = 0;

        loop:
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (sm - arr[i] - arr[j] == 100) {
                    idx1 = i;
                    idx2 = j;

                    for (int k = 0; k < 9; k++) {
                        if (k != idx1 && k != idx2) {
                            sb.append(arr[k]).append("\n");
                        }
                    }
                    break loop;
                }
            }
        }

        System.out.println(sb);

    }
}