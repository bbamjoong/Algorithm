import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int studentCnt = Integer.parseInt(st.nextToken());
        int compareCnt =Integer.parseInt(st.nextToken());

        arr = new boolean[studentCnt+1][studentCnt+1];

        for (int i =0; i<compareCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b= Integer.parseInt(st.nextToken());

            arr[a][b] = true;
        }

        int result = 0;
        for (int i =1; i<arr.length; i++) {
            if (canKnow(i))
                result ++;
        }

        System.out.println(result);
    }
    static final int UP = 1;
    static final int DOWN = -1;
    static final int NONE = 0;

    private static boolean canKnow(int num) {
        boolean[] visit = new boolean[arr.length];
        visit[num] = true;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{num, NONE});

        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            cnt ++;

            for (int i =1; i< arr.length; i++) {
                if (visit[i])
                    continue;

                if (arr[poll[0]][i] && poll[1] != DOWN) {
                    visit[i] = true;
                    queue.add(new int[]{i, UP});
                    continue;
                }

                if (arr[i][poll[0]] && poll[1] != UP) {
                    visit[i] = true;
                    queue.add(new int[]{i, DOWN});
                }
            }
        }

        if (cnt == arr.length-1) {
            return true;
        }

        return false;
    }
}