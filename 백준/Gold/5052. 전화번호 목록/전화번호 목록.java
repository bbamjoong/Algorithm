import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static class Trie {
        boolean end; // 여기서 끝나는 단어가 있나?
        boolean pass; // 여기를 지나간 단어가 있나?
        Trie[] child; // Trie 배열

        Trie() {
            end = false;
            pass = false;
            child = new Trie['9' - '0' + 1];
        }

        public boolean add(String word, int idx) {

            // 여기서 끝난 단어가 있다면? 일관성이 없다.
            if (end) {
                return false;
            }

            // 단어의 끝에 도달했을 때
            if (idx == word.length()) {
                end = true; // 여기서 끝나는 단어가 있다고 표시
                if (pass) { // 지나간 단어가 있다...?
                    return false; // 지나간 단어가 있다면 일관성이 없다.
                } else { // 지나간 단어가 없다면?
                    return true; // 처음 도달한 단어이다.
                }
            }
            // 단어의 끝에 도달하지 못했을 때
            else {
                int next = word.charAt(idx) - '0';
                if (child[next] == null) { // 처음 저장하는 알파벳이라면
                    child[next] = new Trie(); // 새로운 Trie를 생성
                    pass = true; // 지나간 표시를 해준다.
                }
                return child[next].add(word, idx + 1); // 도달한 적 있는 알파벳이라면 존재하는 Trie를 이용한다.
            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            sb.setLength(0);
            Trie trie = new Trie();
            String ans = "YES";
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                String word = br.readLine();
                boolean found = trie.add(word, 0);

                if (!found) {
                    ans = "NO";
                }
            }
            sb.append(ans);
            System.out.println(sb.toString());
        }
    }
}