import java.util.*;

class Solution {
    
    static class Person {
        String name;
        Person parent;
        List<Person> children;
        int earnings;
        
        public Person(String name) {
            this.name = name;
            this.children = new ArrayList<>();
            this.earnings = 0;
        }
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Person> map = new HashMap<>();
        Person center = new Person("-");      
        map.put("-", center);
        
        for (int i = 0; i < enroll.length; i++) {
            String name = enroll[i];
            String ref  = referral[i];
            
            Person node = new Person(name);
            map.put(name, node);
            
            node.parent = map.get(ref); // 현재 노드의 부모 배치
            node.parent.children.add(node); // 부모의 children리스트에 node 추가
        }
        
        for (int i = 0; i < seller.length; i++) {
            Person cur = map.get(seller[i]);
            int profit = amount[i] * 100;
            
            while (cur != center && profit > 0) {
                int commission = profit / 10;
                cur.earnings += profit - commission;
                
                profit = commission;
                cur = cur.parent;
            }
        }
        
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = map.get(enroll[i]).earnings;
        }
        return answer;
    }
}
