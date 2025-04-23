public class Solution {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = 200_000_000; // 최대 2억명이 건널 수 있음.

        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canCross(stones, k, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private boolean canCross(int[] stones, int k, int mid) {
        int skip = 0;
        for (int stone : stones) {
            if (stone < mid) {
                skip++; // 마지막 사람이 못건너는 돌이 되겠지
                
                if (skip >= k) { // k개 이상만큼 skip이면 k+1 건너야하니 안됨.
                    return false;
                }
                
            } else { // 건널 수 있는 돌이 생겼으면, 처음부터 다시 count.
                skip = 0;
            }
        }
        return true;
    }
}
