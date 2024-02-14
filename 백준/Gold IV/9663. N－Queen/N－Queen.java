import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    /**
     * 각 행마다 퀸을 두기 때문에  열, 대각선 두개만 체크합니다.
     */
    static int n;
    static int[] col_check; // 열 체크
    static int[] diag_check1; // 체스판 '/'
    static int[] diag_check2; // 체스판 '\'
    static int ans;

    static void queen(int i) {
        if (i == n) {
            ans++;
            return;
        }
        for (int j = 0; j < n; j++) {
            /**
             * ex) (2,3) (1,4)는 같은 대각선에 위치한다. -> i + j 가 같다.
             * ex) (2,3) (1,2)는 같은 대각선에 위치한다. -> j - i or i - j가 같다.
             * 만약 3 X 3 체스판이다 -> "\"대각선의 j - i는 -2, -1, 0, 1, 2 값을 갖는데
             * 이는 배열에서 0, 1, 2, 3, 4의 index를 가져야한다. 따라서 (j-1) + (n-1)
             */
            if (col_check[j] == 0 && diag_check1[i + j] == 0 && diag_check2[(j - i) + (n - 1)] == 0) {
                col_check[j] = 1;
                diag_check1[i + j] = 1;
                diag_check2[(j - i) + (n - 1)] = 1;
                queen(i + 1);
                col_check[j] = 0;
                diag_check1[i + j] = 0;
                diag_check2[(j - i) + (n - 1)] = 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        col_check = new int[n];
        diag_check1 = new int[2 * n - 1]; // 대각선의 개수 2*n - 1
        diag_check2 = new int[2 * n - 1]; // 대각선의 개수 2*n - 1

        queen(0);
        System.out.println(ans);
    }
}