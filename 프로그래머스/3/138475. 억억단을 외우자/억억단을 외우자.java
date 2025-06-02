import java.util.*;

class Solution {
        
    static class Point implements Comparable<Point> {
        int num;
        int count;
        
        Point(int num, int count){
            this.num = num;
            this.count = count;
        }
        
        @Override
        public int compareTo(Point p){
            if (this.count != p.count) return p.count - this.count;
            return this.num - p.num;
        }
    }
    
    static Point[] dp;
    
    public int[] solution(int e, int[] starts) {
        dp = new Point[e + 1];
        
        for(int i = 0; i <= e; i++) dp[i] = new Point(i, 0);
        
        //배수 세기 O(e log e)
        for(int i = 1; i <= e; i++){
            for(int j = i; j <= e; j += i){
                dp[j].count++;
            }
        }
        
        //약수의 개수가 큰 순서대로 정렬 O(e log e)
        Arrays.sort(dp);     
        
        int[] answer = new int[starts.length];
        
        for(int i = 0; i < starts.length; i++){
            
            for(int j = 0; j <= e; j++){
                if(starts[i] <= dp[j].num){
                    answer[i] = dp[j].num;
                    break;
                }
            }
        }
        
        return answer;
    }
}