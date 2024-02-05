import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int NODE_MAX = 5000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class LinkedList {
        Node head;
        Node tail;
        Node[] nodeArr;
        int nodeCnt;

        public LinkedList() {
            head = null;
            nodeArr = new Node[Solution.NODE_MAX];
            nodeCnt = 0;
        }

        Node getNewNode(int data) { // data를 값으로 갖는 새로운 node를 생성하고, 생성된 node를 return;
            nodeArr[nodeCnt] = new Node(data);
            return nodeArr[nodeCnt++];
        }

        void insert(int idx, int[] nums) { // 앞에서 idx 개 이후에 nums 들을 추가하기
            int st = 0;
            if (idx == 0) { // 맨앞에 붙임(head가 변경되어야함)
                // 한개만 추가 -> head 재조정
                if (head != null) {  // 이미 head가 있는데 제일 앞에 추가하려고 한다.
                    Node newNode = getNewNode(nums[0]);
                    newNode.next = head;
                    head = newNode;
                } else {
                    head = getNewNode(nums[0]);
                }

                idx = 1;
                st = 1;
            }

            Node cur = head;
            // idx개 만큼 이동하기
            for (int i = 1; i < idx; i++) {
                cur = cur.next;
            }
            // nums 추가하기
            for (int i = st; i < nums.length; i++) {
                Node newNode = getNewNode(nums[i]);
                newNode.next = cur.next;
                cur.next = newNode;
                cur = newNode;
            }
            if (cur.next == null) {
                tail = cur;
            }
        }

        void add(int data) {  // 제일 뒤에 data 추가하기
            Node cur = tail;
            Node newNode = getNewNode(data);
            cur.next = newNode;
            tail = newNode;
        }
    }

    public static void main(String[] args) throws Exception {
        for (int test_case = 1; test_case < 11; test_case++) {
            sb.append("#").append(test_case).append(" ");

            int n = Integer.parseInt(br.readLine()); // 원본 암호문의 길이
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            LinkedList linkedList = new LinkedList();
            linkedList.insert(0, arr);

            int k = Integer.parseInt(br.readLine()); // 명령 개수
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                st.nextToken();
                int idx = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());

                int[] tmpArr = new int[cnt];
                for (int j = 0; j < cnt; j++) {
                    tmpArr[j] = Integer.parseInt(st.nextToken());
                }

                linkedList.insert(idx, tmpArr);
            }

            Node currentNode = linkedList.head;
            for (int i = 0; i < 10; i++) {
                sb.append(currentNode.data).append(" ");
                currentNode = currentNode.next;
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
