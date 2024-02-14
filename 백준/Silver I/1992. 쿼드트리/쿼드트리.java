import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    public static int[][] arr;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            String num = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = num.charAt(j) - '0';
            }
        }

        QuadTree(0, 0, n);
        System.out.println(sb);
    }

    public static void QuadTree(int x, int y, int size) {
        if (isPossible(x, y, size)) {
            sb.append(arr[x][y]);
            return;
        }

        size /= 2;

        sb.append('(');

        QuadTree(x, y, size);
        QuadTree(x, y + size, size);
        QuadTree(x + size, y, size);
        QuadTree(x + size, y + size, size);

        sb.append(')');


    }

    static boolean isPossible(int x, int y, int size) {
        int num = arr[x][y]; // 제일 구석 값

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (num != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}