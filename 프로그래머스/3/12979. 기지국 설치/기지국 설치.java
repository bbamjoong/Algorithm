public class Solution {
    static int B;
    static int added;
    static int last;
    
    public int solution(int N, int[] stations, int W) {
        B = 2 * W + 1;
        added = 0;
        last = 0;

        for (int s : stations) {
            int start = Math.max(1, s - W);
            
            if (last + 1 < start) cal(start - last - 1);
            last = Math.min(N, s + W);
        }
        // 마지막 기지국 뒤에 남은 구간 처리
        if (last < N) cal(N - last);

        return added;
    }
    
    static void cal(int gap) {
        added += gap/B;
        if (gap%B != 0) added++;
    }
}
