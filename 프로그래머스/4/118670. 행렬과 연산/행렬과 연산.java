import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    int r, c;
    ArrayDeque<Integer> col1, col2;
    Deque<Deque<Integer>> rows;

    /*
    |   ----------    |
    |   ----------    |         Rotate하는데 영향을 받는 col 두개도 deque로.
    |   ----------    |
    |   ----------    |
     */
    public int[][] solution(int[][] rc, String[] operations) {
        r = rc.length;
        c = rc[0].length;

        col1 = new ArrayDeque<>();
        col2 = new ArrayDeque<>();
        for (int i = 0; i < r; i++) {
            col1.add(rc[i][0]);
            col2.add(rc[i][c - 1]);
        }

        rows = new ArrayDeque<>();
        for (int i = 0; i < r; i++) {
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            for (int j = 1; j < c - 1; j++) {
                dq.add(rc[i][j]);
            }
            rows.add(dq);
        }

        for (String op : operations) {
            if (op.equals("Rotate")) {
                rotate();
            } else {
                shiftRow();
            }
        }
        return getAnswer();
    }

    private void shiftRow() {
        rows.addFirst(rows.pollLast());
        col1.addFirst(col1.pollLast());
        col2.addFirst(col2.pollLast());
    }

    private void rotate() {
        if (c == 2) { // 예외 케이스
            col2.addFirst(col1.pollFirst());
            col1.addLast(col2.pollLast());
            return;
        }
        rows.peekFirst().addFirst(col1.pollFirst());
        col2.addFirst(rows.peekFirst().pollLast());
        rows.peekLast().addLast(col2.pollLast());
        col1.addLast(rows.peekLast().pollFirst());
    }

    private int[][] getAnswer() {
        int[][] ans = new int[r][c];
        for (int i = 0; i < r; i++) {
            ans[i][0] = col1.pollFirst();
            ans[i][c - 1] = col2.pollFirst();
        }
        
        int i = 0;
        for (Deque<Integer> row : rows) {
            for (int j = 1; j < c - 1; j++) {
                ans[i][j] = row.pollFirst();
            }
            i++;
        }
        return ans;
    }
}