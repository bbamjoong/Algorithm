import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        LinkedList<int[]> tower = new LinkedList<>();
//        for (int i = 0; i < tower.size(); i++) {
//            tower.get(i) = new int[2];
//        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i < n + 1; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (tower.isEmpty()) { // 비어있으면 stringbuilder에 0 추가, tower에 추가
                tower.add(new int[]{num, i});
                sb.append("0").append(" ");
            } else {
                while (true) {
                    if (tower.isEmpty()) {
                        tower.add(new int[]{num, i});
                        sb.append("0").append(" ");
                        break;
                    } else if (num > tower.peekLast()[0]) {
                        tower.pollLast();
                    } else {
                        sb.append(tower.peekLast()[1]).append(" ");
                        tower.add(new int[]{num, i});
                        break;
                    }
                }
            }
        }
        System.out.println(sb);
    }
}