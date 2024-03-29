import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static Point mainPoint;
    static List<Point> points;
    static Point root;
    static Stack<Point> stack;
    static boolean[] visited;
    static int ans;

    static class Point implements Comparable<Point> {
        long x;
        long y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }


        // 각도에 따라 정렬할 것
        @Override
        public int compareTo(Point p) {
            long result = ccw(root, this, p);
            if (result > 0) { // 반시계 -> 오름차순
                return -1;
            } else if (result < 0) { // 시계방향 -> 내림차순
                return 1;
            } else if (dist(root, this) < dist(root, p)) { // 일직선에 위치할 때 다음 점이 더 멀면 -> 오름차순
                return -1;
            } else {
                return 1;
            }
        }
    }

    static void setInputs() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        mainPoint = new Point(x, y);

        points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            points.add(new Point(x, y));
        }
        visited = new boolean[n];
        ans = 0;
    }

    static void grahamScan() {
        findRoot();
        Collections.sort(points);

        stack = new Stack<>();
        stack.add(root);

        for (int i = 1; i < points.size(); i++) {
            // 시계방향이거나, 일자 방향이 나오면 pop
            while (stack.size() > 1
                    && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i))
                    <= 0) {
                stack.pop();
            }
            stack.add(points.get(i));

        }
    }

    // y가 가장 작고, x가 가장 작은 점
    static void findRoot() {
        root = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for (Point point : points) {
            if (point.y < root.y) {
                root = point;
            } else if (point.y == root.y) { // y가 같다면 x가 더 작은 점이 root
                if (point.x < root.x) {
                    root = point;
                }
            }
        }
    }

    static long ccw(Point p1, Point p2, Point p3) {
        return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
    }

    static long dist(Point p1, Point p2) {
        return (long) (Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    static boolean checkRange() {
        for (int i = 0; i < stack.size() - 1; i++) {
            if (ccw(mainPoint, stack.get(i), stack.get((i + 1) % stack.size())) <= 0) {
                return false;
            }
        }
        return true;
    }

    static void updatePoints() {
        n -= stack.size(); // 볼록 껍질을 형성한 아이들을 지워준다.
        for (Point point : stack) {
            points.remove(point); // 제거.
        }
    }

    public static void main(String[] args) throws IOException {
        setInputs();
        while (n > 2) { // 3개 -> 넓이 "O"
            grahamScan();
            if (stack.size() <= 2) {
                break;
            }
            if (!checkRange()) { // 감옥이 convex hull 내부에 있지 않으면 break 해야하죠.
                break;
            }
            ans++; // 답 추가
            updatePoints();

        }
        System.out.println(ans);
    }
}