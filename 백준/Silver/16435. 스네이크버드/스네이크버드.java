import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        LinkedList<Integer> foods = new LinkedList<>();

        while (st.hasMoreTokens()) {
            foods.add(Integer.parseInt(st.nextToken()));
        }

        foods.sort(((o1, o2) -> o1 - o2));

        for (Integer food : foods) {
            if (food <= l) {
                l++;
            } else {
                break;
            }
        }

        System.out.println(l);
    }
}