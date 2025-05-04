class Solution {
    
    private static final String EVEN = "Even";
    private static final String ODD = "Odd";
    
    public String solution(int num) {
        return num % 2 == 0 ? EVEN : ODD;
    }
}