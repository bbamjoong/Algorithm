import java.util.*;

class Solution {
    
    static class Info implements Comparable<Info> {
        int a;
        int b;
        int idx;
        
        public Info(int a, int b, int idx) {
            this.a = a;
            this.b = b;
            this.idx = idx;
        }
        
        public int sum() {
            return a + b;
        }
        
        @Override
        public int compareTo(Info i) {
            if (this.a == i.a) return i.b - this.b;
            return i.a - this.a;
        }
        
        public void print() {
            System.out.println(a + " : " + b + " : " + idx);
        }
    }

    public int solution(int[][] scores) {
        int n = scores.length;
        int w1 = scores[0][0], w2 = scores[0][1];
        int w = w1 + w2;

        PriorityQueue<Info> pq = new PriorityQueue<>(); // 전부 내림차순
        for (int i = 0; i < n; i++) 
            pq.add(new Info(scores[i][0], scores[i][1], i));

        int prevA = pq.peek().a;            // 현재 그룹의 a 값
        int maxCo = -1;                     // 전체 b의 최대 값
        int groupMax = Integer.MIN_VALUE;   // 현재 a 그룹에서의 b 최대
        int cnt = 0;

        while (!pq.isEmpty()) {
            Info i = pq.poll();
            
            // ### 디버깅 ###
            // i.print();
            // ### 디버깅 ###
            
            // 그룹 경계: a 값이 바뀌면 이전 그룹의 최대 b를 maxCo에 반영
            if (i.a != prevA) {
                maxCo = Math.max(maxCo, groupMax);
                prevA = i.a;
                groupMax = Integer.MIN_VALUE;
            }

            // 둘 다 낮으면 인센티브 못받음.
            if (i.b < maxCo) {
                if (i.idx == 0) return -1;   // 완호 제외 시 -1 return
                groupMax = Math.max(groupMax, i.b);
                continue;
            }

            if (i.sum() > w) {
                cnt++;
            }
            groupMax = Math.max(groupMax, i.b);
        }

        return cnt + 1;
    }
}