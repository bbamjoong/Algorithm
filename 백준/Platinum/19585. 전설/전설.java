import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static Trie colorTrie;

    static int c;
    static int n;
    static Set<String> nicks = new HashSet<>();

    private static final int SIZE = 'z' - 'a' + 1;

    static class Trie {
        boolean isEnd; // 끝지점이냐.
        Trie[] childTrie;

        public Trie() {
            this.isEnd = false; // 끝지점임을 나타내는 것.
            this.childTrie = new Trie[SIZE];
        }

        void insert(String word) {
            Trie node = this;
            for (char c : word.toCharArray()) {
                if (node.childTrie[c - 'a'] == null) { // 아직 들어온 글자가 없으면 새로운 trie를 만들어줌.
                    node.childTrie[c - 'a'] = new Trie();
                }
                node = node.childTrie[c - 'a']; // 이제 안쪽 trie로 들어가겠음.
            }
            node.isEnd = true; // 끝났다는 표시 해줌.
        }

        ArrayList<Integer> find(String word) {
            Trie trie = this;

            ArrayList<Integer> tmp = new ArrayList<>();
            int idx = 0;

            while (true) {
                if (trie.isEnd) { // 끝에 해당하면 일단 답 갱신해주고 끝까지 탐색해버림.
                    tmp.add(idx);
                }

                if (idx == word.length()) {
                    return tmp;
                }

                boolean find = false; // 글자 찾았냐
                for (int i = 0; i < SIZE; i++) { // 현재 trie 내부를 탐색해준다.
                    if (trie.childTrie[i] != null) { // null이 아니면 글자 일치하는지 탐색.
                        if (i == word.charAt(idx) - 'a') { // 글자가 일치하면
                            trie = trie.childTrie[i];
                            idx++;
                            find = true;
                            break; // 찾으면 for문을 빠져나감.
                        }
                    }
                }
                if (!find) { // 못찾았으면 break;
                    break;
                }
            }
            return tmp;
        }
    }

    static void setInputs() throws IOException {
        colorTrie = new Trie(); // trie 생성

        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
    }

    static void doLogic() throws IOException {
        makeTrie();
        getAnswer();
    }

    static void makeTrie() throws IOException {
        for (int i = 0; i < c; i++) {
            String word = br.readLine();
            colorTrie.insert(word);
        }

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            nicks.add(word);
        }
    }

    static void getAnswer() throws IOException {
        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            String teamName = br.readLine();
            ArrayList<Integer> colors = colorTrie.find(teamName); // 조사한 color의 길이

            boolean find = false;
            for (Integer colorIdx : colors) {
                if (nicks.contains(teamName.substring(colorIdx))) {
                    sb.append("Yes").append("\n");
                    find = true;
                    break;
                }
            }

            if (!find) {
                sb.append("No").append("\n");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        setInputs();
        doLogic();
        System.out.printf(sb.toString());
    }
}
