import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        LinkedList tower = new LinkedList();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i < n + 1; i++) {
            int num = Integer.parseInt(st.nextToken());
            while (true) {
                if (tower.isEmpty()) { // 비어있으면 stringbuilder에 0 추가, tower에 0 추가
                    tower.add(new Tower(num, i)); // 현재 건물 추가
                    sb.append("0").append(" "); // 답에 0 추가
                    break;
                } else if (num > tower.peekLast().number) { // 가장 마지막에 들어온 건물보다 높이가 크면
                    tower.pollLast(); // 가장 마지막에 들어온 건물 제거
                } else if (num < tower.peekLast().number) { // 가장 마지막에 들어온 건물보다 높이가 낮으면
                    sb.append(tower.peekLast().idx).append(" "); // 가장 마지막에 들어온 건물이 정답
                    tower.add(new Tower(num, i)); // tower에 지금 들어온 건물 추가
                    break;
                }
            }
        }
        System.out.println(sb);
    }

    static class LinkedList {
        Tower head;
        Tower tail;
        int cnt;

        public LinkedList() {
            head = null;
            tail = null;
        }

        public boolean isEmpty() {
            return cnt == 0;
        }

        Tower peekLast() {
            return tail;
        }

        void add(Tower tower) {
            if (head == null) { // 0개일 때
                head = tower;
                tail = tower;
            } else {
                tail.next = tower;
                tower.before = tail;
                tail = tower;
            }
            cnt++;
        }

        void pollLast() {
            if (cnt == 0) {
                return;
            } else if (cnt == 1) { // 1개일 때
                head = null;
                tail = null;
            } else {
                tail = tail.before;
                tail.next = null;
            }
            cnt--;
        }
    }


    static class Tower {
        int number;
        int idx;
        Tower next;
        Tower before;

        public Tower(int number, int idx) {
            this.number = number;
            this.idx = idx;
            this.next = null;
            this.before = null;
        }
    }
}