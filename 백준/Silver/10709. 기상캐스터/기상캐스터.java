import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] board = new int[H][W];

        for (int i = 0; i < H; i++) {
            String input = br.readLine();
            for (int j = 0; j < W; j++) {
                board[i][j] = (input.charAt(j) == 'c') ? 0 : -1;
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (board[i][j] == 0) {
                    for (int k = j + 1; k < W; k++) {
                        if (board[i][k] == -1) {
                            board[i][k] = board[i][k - 1] + 1;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
