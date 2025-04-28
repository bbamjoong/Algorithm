import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        LinkedList list = new LinkedList(n, k);
        
        for (String c : cmd) {
            char op = c.charAt(0);
            if (op == 'U') {
                int x = Integer.parseInt(c.substring(2));
                list.moveUp(x);
            } else if (op == 'D') {
                int x = Integer.parseInt(c.substring(2));
                list.moveDown(x);
            } else if (op == 'C') {
                list.delete();
            } else { // 'Z'
                list.recover();
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (Node node : list.nodes) {
            if (node.removed) sb.append('X');
            else sb.append('O');
        }
        
        return sb.toString();
    }
    
    static class Node {
        final int idx;
        Node prev, next;
        boolean removed = false;

        Node(int idx) {
            this.idx = idx;
        }
    }
    
    static class LinkedList {
        Node[] nodes;
        Node current;
        Deque<Node> stack = new ArrayDeque<>();
        
        // 생성자: n개의 노드를 만들고 0~n-1 인덱스로 링크, current는 k번째
        public LinkedList(int n, int k) {
            nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node(i);
            }
            for (int i = 0; i < n; i++) {
                if (i > 0)  nodes[i].prev = nodes[i - 1];
                if (i < n - 1) nodes[i].next = nodes[i + 1];
            }
            current = nodes[k];
        }
        
        public void moveUp(int x) {
            for (int i = 0; i < x; i++) current = current.prev;
        }
        
        public void moveDown(int x) {
            for (int i = 0; i < x; i++) current = current.next;
        }
        
        public void delete() {
            stack.push(current); // 삭제처리 및 stack관리
            current.removed = true;
            
            Node prev = current.prev;
            Node next = current.next;
            
            // 참조관계 변경
            if (prev != null) prev.next = next;
            if (next != null) next.prev = prev;
            
            // 커서를 아래로, 없으면 위로
            if (next == null) {
                current = prev;
            } else if (next != null) {
                current = next;
            }
        }
        
        public void recover() {
            Node node = stack.pop();
            node.removed = false;
            
            Node p = node.prev;
            Node nx = node.next;
            if (p != null) p.next = node;
            if (nx != null) nx.prev = node;
        }
    }
}
