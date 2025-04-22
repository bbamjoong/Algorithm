import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        // 1. 남은 전체 일이 n 이하라면, 모두 0으로 만들 수 있으니 바로 0 리턴
        long totalWork = 0;
        for (int w : works) {
            totalWork += w;
        }
        
        if (totalWork <= n) {
            return 0L;
        }

        // 2. pq
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int w : works) {
            pq.add(w);
        }

        // 3. 업무량 큰 순서로 1씩 줄이기
        while (n > 0) {
            int value = pq.poll();
            if (value == 0) { // 이미 0이면 더 이상 줄일 게 없음
                break;
            }
            pq.add(value - 1);
            n--;
        }

        // 4. 계산
        long answer = 0;
        for (int w : pq) {
            answer += (long) w * w;
        }
        return answer;
    }
}
