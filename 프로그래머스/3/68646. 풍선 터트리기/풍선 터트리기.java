class Solution {
    public int solution(int[] a) {
        int n = a.length;
        if (n <= 2) {
            return n;  // 1개나 2개면 무조건 모두 생존 가능
        }

        int[] beforeMin = new int[n];
        int[] afterMin = new int[n];

        // 1) beforeMin 채우기
        beforeMin[0] = a[0];
        for (int i = 1; i < n; i++) {
            beforeMin[i] = Math.min(beforeMin[i-1], a[i]);
        }

        // 2) afterMin 채우기
        afterMin[n-1] = a[n-1];
        for (int i = n - 2; i >= 0; i--) {
            afterMin[i] = Math.min(afterMin[i+1], a[i]);
        }

        // 3) 생존 가능 풍선 개수 카운트
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > beforeMin[i] && a[i] > afterMin[i]) {
                continue;
            }
            count++;
        }
        
        return count;
    }
}
