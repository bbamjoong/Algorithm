import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringBuilder sbFind = new StringBuilder();

    static Trie trie;
    static int index;
    static String word;
    static int len;

    private static final String NONE = "none";
    private static final int SIZE = 'z' - 'a' + 1;

    static class Trie {
        int cnt;
        boolean isEnd; // 끝지점이냐.
        Trie[] childTrie;

        public Trie() {
            this.cnt = 0;
            this.isEnd = false; // 끝지점임을 나타내는 것.
            this.childTrie = new Trie[SIZE];
        }

        void insert(String word) {
            Trie node = trie;
            for (char c : word.toCharArray()) {
                if (node.childTrie[c - 'a'] == null) { // 아직 들어온 글자가 없으면 새로운 trie를 만들어줌.
                    node.childTrie[c - 'a'] = new Trie();
                }
                node.cnt++; // 추가가 되었으니 카운트 증가해줌.
                node = node.childTrie[c - 'a']; // 이제 안쪽 trie로 들어가겠음.
            }
            node.cnt++; // 마지막에 도달했으면 마지막 character의 cnt도 증가시켜준다.
            node.isEnd = true; // 끝났다는 표시 해줌.
        }

        void find(int index) { // childTrie를 순회하면서 cnt를 체크해줘야함.
            sbFind.setLength(0); // find의 StringBuilder 길이 0으로 바꿔줌. 객체 생성하지말고 길이 바꾸자.
            // 트라이를 타고 들어가면서 1글자씩 추가하자.
            int cnt = index; // index는 용어에 혼동이 올 것 같아서 cnt로 할게요.

            while (true) { // 새로운 트라이를 들어갈 때 마다 반복해줄 것임.
                if (trie.isEnd) { // 여기에서 끝났던 단어가 있다. -> 값을 하나 줄여준다.
                    cnt--;
                }

                if (cnt == 0) {
                    break;
                }

                for (int i = 0; i < SIZE; i++) { // 현재 trie 내부를 탐색해준다.
                    if (trie.childTrie[i] != null) {
                        cnt -= trie.childTrie[i].cnt;
                    }

                    if (cnt <= 0) {
                        cnt += trie.childTrie[i].cnt; // 0이하로 index가 내려가면 이제 탐색해야 하니까 다시 index를 이전 trie만큼으로 복구시켜준다.
                        trie = trie.childTrie[i]; // 이쪽이 정답이 속해있는 trie구나!
                        sbFind.append((char) (i + 'a'));
                        break;
                    }
                }
            }
        }
    }

    static void setInputs() throws IOException {
        trie = new Trie(); // trie 생성
        index = Integer.parseInt(br.readLine()); // 몇 번째 문자열 고를거냐?
        word = br.readLine();
    }

    static void doLogic() {
        len = word.length(); // 글자 길이

        if (index > len) { // 글자 길이보다 index가 더 크면 바로 none 출력하자.
            sb.append(NONE).append("\n");
            return;
        }

        // index <= len이라면 trie에 insert 해줌.
        makeTrie();
    }

    static void makeTrie() {
        for (int i = 0; i < len; i++) {
            trie.insert(word.substring(i));
        }
        trie.find(index);
        sb.append(sbFind).append("\n");
    }

    public static void main(String[] args) throws Exception {

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            sb.append("#").append(tc).append(" ");
            setInputs();
            doLogic();
        }
        System.out.println(sb);
    }
}