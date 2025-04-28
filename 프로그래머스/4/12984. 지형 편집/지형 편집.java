class Solution {
    public long solution(int[][] land, int P, int Q) {
        int n = land.length, m = land[0].length;
        long bestCost = Long.MAX_VALUE;
        
        int s = 0, e = 1_000_000_000;;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            // 이분탐색을하는데 이 문제의 경우 비용은 오목함수임. 그러므로 옆의 값과 비교해서 이분탐색 진행.
            long costMid  = computeCost(land, P, Q, mid);
            long costNext = computeCost(land, P, Q, mid + 1);

            if (costMid < bestCost) bestCost = costMid; // 값 갱신

            if (costMid > costNext) s = mid + 1; 
            else e = mid - 1;
        }

        return bestCost;
    }

    private long computeCost(int[][] land, int P, int Q, int target) {
        long cost = 0;
        for (int[] row : land) {
            for (int h : row) {
                if (h < target)      cost += (long)(target - h) * P;
                else if (h > target) cost += (long)(h - target) * Q;
            }
        }
        return cost;
    }
}
