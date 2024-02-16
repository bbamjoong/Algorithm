import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int peopleCnt = Integer.parseInt(br.readLine()); // 사람 수 입력
        ArrayList<Integer>[] people = new ArrayList[peopleCnt + 1];
        for (int i = 0; i < people.length; i++) {
            people[i] = new ArrayList<>();
        }

        // 비교할 사람 두명 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int person1 = Integer.parseInt(st.nextToken());
        int person2 = Integer.parseInt(st.nextToken());

        // 그래프 생성
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            people[a].add(b);
            people[b].add(a);
        }

        int[] visited = new int[peopleCnt + 1];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{person1, 0});

        while (!queue.isEmpty()) {
            int[] info = queue.poll();
            int node = info[0];
            int cnt = info[1];

            for (Integer person : people[node]) {
                if (visited[person] == 0) { // 방문 안한 좌표라면 bfs에 넣어줍니다.
                    visited[person] = cnt + 1; // 1촌 추가
                    queue.add(new int[]{person, cnt + 1}); // queue에 정보 넣기
                }
            }
        }

        if (visited[person2] == 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(visited[person2]);
    }
}