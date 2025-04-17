class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int startTime = h1 * 3600 + m1 * 60 + s1;
        int endTime = h2 * 3600 + m2 * 60 + s2;
        
        // 12시부터 시작했을 때
        int num = endTime * 59 / 3600 + endTime * 719 / 43200
            - startTime * 59 / 3600 - startTime * 719 / 43200;
        
        if (startTime >= 43200) {
            num += 1;
        }
        
        if (endTime >= 43200) {
            num -= 1;
        }
        
        if (startTime * 59 % 3600 == 0 || startTime * 719 % 43200 == 0) {
            num += 1;
        }

        return num;
    } 
}