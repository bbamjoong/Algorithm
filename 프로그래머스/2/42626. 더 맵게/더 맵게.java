import java.util.*;

class Solution {
    static int count = 0;
    
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int s : scoville) {
            pq.add(s);
        }
        
        while (pq.size() >= 2) {
            if (pq.peek() >= K) break;
            
            int first = pq.poll();
            int second = pq.poll();
            int newFood = first + (second * 2);
            pq.add(newFood);
            count++;
        }
        
        if (pq.peek() >= K) return count;
        else return -1;
    }
}
