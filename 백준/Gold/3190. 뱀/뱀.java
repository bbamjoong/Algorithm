import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static Apple[] apples;
    static RotateInfo[] rotateInfos;
    static boolean[][] visited;
    // 오른쪽, 아래쪽, 왼쪽, 위쪽
    static int x = 0;
    static int y = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        apples = new Apple[k];
        StringTokenizer st;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            apples[i] = new Apple(a, b);
        }

        int l = Integer.parseInt(br.readLine());
        rotateInfos = new RotateInfo[l];
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            String b = st.nextToken();
            rotateInfos[i] = new RotateInfo(a, b);
        }

        visited = new boolean[n][n];
        visited[0][0] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        Queue<int[]> tail = new LinkedList<>();
        tail.add(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] arr = q.poll();
            int cnt = arr[0];
            int direction = arr[1];

            int nx = x + dx[direction];
            int ny = y + dy[direction];
            
            if (direction == 3 && nx == -1) {
                ans = cnt + 1;
                break;
            } else if (direction == 1 && nx == n) {
                ans = cnt + 1;
                break;
            } else if (direction == 2 && ny == -1) {
                ans = cnt + 1;
                break;
            } else if (direction == 0 && ny == n) {
                ans = cnt + 1;
                break;
            }

            if (visited[nx][ny]) {
                ans = cnt + 1;
                break;
            }
            boolean isApple = false;
            for (Apple apple : apples) {
                if (apple.x == nx && apple.y == ny && !apple.eaten) {
                    apple.eaten = true;
                    visited[nx][ny] = true;
                    isApple = true;
                    tail.add(new int[]{nx, ny});
                    break;
                }
            }

            // 사과 없을 때
            if (!isApple) {
                visited[nx][ny] = true;
                int[] tailArr = tail.poll();
                int tailX = tailArr[0];
                int tailY = tailArr[1];
                // visited 꼬리 = false;
                visited[tailX][tailY] = false;
                tail.add(new int[]{nx, ny});
            }

            cnt++;

            boolean isRotated = false;
            // 방향 전환
            for (RotateInfo rotateInfo : rotateInfos) {
                if (rotateInfo.time == cnt) {
                    int nowDirection = rotate(direction, rotateInfo.direction);
                    q.add(new int[]{cnt, nowDirection});
                    x = nx;
                    y = ny;
                    isRotated = true;
                    break;
                }
            }

            if (!isRotated) {
                q.add(new int[]{cnt, direction});
                x = nx;
                y = ny;
            }
        }

        System.out.println(ans);
    }

    static class Apple {
        int x;
        int y;
        boolean eaten = false;

        public Apple(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class RotateInfo {
        int time;
        String direction;

        public RotateInfo(int time, String direction) {
            this.time = time;
            this.direction = direction;
        }
    }

    static int rotate(int num, String direction) {
        if (direction.equals("D")) {
            return (num + 1) % 4;
        } else {
            return (num + 3) % 4;
        }
    }
}