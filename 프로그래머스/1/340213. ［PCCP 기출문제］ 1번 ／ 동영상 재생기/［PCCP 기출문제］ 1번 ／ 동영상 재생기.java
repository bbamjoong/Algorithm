import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int video_l = toInt(video_len);
        int now = toInt(pos);
        int op_s = toInt(op_start);
        int op_e = toInt(op_end);
        
        now = op(now, op_s, op_e);
        
        for (String com : commands) {
            if (com.equals("prev")) {
                if (now < 10) {
                    now = 0;
                } else {
                    now -= 10;
                }
                now = op(now, op_s, op_e);
            } else if (com.equals("next")) {
                if (video_l - now < 10) {
                    now = video_l;
                } else {
                    now += 10;
                }
                now = op(now, op_s, op_e);
            }
        }
        
        return getAnswer(now);
    }
    
    static int toInt(String info) {
        String[] parts = info.split(":");
        int min = Integer.parseInt(parts[0]);
        int sec = Integer.parseInt(parts[1]);
        return min * 60 + sec;
    }
    
    static int op(int info, int op_start, int op_end) {
        if (op_start <= info && info <= op_end) {
            return op_end;
        }
        return info;
    }
    
    static String getAnswer(int now) {
        int min = now / 60;
        int sec = now % 60;
        return String.format("%02d:%02d", min, sec);
    }
}