import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                man(b, b);
            } else {
                women(b, 0);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            sb.append(arr[i]).append(" ");
            if (i % 20 == 0) {
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }

    static void man(int num, int width) {
        if (num > n) {
            return;
        }

        change(num);
        num += width;
        man(num, width);
    }

    static void women(int num, int width) {
        if (num - width < 1 || num + width > n) {
            return;
        }

        if (width == 0) {
            change(num);
        } else {
            if (arr[num - width] == arr[num + width]) {
                change(num - width);
                change(num + width);
            } else {
                return;
            }
        }

        women(num, width + 1);
    }

    static void change(int num) {
        if (arr[num] == 1) {
            arr[num] = 0;
        } else {
            arr[num] = 1;
        }
    }
}