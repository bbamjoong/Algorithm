import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    static int INF = 2_000_000_000;
    static int[][] dist;
    static List<Node>[] list, rList;
    static Map<Integer, Integer> trapList;

    // 인접 리스트를 계속 바꾸는건 말이 안됨. 노드의 상태를 보고 원래 방향대로 갈 수 있는지를 판단.
    // 간선을 기준으로 활성화된 trap이 홀수개면 역방향. 나머지는 정방향.
    static class Node implements Comparable<Node> {
        int to;
        int weight;
        int status;

        public Node(int to, int weight, int status) {
            this.to = to;
            this.weight = weight;
            this.status = status;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        list = new ArrayList[n + 1];
        rList = new ArrayList[n + 1];

        for (int i = 1; i < n + 1; i++) { // 인접리스트
            list[i] = new ArrayList<>();
            rList[i] = new ArrayList<>();
        }

        trapList = new HashMap<>();
        for (int i = 0; i < traps.length; i++) {
            trapList.put(traps[i], 1 << (i + 1));
        }

        for (int i = 0; i < roads.length; i++) {
            int from = roads[i][0];
            int to = roads[i][1];
            int weight = roads[i][2];

            list[from].add(new Node(to, weight, 0));
            rList[to].add(new Node(from, weight, 0));
        }

        dist = new int[n + 1][1 << traps.length + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dist[i], INF);
        }

        dijkstra(start, end);

        int answer = INF;
        for (int res : dist[end]) {
            answer = Math.min(answer, res);
        }

        return answer;
    }

    static void dijkstra(int start, int end) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start][0] = 0;
        pq.add(new Node(start, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int to = cur.to;
            int w = cur.weight;
            int status = cur.status;

            if (to == end) {
                return;
            }
            if (w > dist[to][status]) {
                continue;
            }

            int flip1 = 0;
            if (trapList.containsKey(to)) { // 현재 노드 to가 활성화된 트랩인지 플래그 계산
                if ((status & trapList.get(to)) != 0) { // to의 비트가 켜져있는 상태
                    flip1 = 1;
                }
            }

            
            for (Node next : list[to]) { // 정방향 간선 탐색
                int nextTo = next.to;
                int bit = trapList.getOrDefault(nextTo, 0); // 다음 노드의 비트는 어떻게 생겼는지
                int flip2 = ((status & bit) != 0 ? 1 : 0); // 지금 다음 노드가 켜져있는지?
                
                int canForward = flip1 ^ flip2; // 0이면 정방향 1이면 역방향
                int nextStatus = status ^ bit; // 다음 방문할 곳의 bit를 XOR (켜져있으면 끄고, 꺼져있으면 키고)

                if (canForward == 0) {
                    int predictedWeight = w + next.weight;
                    if (predictedWeight < dist[nextTo][nextStatus]) {
                        dist[nextTo][nextStatus] = predictedWeight;
                        pq.add(new Node(nextTo, predictedWeight, nextStatus));
                    }
                }
            }

            for (Node next : rList[to]) { // 역방향 간선 탐색
                int nextTo = next.to;
                int bit = trapList.getOrDefault(nextTo, 0);
                int flip2 = ((status & bit) != 0 ? 1 : 0);
                
                int canForward = flip1 ^ flip2;
                int nextStatus = status ^ bit;

                if (canForward == 1) {
                    int predictedWeight = w + next.weight;
                    if (predictedWeight < dist[nextTo][nextStatus]) {
                        dist[nextTo][nextStatus] = predictedWeight;
                        pq.add(new Node(nextTo, predictedWeight, nextStatus));
                    }
                }
            }
        }
    }
}