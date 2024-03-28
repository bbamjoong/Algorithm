import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static List<Point> points;
    static Point root;

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
            } else if (dist(root, this) > dist(root, p)) {
                return 1;
            }
            return 0;
        }
    }

    static long dist(Point p1, Point p2) {
        return (long) (Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    static void setInputs() throws IOException {
        n = Integer.parseInt(br.readLine());

        points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            String s = st.nextToken();
            if (s.equals("Y")) {
                points.add(new Point(x, y));
            }
        }
    }

    static void findPolygon() {
        findRoot();
        points.remove(root);
        Collections.sort(points);
    }

    static void findRoot() {
        root = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for (Point point : points) {
            if (point.x < root.x) {
                root = point;
            } else if (point.x == root.x) {
                if (point.y < root.y) {
                    root = point;
                }
            }
        }
    }

    static long ccw(Point p1, Point p2, Point p3) {
        return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
    }

    static int findLine() {
        int idx = points.size() - 1;
        Point firstPoint = points.get(points.size() - 1); // 가장 마지막 점
        // root - 가장 마지막 점 - 탐색할 점   -> 이  어디까지 일직선인가를 판단. 얘네들은 순서를 거꾸로 바꿔줘야함.
        for (int i = points.size() - 2; i > 0; i--) {
            Point secondPoint = points.get(i);
            if (ccw(root, firstPoint, secondPoint) == 0) {
                idx--;
            }
        }
        return idx;
    }


    static void printAnswer() {
        sb.append(points.size() + 1).append("\n");
        sb.append(root.x).append(" ").append(root.y).append("\n");
        int idx = findLine();
        for (int i = 0; i < idx; i++) {
            sb.append(points.get(i).x).append(" ").append(points.get(i).y).append("\n");
        }
        for (int i = points.size() - 1; i >= idx; i--) {
            sb.append(points.get(i).x).append(" ").append(points.get(i).y).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        setInputs();
        findPolygon();
        printAnswer();
        System.out.printf(sb.toString());
    }
}