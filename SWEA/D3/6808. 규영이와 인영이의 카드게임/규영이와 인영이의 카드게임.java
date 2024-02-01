import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static int[] rbdud;
    static int[] dlsdud;
    static ArrayList<Integer> dlsdudList = new ArrayList<>();
    static int rbdudWin;
    static int rbdudLose;

    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case < T + 1; test_case++) {
            rbdud = new int[9];
            dlsdud = new int[9];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 9; i++) {
                rbdud[i] = Integer.parseInt(st.nextToken());
            }

            int index = 0;
            for (int i = 1; i < 19; i++) {
                boolean flag = false;
                for (int num : rbdud) {
                    if (i == num) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    dlsdud[index++] = i;
                }
            }
            rbdudWin = 0;
            rbdudLose = 0;
            permutation(0, (1 << 9) - 1);

            sb.append("#" + test_case + " ").append(rbdudWin).append(" ").append(rbdudLose).append("\n");
        }
        System.out.println(sb);
    }

    static void permutation(int depth, int visited) {
        if (depth == 9) {
            calculate();
            return;
        }

        for (int i = 0; i < 9; i++) {
            if ((visited & (1 << i)) > 0) {
                visited &= ~(1 << i);
                dlsdudList.add(dlsdud[i]);
                permutation(depth + 1, visited);
                dlsdudList.remove(dlsdudList.size() - 1);
                visited |= (1 << i);
            }
        }
    }

    static void calculate() {
        int rbdudScore = 0;
        int dlsdudScore = 0;
        for (int i = 0; i < 9; i++) {
            if (rbdud[i] > dlsdudList.get(i)) {
                rbdudScore += rbdud[i] + dlsdudList.get(i);
            } else {
                dlsdudScore += rbdud[i] + dlsdudList.get(i);
            }
        }

        if (rbdudScore > dlsdudScore) {
            rbdudWin++;
            return;
        }
        rbdudLose++;
    }
}