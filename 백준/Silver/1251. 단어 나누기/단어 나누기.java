import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String left = "";
        String mid = "";
        String right = "";

        ArrayList<String> list = new ArrayList<>();

        for (int i = 1; i < input.length() - 1; i++) {
            left = input.substring(0, i);
            for (int j = i + 1; j < input.length(); j++) {
                String tmp = "";

                mid = input.substring(i, j);
                right = input.substring(j);

                tmp += reverse(left);
                tmp += reverse(mid);
                tmp += reverse(right);
                
                list.add(tmp);
            }
        }
        Collections.sort(list);
        System.out.println(list.get(0));
    }

    static String reverse(String word) {
        StringBuilder sb = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
            sb.append(word.charAt(i));
        }
        return sb.toString();
    }
}