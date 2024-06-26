import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int k;
    static LinkedList<Integer>[] arr = new LinkedList[4]; // 회전을 위해 linkedList를 썼습니다.
    static int[][] same = new int[4][2]; // 만나는 극 정보 저장
    static int ans;

    static void setInputs() throws IOException {
        ans = 0;
        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < 4; i++) {
            arr[i] = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 8; j++) {
                arr[i].add(Integer.parseInt(st.nextToken()));
            }
        }
    }

    static void doLogic() throws IOException {
        for (int t = 0; t < k; t++) {
            setSame();

            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            change(num, dir, 0);
        }
    }

    static void setSame() {
        for (int i = 0; i < 3; i++) {
            if (arr[i].get(2).equals(arr[i + 1].get(6))) { // 옆바퀴와 같은 극 = 1
                same[i][1] = 1;
                same[i + 1][0] = 1;
            } else { // 옆바퀴와 다른 극 = -1
                same[i][1] = -1;
                same[i + 1][0] = -1;
            }
        }
    }

    static void change(int num, int dir, int vector) {
        if (num < 0 || num > 3) {
            return;
        }
        /**
         * vector = -1  -> 왼쪽으로 나아가기
         * vector = 0   -> 처음 입력받아서 이동 방향이 없다
         * vector = 1   -> 오른쪽으로 나아가기
         */
        if (vector == 0) { // 처음 입력받아 이동 방향이 없을 때
            if (num == 0) { // 첫번째 톱니바퀴
                rotate(arr[num], dir);
                if (same[num][1] != 1) { // 두번째 톱니바퀴와 다른 극
                    change(num + 1, -dir, 1); // 두번째 톱니바퀴는 / 첫번째 톱니바퀴와 다른 방향으로 돈다. / 오른쪽으로 탐색
                }
            } else if (num == 3) { // 네번째 톱니바퀴
                rotate(arr[num], dir);
                if (same[num][0] != 1) { // 세번째 톱니바퀴와 다른 극
                    change(num - 1, -dir, -1); // 세번째 톱니바퀴는 / 네번째 톱니바퀴와 다른 방향으로 돈다. / 왼쪽으로 탐색
                }
            } else { // 가운데 위치한 톱니바퀴
                rotate(arr[num], dir);
                // 왼쪽에 위치한 톱니바퀴 비교
                if (same[num][0] != 1) { // 왼쪽 톱니바퀴가 다른 극
                    change(num - 1, -dir, -1); // 왼쪽 톱니바퀴는 / 다른 방향으로 돈다. / 왼쪽으로 탐색
                }

                if (same[num][1] != 1) { // 오른쪽 톱니바퀴가 다른 극
                    change(num + 1, -dir, 1); // 오른쪽 톱니바퀴는 / 다른 방향으로 돈다. / 오른쪽으로 탐색
                }
            }
        } else if (vector == 1) { // vector = 1 / 오른쪽 방향으로 나아가며 체크하는 경우
            rotate(arr[num], dir);
            if (same[num][1] == -1) { // 오른쪽 톱니바퀴가 다른 극
                change(num + 1, -dir, 1); // 오른쪽 톱니바퀴는 / 다른 방향으로 돈다. / 오른쪽으로 탐색
            }

        } else if (vector == -1) { // vector = -1 / 왼쪽 방향으로 나아가며 체크하는 경우
            rotate(arr[num], dir);
            if (same[num][0] == -1) { // 왼쪽 톱니바퀴가 다른 극
                change(num - 1, -dir, -1); // 왼쪽 톱니바퀴는 / 다른 방향으로 돈다. / 왼쪽으로 탐색
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

    static void getScore() {
        if (arr[0].get(0) == 1) {
            ans += 1;
        }
        if (arr[1].get(0) == 1) {
            ans += 2;
        }
        if (arr[2].get(0) == 1) {
            ans += 4;
        }
        if (arr[3].get(0) == 1) {
            ans += 8;
        }
        return;
    }

    public static void main(String[] args) throws Exception {

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            setInputs();
            doLogic();
            getScore();
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.printf(sb.toString());
    }
}