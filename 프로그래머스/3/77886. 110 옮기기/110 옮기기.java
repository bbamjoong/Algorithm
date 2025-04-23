import java.util.*;

public class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            Stack<Character> stack = new Stack<>();
            String str = s[i];
            int count = 0;

            for (int j = 0; j < str.length(); j++) {
                stack.push(str.charAt(j));

                if (stack.size() >= 3) {
                    char first = stack.pop();
                    if (first != '0') {
                        stack.push(first);
                        continue;
                    }
                    char second = stack.pop();
                    if (second != '1') {
                        stack.push(second);
                        stack.push(first);
                        continue;
                    }
                    char third = stack.pop();
                    if (third != '1') {
                        stack.push(third);
                        stack.push(second);
                        stack.push(first);
                        continue;
                    }
                    count++;
                }
            }

            StringBuilder sb = new StringBuilder();
            int index = -1; // 마지막 0의 위치
            int size = stack.size() - 1;

            while (!stack.isEmpty()) {
                char value = stack.pop();
                sb.insert(0, value);
                if (index == -1 && value == '0') {
                    index = size;
                }
                size--;
            }

            int offset;
            if (index == -1) {
                offset = 0;
            } else {
                offset = index + 1;
            }

            while (count-- > 0) {
                sb.insert(offset, "110");
            }

            answer[i] = sb.toString();
        }
        return answer;
    }
}