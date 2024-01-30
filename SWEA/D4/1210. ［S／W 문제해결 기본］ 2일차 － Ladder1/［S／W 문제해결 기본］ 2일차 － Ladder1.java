import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int x;
    static int y;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int test_case = 1; test_case < 11; test_case++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[100][100];
            StringTokenizer st;
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int j = 0; j < 100; j++) {
                if (arr[99][j] == 2) {
                    x = 99;
                    y = j;
                }
            }

            while (x > 0) {
                if (checkRange(x, y + 1) && arr[x][y + 1] == 1) {
                    while (checkRange(x, y + 1) && arr[x][y + 1] == 1) {
                        y++;
                    }
                } else if (checkRange(x, y - 1) && arr[x][y - 1] == 1) {
                    while (checkRange(x, y - 1) && arr[x][y - 1] == 1) {
                        y--;
                    }
                }
                x--;
            }
            sb.append("#" + test_case + " " + y + "\n");
        }
        System.out.println(sb.toString());
    }


    static boolean checkRange(int x, int y) {
        return !(x < 0 || y < 0 || x >= 100 || y >= 100);
    }
}