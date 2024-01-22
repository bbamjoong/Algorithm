import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int r;
    static int q;
    static List<Integer>[] graph;
    static int[] countArray;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        countArray = new int[n + 1]; // 쿼리의 개수를 저장한다.

        dfs(r); // 쿼리의 개수 계산

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            sb.setLength(0);
            int vertex = Integer.parseInt(br.readLine());
            sb.append(countArray[vertex]);
            System.out.println(sb.toString());
        }
    }

    static void dfs(int parent) {
        countArray[parent] = 1; // 처음 방문 시 1로 초기화 해준다.
        for (Integer children : graph[parent]) {
            if (countArray[children] >= 1) { // 1 or  그 이상의 값이면 한번 방문했다는 뜻.
                continue; // 방문했다면 continue;
                // 루트에서 리프로 타고 내려가는 방향이기 때문에, 다시 루트쪽으로 올라가는 것을 방지.
            }
            dfs(children); // 리프 dfs 실행
            countArray[parent] += countArray[children]; // 자식 노드의 쿼리 개수를 더해준다.
        }
    }
}