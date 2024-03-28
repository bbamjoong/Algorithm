import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {
    static class Point implements Comparable<Point> {
        long x, y, idx;

        public Point(long x, long y, long idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }

        @Override
        public int compareTo(Point other) {
            if (this.x != other.x) {
                return Long.compare(this.x, other.x);
            } else {
                return Long.compare(this.y, other.y);
            }
        }
    }

    static int ccw(Point a, Point b, Point c) {
        long res = a.x * b.y + b.x * c.y + c.x * a.y;
        res -= b.x * a.y + c.x * b.y + a.x * c.y;
        if (res > 0) {
            return 1;
        }
        if (res < 0) {
            return -1;
        }
        return 0;
    }

    static long dist(Point a, Point b) {
        long dx = a.x - b.x;
        long dy = a.y - b.y;
        return dx * dx + dy * dy;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            List<Point> v = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                long x = scanner.nextLong();
                long y = scanner.nextLong();
                v.add(new Point(x, y, i));
            }

            Point minPoint = Collections.min(v);
            Collections.swap(v, 0, v.indexOf(minPoint));

            v.subList(1, v.size()).sort((a, b) -> {
                int cw = ccw(v.get(0), a, b);
                if (cw != 0) {
                    return cw > 0 ? 1 : -1;
                }
                return Long.compare(dist(v.get(0), a), dist(v.get(0), b));
            });

            int pt = v.size() - 1;
            for (int i = v.size() - 1; i >= 1; i--) {
                if (ccw(v.get(0), v.get(pt), v.get(pt - 1)) == 0) {
                    pt--;
                } else {
                    break;
                }
            }

            Collections.reverse(v.subList(pt, v.size()));
            for (Point i : v) {
                System.out.print(i.idx + " ");
            }
            System.out.println();
        }
    }
}
