import java.io.IOException;

public class Main {
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = nextInt();
        int m = nextInt();

        arr = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                arr[i][j] = nextInt() + arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1];
            }
        }

        for (int i = 0; i < m; i++) {
            int x1 = nextInt() - 1;
            int y1 = nextInt() - 1;
            int x2 = nextInt();
            int y2 = nextInt();

            sb.append(arr[x2][y2] + arr[x1][y1] - arr[x2][y1] - arr[x1][y2]).append("\n");
        }
        System.out.println(sb);
    }

    static final int BUFFER_SIZE = 1 << 13;
    static byte[] buffer = new byte[BUFFER_SIZE];
    static int bufferLen, bufferIdx;

    static int nextInt() throws IOException {
        byte b;
        int n = 0;
        while ((b = read()) <= 32)
            ;
        do {
            n = (n << 3) + (n << 1) + (b & 15);
        } while (isNumber(b = read()));

        return n;
    }

    static boolean isNumber(byte b) {
        return 47 < b && b < 58;
    }

    static byte read() throws IOException {
        if (bufferIdx == bufferLen) {
            bufferLen = System.in.read(buffer, bufferIdx = 0, BUFFER_SIZE);
            if (bufferLen == -1) {
                buffer[0] = -1;
            }
        }
        return buffer[bufferIdx++];
    }
}