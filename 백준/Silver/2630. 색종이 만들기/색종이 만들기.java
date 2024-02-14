import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int white = 0;
    static int blue = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        check(arr, n);
        System.out.println(white);
        System.out.println(blue);
    }

    static void check(int[][] arr, int length) {
        int num1 = arr[0][0];
        boolean stop = false;

        for (int i = 0; i < length; i++) {
            if (stop) {
                break;
            }
            for (int j = 0; j < length; j++) {
                if (arr[i][j] != num1) {
                    stop = true;
                    break;
                }
            }
        }

        if (!stop) {
            if (num1 == 0) {
                white++;
            } else {
                blue++;
            }
        } else {
            if (length == 2) {
                for (int i = 0; i < length; i++) {
                    for (int j = 0; j < length; j++) {
                        if (arr[i][j] == 0) {
                            white++;
                        } else {
                            blue++;
                        }
                    }
                }
            } else {
                check(subArray(arr, 0, length / 2, 0, length / 2), length / 2);
                check(subArray(arr, 0, length / 2, length / 2, length), length / 2);
                check(subArray(arr, length / 2, length, 0, length / 2), length / 2);
                check(subArray(arr, length / 2, length, length / 2, length), length / 2);
            }
        }
    }

    static int[][] subArray(int[][] arr, int startRow, int endRow, int startCol, int endCol) {
        int[][] sub = new int[endRow - startRow][endCol - startCol];
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                sub[i - startRow][j - startCol] = arr[i][j];
            }
        }
        return sub;
    }
}
