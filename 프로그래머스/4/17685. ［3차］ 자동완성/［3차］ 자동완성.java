class Solution {
    static class Trie {
        int count; // 이 노드를 포함한 서브트리의 단어 수
        boolean end;
        Trie[] child;

        public Trie() {
            this.count = 0;
            this.end = false;
            this.child = new Trie['z' - 'a' + 1];
        }

        public void add(String word, int idx) { // 단어 삽입: 지나가는 모든 노드의 count를 1씩 올려줌
            this.count++;
            
            if (idx == word.length()) { // 끝까지 도달
                this.end = true;
                return;
            }
            // 끝까지 도달 x
            int next = word.charAt(idx) - 'a';
            if (child[next] == null) {
                child[next] = new Trie();
            }
            child[next].add(word, idx + 1);
        }

        public int cal(String word, int idx) {
            // 한 글자 이상 입력했고, 이 서브트리에 단어가 하나뿐이면 여기서 멈춤
            if (idx > 0 && this.count == 1) {
                return idx;
            }
            
            // 단어 끝까지 왔으면 전체 길이만큼 입력
            if (idx == word.length()) {
                return idx;
            }
            
            // 다음 글자 계속 탐색
            int next = word.charAt(idx) - 'a';
            return child[next].cal(word, idx + 1);
        }
    }

    public int solution(String[] words) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.add(w, 0);
        }
        int answer = 0;
        for (String w : words) {
            answer += trie.cal(w, 0);
        }
        return answer;
    }
}
