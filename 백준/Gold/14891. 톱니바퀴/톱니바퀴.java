import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    static LinkedList<Integer>[] arr = new LinkedList[4];
    static int[][] same = new int[4][2];
    static int[][] turn;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            arr[i] = new LinkedList<>();
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                arr[i].add(str.charAt(j) - '0');
            }
        }

        int n = Integer.parseInt(br.readLine());
        turn = new int[n][2];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                turn[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int[] info : turn) {
            for (int i = 0; i < 3; i++) {
                if (Objects.equals(arr[i].get(2), arr[i + 1].get(6))) {
                    same[i][1] = 1;
                    same[i + 1][0] = 1;
                } else {
                    same[i][1] = -1;
                    same[i + 1][0] = -1;
                }
            }
            change(info[0] - 1, info[1], 0);
        }
        int score = getScore();
        System.out.println(score);
    }

    static int getScore() {
        int score = 0;

        if (arr[0].get(0) == 1) {
            score += 1;
        }
        if (arr[1].get(0) == 1) {
            score += 2;
        }
        if (arr[2].get(0) == 1) {
            score += 4;
        }
        if (arr[3].get(0) == 1) {
            score += 8;
        }
        return score;
    }

    static void change(int num, int dir, int vector) {
        if (num < 0 || num > 3) {
            return;
        }

        if (vector == 0) {
            if (num == 0) {
                rotate(arr[num], dir);
                if (same[num][1] == 1) {
                    change(num + 1, 0, 1);
                } else {
                    change(num + 1, -dir, 1);
                }
            } else if (num == 3) {
                rotate(arr[num], dir);
                if (same[num][0] == 1) {
                    change(num - 1, 0, 1);
                } else {
                    change(num - 1, -dir, -1);
                }
            } else if (num == 1 || num == 2) {
                rotate(arr[num], dir);
                if (same[num][0] == 1) {
                    change(num - 1, 0, -1);
                } else {
                    change(num - 1, -dir, -1);
                }

                if (same[num][1] == 1) {
                    change(num + 1, 0, 1);
                } else {
                    change(num + 1, -dir, 1);
                }
            }
        } else if (vector == 1) {
            rotate(arr[num], dir);
            if (same[num][1] == 1) {
                change(num + 1, 0, 1);
            } else if (same[num][1] == -1) {
                change(num + 1, -dir, 1);
            }
        } else if (vector == -1) {
            rotate(arr[num], dir);
            if (same[num][0] == 1) {
                change(num - 1, 0, -1);
            } else if (same[num][0] == -1) {
                change(num - 1, -dir, -1);
            }
        }
    }

    static void rotate(LinkedList<Integer> linkedList, int dir) {
        if (dir == 0) {
            return;
        } else if (dir > 0) {
            for (int i = 0; i < dir; i++) {
                int num = linkedList.pollLast();
                linkedList.addFirst(num);
            }
        } else {
            for (int i = dir; i < 0; i++) {
                int num = linkedList.pollFirst();
                linkedList.add(num);
            }
        }
    }
}