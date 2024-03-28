import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static List<Point> points;

    static class Point implements Comparable<Point> {
        long x;
        long y;
        int idx;

        public Point(long x, long y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }

        @Override
        public int compareTo(Point p) {
            if (this.x != p.x) {
                return Long.compare(this.x, p.x);
            } else {
                return Long.compare(this.y, p.y);
            }
        }
    }

    static int ccw(Point p1, Point p2, Point p3) {
        long res = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);

        if (res > 0) { // 반시계
            return 1;
        }
        if (res < 0) { // 시계
            return -1;
        }
        return 0; // 일직선
    }

    static long dist(Point p1, Point p2) {
        return (long) (Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            points = new ArrayList<>();

            int idx = 0;
            while (st.hasMoreTokens()) {
                long x = Integer.parseInt(st.nextToken());
                long y = Integer.parseInt(st.nextToken());
                points.add(new Point(x, y, idx++));
            }

            Point root = Collections.min(points);
            Collections.swap(points, 0, points.indexOf(root));

            points.subList(1, points.size()).sort((a, b) -> {
                int result = ccw(points.get(0), a, b);
                if (result != 0) {
                    if (result > 0) {
                        return 1;
                    }
                    return -1;
                }
                return Long.compare(dist(points.get(0), a), dist(points.get(0), b));
            });

            int right = points.size() - 1;
            for (int i = points.size() - 1; i >= 1; i--) {
                if (ccw(root, points.get(right), points.get(right - 1)) == 0) {
                    right--;
                } else {
                    break;
                }
            }

            Collections.reverse(points.subList(right, points.size()));
            for (Point i : points) {
                System.out.print(i.idx + " ");
            }
            System.out.println();
        }

    }
}
