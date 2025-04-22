class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
    
        long left = 1;
        // 금 은의 무게는 10e9. 한번에 1만 옮길 수 있고, 편도시간이 10e5
        long right = (long) ((10e9 * 2) * (10e5 * 2)) + 1; 
        
        int len = g.length;
        
        while(left <= right){
            long mid = left + (right - left) / 2;
            
            //도시 순회
            int gold = 0;
            int silver = 0;
            int sum = 0;
            for(int i=0; i < len; i++){
                int weight = w[i];
                int time = t[i];
                
                long cnt = mid / (time * 2); // 운반 횟수 왕복
                
                if((mid % (time * 2)) >= time){ // 운반 편도
                    cnt++;
                }
                
                // 존재하는 양만큼만 가져갈 수 있음.
                gold += Math.min(g[i], weight * cnt);
                silver += Math.min(s[i], weight * cnt);
                sum += Math.min(g[i]+s[i], weight * cnt);
            }
            
            if(gold >=a && silver>=b && sum>=a+b){ // 운반 가능
                answer = mid;
                right = mid-1;
            } else { // 불가능
                left = mid+1;
            }
        }
        
        return answer;
    }
}