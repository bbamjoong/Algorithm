import java.util.*;

class Solution {

    public int solution(int[] bandage, int health, int[][] attacks) {
        int cnt = bandage[0];
        int now = health;
        int lastTime = 0; // 마지막으로 공격당한 시간

        int v1, v2;
        for (int[] arr: attacks) {
            
            if (now <= 0) {
                return -1;
            }

            v1 = arr[0] - lastTime - 1; // 붕대 몇번? 공격받았을 때는 회복X
            v2 = v1 / cnt; // 추가 체력 몇번 회수?

            // 맞기 직전까지의 체력 정산
            lastTime = arr[0];
            
            now += (v1 * bandage[1]);
            now += (v2 * bandage[2]);
            
            if (now >= health) {
                now = health;
            }

            now -= arr[1];
        }        
        
        if (now <= 0) {
            return -1;
        }
        return now;
    }
}