import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();

    private static class Node {
        int number;
        Node left, right;

        public Node(int number) {
            this.number = number;
        }

        void insert(int input) {
            if (this.number > input) {
                if (this.left == null) {
                    this.left = new Node(input);
                } else {
                    this.left.insert(input);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(input);
                } else {
                    this.right.insert(input);
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine())); // 루트

        while (true) { // 나머지 노드 삽입
            String input = br.readLine();
            if (input == null || input.isEmpty()) {
                break;
            }

            int num = Integer.parseInt(input);
            root.insert(num);
        }

        changeToPostOrder(root);

        System.out.println(sb);
    }

    static void changeToPostOrder(Node node) {
        if (node.left != null) { // 왼쪽
            changeToPostOrder(node.left);
        }
        if (node.right != null) { // 오른쪽
            changeToPostOrder(node.right);
        }
        sb.append(node.number).append("\n"); // 부모
    }
}