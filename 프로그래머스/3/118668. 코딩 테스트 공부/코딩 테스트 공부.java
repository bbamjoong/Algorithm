import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    static int targetA = 0;
    static int targetC = 0;
    static int maxA; // 문제에서 가능한 최대 알고력
    static int maxC; // 문제에서 가능한 최대 코딩력
    static int[][] dist;

    static class State implements Comparable<State> {
        int alp;
        int cop;
        int cost;
        State(int alp, int cop, int cost) {
            this.alp = alp;
            this.cop = cop;
            this.cost = cost;
        }

        @Override
        public int compareTo(State s) {
            return this.cost - s.cost;
        }
    }

    public int solution(int alp, int cop, int[][] problems) {
        for (int[] p : problems) {
            targetA = Math.max(targetA, p[0]);
            targetC = Math.max(targetC, p[1]);
        }
        maxA = Math.max(targetA, alp);
        maxC = Math.max(targetC, cop);
        
        dist = new int[maxA + 1][maxC + 1];
        for (int i = 0; i <= maxA; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        
        return dijkstra(alp, cop, problems);
    }

    // 다익스트라 알고리즘을 분리한 메소드
    private int dijkstra(int startA, int startC, int[][] problems) {
        
        PriorityQueue<State> pq = new PriorityQueue<>();
        dist[startA][startC] = 0;
        pq.offer(new State(startA, startC, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            
            if (cur.cost > dist[cur.alp][cur.cop]) continue;
            if (cur.alp >= targetA && cur.cop >= targetC) return cur.cost;

            // 공부하기
            if (cur.alp + 1 <= maxA && dist[cur.alp + 1][cur.cop] > cur.cost + 1) {
                dist[cur.alp + 1][cur.cop] = cur.cost + 1;
                pq.offer(new State(cur.alp + 1, cur.cop, cur.cost + 1));
            }
            if (cur.cop + 1 <= maxC && dist[cur.alp][cur.cop + 1] > cur.cost + 1) {
                dist[cur.alp][cur.cop + 1] = cur.cost + 1;
                pq.offer(new State(cur.alp, cur.cop + 1, cur.cost + 1));
            }

            // 문제 풀기
            for (int[] p : problems) {
                int reqA = p[0];
                int reqC = p[1];
                int rewardA = p[2];
                int rewardC = p[3];
                int cost = p[4];
                if (cur.alp >= reqA && cur.cop >= reqC) {
                    int nextA = Math.min(maxA, cur.alp + rewardA);
                    int nextC = Math.min(maxC, cur.cop + rewardC);
                    int nCost = cur.cost + cost;
                    if (dist[nextA][nextC] > nCost) {
                        dist[nextA][nextC] = nCost;
                        pq.offer(new State(nextA, nextC, nCost));
                    }
                }
            }
        }
        return dist[targetA][targetC];
    }
}
