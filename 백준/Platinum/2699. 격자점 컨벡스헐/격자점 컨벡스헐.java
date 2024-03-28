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
    static List<Point> points;
    static Point root;
    static int ans;
    static Stack<Point> stack;

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
        ans = 0;
        stack = new Stack<>();

        n = Integer.parseInt(br.readLine()); // 점의 개수

        int a = n / 5;
        int b = n % 5;
        if (b > 0) {
            a++;
        }

        points = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            st = new StringTokenizer(br.readLine());
            int[] nums = new int[2];
            while (st.hasMoreTokens()) {
                for (int j = 0; j < 2; j++) {
                    nums[j] = Integer.parseInt(st.nextToken());
                }
                points.add(new Point(nums[0], nums[1]));
            }
        }
    }

    static void grahamScan() {
        findRoot();
        Collections.sort(points);

        stack.add(root);

        for (int i = 1; i < points.size(); i++) {
            // 시계방향이거나, 일자 방향이 나오면 pop
            while (stack.size() > 1
                    && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i)) <= 0) {
                stack.pop();
            }
            stack.add(points.get(i));
        }
        ans = stack.size();
    }

    // y가 가장 작고, x가 가장 작은 점
    static void findRoot() {
        root = new Point(21, -21);

        for (Point point : points) {
            if (point.y > root.y) {
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

    static void printAnswer() {
        sb.append(ans).append("\n");
        sb.append(stack.get(0).x).append(" ").append(stack.get(0).y).append("\n");
        for (int i = stack.size() - 1; i > 0; i--) {
            Point point = stack.get(i);
            sb.append(point.x).append(" ").append(point.y).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            setInputs();
            grahamScan();
            printAnswer();
        }
        System.out.println(sb);
    }
}