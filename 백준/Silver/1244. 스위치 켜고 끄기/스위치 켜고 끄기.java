import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        //1 켜져있음. 0 꺼져있음.
        // 남학생 : 스위치 번호가 자기가 받은 수의 "배수" -> 바꿈.
        // 여학생 : 좌우대칭 탐색. 좌우가 같으면 index확장.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int a;
        int b;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                man(b - 1);
            } else {
                women(b - 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append(" ");
            if ((i + 1) % 20 == 0) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    static void man(int num) {
        for (int i = num; i < n; i += num + 1) {
            change(i);
        }
    }

    static void women(int num) {
        int start = num - 1;
        int end = num + 1;

        if (num == 0) {
            change(num);
        } else if (num == n - 1) {
            change(num);
        } else {
            while (start != 0 && end != n - 1) {
                if (arr[start] == arr[end]) {
                    start--;
                    end++;
                } else {
                    break;
                }
            }

            if (arr[start] != arr[end]) {
                start++;
                end--;
            }

            for (int i = start; i < end + 1; i++) {
                change(i);
            }
        }
    }

    static void change(int num) {
        if (arr[num] == 1) {
            arr[num] = 0;
        } else {
            arr[num] = 1;
        }
    }
}