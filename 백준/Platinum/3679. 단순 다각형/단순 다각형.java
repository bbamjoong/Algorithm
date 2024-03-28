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
    static int l;
    static List<Point> points;
    static Point root;
    static Stack<Point> stack;

    static class Point implements Comparable<Point> {
        long x;
        long y;
        int idx;

        Point(long x, long y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }


        // 각도에 따라 정렬할 것
        @Override
        public int compareTo(Point p) {
            long result = ccw(root, this, p);
            if (result > 0) { // 반시계 -> 오름차순
                return -1;
            } else if (result < 0) {
                return 1;
            } else { // 거리가 같으면 가까운 것부터 오름차순 정렬
                return (int) (dist(root, this) - dist(root, p));
            }
        }
    }

    static long dist(Point p1, Point p2) {
        return (long) (Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    static void setInputs() throws IOException {
        stack = new Stack<>();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        points = new ArrayList<>();
        int idx = 0;
        while (st.hasMoreTokens()) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new Point(x, y, idx++));
        }
    }

    static void findPolygon() {
        findRoot();
        points.remove(root);
        Collections.sort(points);
    }

    // y가 가장 작고, x가 가장 작은 점
    static void findRoot() {
        root = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE, 0);

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
        sb.append(root.idx).append(" ");

        int idx = findLine();
        for (int i = 0; i < idx; i++) {
            sb.append(points.get(i).idx).append(" ");
        }
        for (int i = points.size() - 1; i >= idx; i--) {
            sb.append(points.get(i).idx).append(" ");
        }
        sb.append("\n");
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            setInputs();
            findPolygon();
            printAnswer();
        }
        System.out.printf(sb.toString());
    }
}