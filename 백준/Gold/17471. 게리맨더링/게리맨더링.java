import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[] peopleCnt;
    static ArrayList<Integer>[] edgeList;
    static boolean[] groupsInfo;
    static int totalSum;
    static int ans = Integer.MAX_VALUE;
    static int tmpCnt;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        peopleCnt = new int[n + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i < n + 1; i++) {
            int count = Integer.parseInt(st.nextToken());
            peopleCnt[i] = count;
            totalSum += count;
        }

        edgeList = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            edgeList[i] = new ArrayList<>();
        }

        groupsInfo = new boolean[n + 1];

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int range = Integer.parseInt(st.nextToken());
            for (int j = 0; j < range; j++) {
                int num = Integer.parseInt(st.nextToken());
                edgeList[i].add(num);
            }
        }

        int range = n / 2;
        for (int size = 1; size <= range; size++) {
            combination(0, size, 1);
        }

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    static void combination(int depth, int size, int index) {
        if (depth == size) {
            if (checkGroup(size, true) && checkGroup(n - size, false)) {
                calculateAnswer();
            }
            return;
        }

        for (int i = index; i < n + 1; i++) {
            groupsInfo[i] = true;
            combination(depth + 1, size, i + 1);
            groupsInfo[i] = false;
        }
    }

    static boolean checkGroup(int size, boolean type) {
        boolean[] visited = new boolean[n + 1];

        for (int node = 1; node < groupsInfo.length; node++) {
            if (groupsInfo[node] == type) { // 조건에 맞는 가장 처음 노드 확인
                tmpCnt = 0;
                calculateGroupSize(1, node, type, visited);
                break; // 더 이상 다음 노드를 확인할 필요가 없음
            }
        }
        return tmpCnt == size;
    }

    static void calculateGroupSize(int count, int node, boolean type, boolean[] visited) {
        tmpCnt++; // 방문헀으므로 cnt++
        visited[node] = true; // 방문처리

        for (Integer next : edgeList[node]) {
            if (groupsInfo[next] == type && !visited[next]) { // 다음 노드가 검색할 type과 같고 + 방문하지 않은 곳이면
                calculateGroupSize(count + 1, next, type, visited); // 다음 노드 방문
            }
        }
    }

    static void calculateAnswer() {
        int countA = 0;
        int countB = 0;
        for (int i = 1; i < n + 1; i++) {
            if (groupsInfo[i]) {
                countA += peopleCnt[i];
            } else {
                countB += peopleCnt[i];
            }
        }
        ans = Math.min(ans, Math.abs(countA - countB));
    }
}
