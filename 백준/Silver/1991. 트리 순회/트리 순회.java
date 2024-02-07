import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static HashMap<String, String[]> tree;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        tree = new HashMap<>();

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            String c = st.nextToken();
            tree.put(a, new String[]{b, c});
        }

        preOrder("A");
        sb.append("\n");
        inOrder("A");
        sb.append("\n");
        postOrder("A");

        System.out.println(sb);
    }

    static void preOrder(String root) {
        if (!root.equals(".")) {
            sb.append(root); // root
            preOrder(tree.get(root)[0]); // left
            preOrder(tree.get(root)[1]); // right
        }
    }

    static void inOrder(String root) {
        if (!root.equals(".")) {
            inOrder(tree.get(root)[0]); // left
            sb.append(root); // root
            inOrder(tree.get(root)[1]); // right
        }
    }

    static void postOrder(String root) {
        if (!root.equals(".")) {
            postOrder(tree.get(root)[0]); // left
            postOrder(tree.get(root)[1]); // right
            sb.append(root); // root
        }
    }
}