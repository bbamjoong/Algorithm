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
    static Stack<Point> stack;
    static double ans;

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
        ans = (int) 1e9;
        points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new Point(x, y));
        }
    }

    static void grahamScan() {
        findRoot();
        Collections.sort(points);

        stack = new Stack<>();
        stack.add(root);

        for (int i = 1; i < points.size(); i++) {
            // 시계방향이거나, 일자 방향이 나오면 pop
            while (stack.size() > 1
                    && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i)) <= 0) {
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

    static int[] comb;
    static boolean[] visited;

    static void combination() {
        stack.add(stack.get(0)); // 끝에 하나 더 넣음.
        comb = new int[2];
        visited = new boolean[n];

        for (int i = 0; i < stack.size() - 1; i++) {
            comb[0] = i;
            comb[1] = (i + 1) % (stack.size() - 1);
            visited[i] = true;
            visited[(i + 1) % (stack.size() - 1)] = true;

            calculateAnswer();

            visited[i] = false;
            visited[(i + 1) % (stack.size() - 1)] = false;

        }
    }

    static void calculateAnswer() {
        double tmp = 0; // 임시 답
        Point p1 = stack.get(comb[0]);
        Point p2 = stack.get(comb[1]);
        for (int i = 0; i < stack.size() - 1; i++) {
            if (!visited[i]) {
                Point p3 = stack.get(i);
                double linePointDist = getLinePointDist(p3, p1, p2);
                tmp = Math.max(tmp, linePointDist);
            }
        }
        ans = Math.min(ans, tmp);
    }

    static double getLinePointDist(Point p, Point a, Point b) {
        long area = Math.abs((a.x - p.x) * (b.y - p.y) - (a.y - p.y) * (b.x - p.x));
        double lengthLine = Math.sqrt((Math.pow((a.x - b.x), 2) + Math.pow(a.y - b.y, 2)));
        return area / lengthLine;
    }

    public static void main(String[] args) throws IOException {
        int idx = 1;
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }
            setInputs();
            grahamScan();
            combination();

            ans = Math.ceil(ans * 100) / 100.0;
            sb.append("Case ").append(idx).append(": ")
                    .append(String.format("%.2f", ans)).append("\n");
            idx++;
        }
        System.out.println(sb);
    }
}