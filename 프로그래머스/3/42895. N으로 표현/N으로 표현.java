import java.util.*;
 
class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> countList = new ArrayList<>();

        for(int i=0; i<=8; i++) // 0개부터 ~ 8개썼을 때의 Set
            countList.add(new HashSet<>());

        countList.get(1).add(N); // N을 1개 쓴 값은 N 혼자

        for(int i=2; i<=8; i++){ // N을 2개 부터 ~ 8개까지
            Set<Integer> countSet = countList.get(i);

            for(int j=1; j<=i; j++){ // j를 i까지 순회하는 이유는 괄호때문임.
                Set<Integer> preSet = countList.get(j);
                Set<Integer> postSet = countList.get(i - j);

                for(int preNum : preSet){
                    for(int postNum : postSet){
                        countSet.add(preNum + postNum);
                        countSet.add(preNum - postNum);
                        countSet.add(preNum * postNum);

                        if(preNum != 0 && postNum != 0) countSet.add(preNum / postNum);
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < i; k++) sb.append(N);
            
            int combinedNumber = Integer.parseInt(sb.toString());
            countSet.add(combinedNumber);
        }

        for (int i = 1; i <= 8; i++) {
            if (countList.get(i).contains(number)) return i;
        }

        return -1;
    }
}
 