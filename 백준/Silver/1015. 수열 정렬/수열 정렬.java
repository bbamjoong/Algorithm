import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;

    static Integer[] li;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        li = new Integer[n];
        for (int i = 0; i < n; i++) {
            li[i] = Integer.parseInt(st.nextToken());
        }

        // 값과 인덱스를 저장하는 리스트 생성
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pairs.add(new Pair(li[i], i));
        }

        // 값을 기준으로 오름차순 정렬
        Collections.sort(pairs);

        // P 배열 생성
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[pairs.get(i).index] = i;
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(res[i]).append(" ");
        }

        System.out.println(sb);
    }

    static class Pair implements Comparable<Pair> {
        int value, index;

        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Pair p) {
            return this.value - p.value;
        }
    }
}