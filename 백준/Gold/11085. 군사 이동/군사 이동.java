import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int p; // 지점 개수
    static int w; // 길 개수
    static int c; // 백준 수도
    static int v; // 큐브 수도
    static int[] parent;

    static int ans;

    static class Road implements Comparable<Road> {
        int a;
        int b;
        int width;

        public Road(int a, int b, int width) {
            this.a = a;
            this.b = b;
            this.width = width;
        }

        @Override
        public int compareTo(Road r) { // 너비를 기준으로 오름차순
            return -(this.width - r.width);
        }
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parent[b] = a;
            return;
        }
        parent[a] = b;
    }

    static void getAnswer(PriorityQueue<Road> pq) {
        while (!pq.isEmpty()) {
            Road road = pq.poll();
            union(road.a, road.b);

            if (find(c) == find(v)) {
                ans = road.width;
                break;
            }
        }
    }


    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        parent = new int[p + 1];
        for (int i = 0; i < p + 1; i++) { // 부모 초기화
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        PriorityQueue<Road> pq = new PriorityQueue<>(); // 도로의 정보를 너비를 기준으로 오름차순 정렬
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            pq.add(new Road(a, b, width));
        }
        getAnswer(pq);

        System.out.println(ans);
    }
}