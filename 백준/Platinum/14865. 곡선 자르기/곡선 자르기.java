import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static class Bong implements Comparable<Bong> {
        int start;
        boolean isStart;

        public Bong(int start, boolean isStart) {
            this.start = start;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Bong b) { // 스타트 x점을 기준으로 정렬
            return this.start - b.start;
        }
    }

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        int idx = 0;
        Pos[] arr = new Pos[n];

        int standard = (int) 1e9;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[i] = (new Pos(x, y));

            if (x < standard) {
                standard = x;
                idx = i;
            }
        }

        ArrayList<Bong> bongs = new ArrayList<>();
        // 시작점 정의하고 스타투
        int cnt = 1;
        int nowX = arr[idx].x;
        int nowY = arr[idx].y;

        while (cnt <= n) {
            idx = (idx + 1) % n;
            Pos nowPos = arr[idx];

            // 이전 y가 음수 현재 y가 양수일 때 -> 시작하는 봉우리
            if (nowY < 0 && nowPos.y > 0) {

                // 이전 관통 선분이 봉우리 끝이면 지금 관통 선분은 무조건 봉우리 시작부분이다
                nowX = nowPos.x;
                nowY = nowPos.y;
            }

            // 봉우리가 끝나는 지점일 때 업데이트 해준다
            else if (nowY > 0 && nowPos.y < 0) {
                int minX = Math.min(nowX, nowPos.x);
                int maxX = Math.max(nowX, nowPos.x);

                nowX = nowPos.x;
                nowY = nowPos.y;

                Bong left = new Bong(minX, true);
                Bong right = new Bong(maxX, false);
                bongs.add(left);
                bongs.add(right);
            }
            cnt++;
        }

        Collections.sort(bongs);

        Stack<Integer> stack = new Stack<>();
        int num = 0;
        int ans1 = 0;
        int ans2 = 0;
        for (Bong bong : bongs) {
            boolean check = bong.isStart; // 봉우리

            if (check) {
                stack.add(num);
            } else {
                int popNum = stack.pop();
                if (stack.isEmpty()) { // 제일 큰놈
                    ans1++;
                }

                if (popNum == num) {
                    ans2++;
                    num++;
                }
            }
        }

        System.out.println(ans1 + " " + ans2);
    }
}