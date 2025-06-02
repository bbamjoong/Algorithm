import java.util.Arrays;

class Solution {
    public int[] solution(int e, int[] starts) {
        // x의 약수 개수 저장
        int[] cnt = new int[e + 1];
        
        // 약수 계산. O(e log e)
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                cnt[j]++;
            }
        }
        
        // [i..e] 구간에서 약수 개수가 가장 많고, 동일하면 가장 작은 수
        int[] best = new int[e + 1];
        int maxCnt = 0;
        int maxArg = e;
        
        // e에서 1까지 내려오며 best[]를 채운다 (O(e))
        for (int i = e; i >= 1; i--) {
            if (cnt[i] > maxCnt) {
                maxCnt = cnt[i];
                maxArg = i;
            } else if (cnt[i] == maxCnt && i < maxArg) {
                maxArg = i; // 동일한 등장 횟수(count)라면 더 작은 수(i)를 선택
            }
            best[i] = maxArg;
        }
        
        // starts 배열을 순회 (O(starts.length))
        int[] answer = new int[starts.length];
        for (int k = 0; k < starts.length; k++) {
            answer[k] = best[starts[k]];
        }
        return answer;
    }
}