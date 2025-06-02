import java.util.*;

class Solution {
    public long[] solution(long k, long[] room_number) {
        
        int len = room_number.length;
        HashMap<Long, Long> map = new HashMap<>();
        long[] ans = new long[len];
    
        for (int i = 0; i < len; i++) {
            long key = room_number[i];

            ArrayList<Long> tmp = new ArrayList<>();
            
            // 최대한 이동가능한 만큼 key 찾아보기
            while (true) {
                if (map.containsKey(key)) {
                    tmp.add(key);
                    key = map.get(key);
                } else {
                    break;
                }
            }

            long value = key + 1; // 값 갱신을 위한 value
            // map 값 업데이트
            for (long num : tmp) map.put(num, value);

            ans[i] = key;
            map.put(key, value);
        }
        return ans;
    }
}