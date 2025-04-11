class Solution {
    static boolean isImpossible(int[] diffs, int[] times, long level, long limit){
        // 첫 퍼즐은 무조건 풀 수 있으니까.
        long t = (long)times[0];
        
        for(int i = 1; i < times.length; i++){
            if(diffs[i] > level){
                t += ((long)diffs[i] - level) * ((long)times[i-1] + (long)times[i]);
            }
            
            t += (long)times[i];
        }
        
        return limit < t;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        long left = 1;
        long right = limit;
        
        while (left <= right){
            long mid = (left + right) / 2;
            
            if (isImpossible(diffs, times, mid, limit)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return (int) left;
    }
}