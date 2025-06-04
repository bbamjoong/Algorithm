import java.util.*;

class Solution {
    
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        int left = 0;
        int right = distance;
        
        while (left <= right) {
            // mid = 바위를 n개 제거한 뒤 각 지점 사이의 거리의 최솟값 중에 가장 큰 값
            // parametric search 문제.
            int mid = (left + right) / 2;
            int cnt = getCnt(rocks, distance, mid);
            
            if (cnt <= n) { // 만약 cnt < n인 상황이 있다면. mid를 유지한채로 다른 돌 아무거나 더 깨면됨. 최솟값의 최대값을 유지하는게 목표니까.
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    static int getCnt(int[] rocks, int distance, int mid){
        int before = 0;
        int cnt = 0;
        
        for (int i = 0; i < rocks.length; i++){
            if (rocks[i] - before < mid) { // mid보다 작은 경우 제거
                cnt++;
                continue;
            }
            before = rocks[i]; // 그렇지 않은 경우 제거 x. + before 돌 갱신.
        }
        
        if (distance - before < mid){ // 마지막 돌멩이와의 끝점 거리.
            cnt++;
        }
        
        return cnt;
    }
}