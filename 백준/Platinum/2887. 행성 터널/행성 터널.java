import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int from;
        int to;
        int value;

        public Node(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return false;
        }

        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
        return true;
    }

    static int n;
    static int[] parents;

    static List<int[]> xList, yList, zList;
    static Queue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        xList = new ArrayList<>();
        yList = new ArrayList<>();
        zList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            // 각 리스트에 (행성의 인덱스, 해당 좌표 값) 저장
            xList.add(new int[]{i, x});
            yList.add(new int[]{i, y});
            zList.add(new int[]{i, z});
        }

        // x, y, z 각각 오름차순 정렬 (좌표 값 기준)
        xList.sort((a, b) -> a[1] - b[1]);
        yList.sort((a, b) -> a[1] - b[1]);
        zList.sort((a, b) -> a[1] - b[1]);

        // 모든 후보 간선을 하나의 우선순위 큐에 넣기
        pq = new PriorityQueue<>();

        // 각 좌표별로 인접한 행성 간 간선 생성 (비용은 좌표 차이의 절대값)
        for (int i = 1; i < n; i++) {
            int[] prev, curr;

            // x 좌표
            prev = xList.get(i - 1);
            curr = xList.get(i);
            int cost = Math.abs(curr[1] - prev[1]);
            pq.add(new Node(prev[0], curr[0], cost));

            // y 좌표
            prev = yList.get(i - 1);
            curr = yList.get(i);
            cost = Math.abs(curr[1] - prev[1]);
            pq.add(new Node(prev[0], curr[0], cost));

            // z 좌표
            prev = zList.get(i - 1);
            curr = zList.get(i);
            cost = Math.abs(curr[1] - prev[1]);
            pq.add(new Node(prev[0], curr[0], cost));
        }

        // Union-Find 초기화
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        long totalCost = 0;
        int edgesUsed = 0;

        // 크루스칼 알고리즘: PQ에서 가장 낮은 비용 간선부터 선택
        while (!pq.isEmpty() && edgesUsed < n - 1) {
            Node edge = pq.poll();
            if (union(edge.from, edge.to)) {
                totalCost += edge.value;
                edgesUsed++;
            }
        }

        System.out.println(totalCost);
    }
}
