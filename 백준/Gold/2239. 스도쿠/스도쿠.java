import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int[][] arr;
    static int SIZE = 9;
    static List<Point> points;

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean dfs(int cnt) {
        if (cnt == points.size()) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    sb.append(arr[i][j]);
                }
                sb.append("\n");
            }
            return true;
        }

        // 1 ~ 9 중 어떤 값을 넣을까요?
        for (int i = 1; i < SIZE + 1; i++) {
            int x = points.get(cnt).x;
            int y = points.get(cnt).y;

            // 행, 열, 3x3 행열 모두 조건이 맞다면
            if (checkRow(x, i) && checkCol(y, i) && checkDiag(x, y, i)) {
                arr[x][y] = i;
                if (dfs(cnt + 1)) {
                    return true;
                }

                // 백트래킹
                arr[x][y] = 0;
            }
        }
        return false;
    }

    static boolean checkRow(int x, int i) {
        for (int j = 0; j < SIZE; j++) {
            if (i == arr[x][j]) {
                return false;
            }
        }
        return true;
    }

    static boolean checkCol(int y, int i) {
        for (int j = 0; j < SIZE; j++) {
            if (i == arr[j][y]) {
                return false;
            }
        }
        return true;
    }

    static boolean checkDiag(int x, int y, int i) {
        // 9개의 구역 가장 왼쪽 위 점 찾는 과정
        int nx = x / 3 * 3;
        int ny = y / 3 * 3;

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                if (i == arr[nx + j][ny + k]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        arr = new int[SIZE][SIZE];
        points = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {
            String numbers = br.readLine();
            for (int j = 0; j < SIZE; j++) {
                int num = numbers.charAt(j) - '0';
                arr[i][j] = num;

                if (num == 0) {
                    points.add(new Point(i, j));
                }
            }
        }
        dfs(0);
        System.out.println(sb);
    }
}