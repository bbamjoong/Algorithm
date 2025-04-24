class Solution {
    public String solution(String playTime, String advTime, String[] logs) {
        int playLen = timeToInt(playTime);
        int advLen  = timeToInt(advTime);
        
        long[] viewers = new long[playLen + 1]; // 시청자 변화 추이
        for (String log : logs) {
            String[] p = log.split("-");
            int s = timeToInt(p[0]);
            int e = timeToInt(p[1]);
            viewers[s] += 1;
            if (e <= playLen) viewers[e] -= 1;
        }
        
        for (int i = 1; i <= playLen; i++) { // 시청자 수 누적합
            viewers[i] += viewers[i - 1];
        }
        
        
        long sum = 0;
        for (int i = 0; i < advLen; i++) { // 0초 ~ 광고시간만큼의 합
            sum += viewers[i];
        }
        
        long maxSum = sum;
        int  maxIdx = 0;
        for (int i = advLen; i <= playLen; i++) { // 광고시간초 ~ 끝나는 시간까지. 종료시간을 슬라이딩 시킨다.
            sum += viewers[i] - viewers[i - advLen];
            if (sum > maxSum) {
                maxSum = sum;
                maxIdx = i - advLen + 1;
            }
        }
        
        return timeToString(maxIdx);
    }
    
    private static int timeToInt(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 3600
             + Integer.parseInt(t[1]) *   60
             + Integer.parseInt(t[2]);
    }
    
    private static String timeToString(int time) {
        int h = time / 3600;   time %= 3600;
        int m = time /   60;   time %=   60;
        int s = time;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
