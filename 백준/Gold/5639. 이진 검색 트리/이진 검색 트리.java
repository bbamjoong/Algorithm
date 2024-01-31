import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static ArrayList<Integer> preOrder = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while (true) {
            input = br.readLine();
            if (input == null || input.isEmpty()) {
                break;
            }
            preOrder.add(Integer.parseInt(input));
        }
        changeToPostOrder(0, preOrder.size() - 1);

        System.out.println(sb);
    }

    static void changeToPostOrder(int start, int end) {
        if (start > end) {
            return;
        }

        int rightStart = end + 1;

        for (int i = start; i < rightStart; i++) {
            if (preOrder.get(start) < preOrder.get(i)) {
                rightStart = i;
                break;
            }
        }

        changeToPostOrder(start + 1, rightStart - 1);
        changeToPostOrder(rightStart, end);
        sb.append(preOrder.get(start) + "\n");
    }
}