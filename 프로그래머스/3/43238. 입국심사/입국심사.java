import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long left = 0;
        long right = 1_000_000_000 * (long) n;
        
        while(left <= right) {
            long mid = (left + right) / 2;
            long complete = 0;
            
            for (int i = 0; i < times.length; i++) {
                complete += mid / times[i];
            }
                
            if (complete < n) left = mid + 1;
            else {
                right = mid - 1;
                answer = mid;
            }
        }  
        return answer;
    }
}