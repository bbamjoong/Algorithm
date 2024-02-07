import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int m;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken()); // 연산 수

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < r; i++) {
            int command = Integer.parseInt(st.nextToken());

            if (command == 1) {
                one();
            } else if (command == 2) {
                two();
            } else if (command == 3) {
                three();
            } else if (command == 4) {
                four();
            } else if (command == 5) {
                five();
            } else if (command == 6) {
                six();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void one() {
        for (int i = 0; i < n / 2; i++) {
            int[] tmpArr = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = tmpArr;
        }
    }

    static void two() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                int tmp = arr[i][j];
                arr[i][j] = arr[i][m - 1 - j];
                arr[i][m - 1 - j] = tmp;
            }
        }
    }

    static void three() {
        int[][] tmpArr = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmpArr[j][n - i - 1] = arr[i][j];
            }
        }

        int tmp = n; // 배열의 행, 열 크기도 바꿔주어야 한다.
        n = m;
        m = tmp;

        arr = tmpArr;
    }

    static void four() {
        int[][] tmpArr = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmpArr[m - j - 1][i] = arr[i][j];
            }
        }

        int tmp = n; // 배열의 행, 열 크기도 바꿔주어야 한다.
        n = m;
        m = tmp;

        arr = tmpArr;
    }

    static void five() {
        int[][] tmpArr = new int[n][m];

        // 1 -> 2
        for (int i = 0; i < n / 2; i++) {
            for (int j = m / 2; j < m; j++) {
                tmpArr[n / 2 + i][j] = arr[i][j];
            }
        }
        // 2 -> 3
        for (int i = n / 2; i < n; i++) {
            for (int j = m / 2; j < m; j++) {
                tmpArr[i][j - m / 2] = arr[i][j];
            }
        }
        // 3 -> 4
        for (int i = n / 2; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                tmpArr[i - n / 2][j] = arr[i][j];
            }
        }

        // 4 -> 1
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                tmpArr[i][m / 2 + j] = arr[i][j];
            }
        }
        arr = tmpArr;

    }

    static void six() {
        int[][] tmpArr = new int[n][m];

        // 1 -> 4
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                tmpArr[n / 2 + i][j] = arr[i][j];

            }
        }

        // 4 -> 3
        for (int i = n / 2; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                tmpArr[i][j + m / 2] = arr[i][j];
            }
        }

        // 3 -> 2
        for (int i = n / 2; i < n; i++) {
            for (int j = m / 2; j < m; j++) {
                tmpArr[i - n / 2][j] = arr[i][j];
            }
        }

        // 2 -> 1
        for (int i = 0; i < n / 2; i++) {
            for (int j = m / 2; j < m; j++) {
                tmpArr[i][j - m / 2] = arr[i][j];
            }
        }

        arr = tmpArr;

    }
}