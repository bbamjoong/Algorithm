class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        
        long dpPos = sequence[0];
        long dpNeg = -sequence[0];
        
        long answer = Math.max(dpPos, dpNeg);

        for (int i = 1; i < n; i++) {
            long x = sequence[i];
            
            // 이전값
            long prevPos = dpPos;
            long prevNeg = dpNeg;

            // 나부터 새로 시작할 지, 기존 값 이어갈지 결정
            dpPos = Math.max(x, prevNeg + x);
            dpNeg = Math.max(-x, prevPos - x);

            // ### 디버깅 ###
            // System.out.println(dpPos + " : " + dpNeg);
            //

            answer = Math.max(answer, Math.max(dpPos, dpNeg));
        }

        return answer;
    }
}
